package com.dev2.sa.sistemaacessibilidade;

        import android.app.Activity;
        import android.content.ClipData;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.support.annotation.DrawableRes;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.DragEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.jmedeisis.draglinearlayout.DragLinearLayout;

        import java.util.ArrayList;
        import java.util.HashMap;

public class Jogo_De_Ordenar_Palavra2_fase2 extends AppCompatActivity {

    private int fase = 1, acerto = 0;
    private final int pontoAcerto = 10;
    private final int pontoErro = 5;
    private int pontuacao = 0;

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo__de__ordenar__palavra2_fase2);

        findViewById(R.id.palavragrilo).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavratrono).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavratreno).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavraporta).setOnLongClickListener(new MyOnLongClickListener());


        findViewById(R.id.primeiro).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.segundo).setOnDragListener(new MyOnDragListener(2));
        findViewById(R.id.terceiro).setOnDragListener(new MyOnDragListener(3));
        findViewById(R.id.quarto).setOnDragListener(new MyOnDragListener(4));

        randomObjects();
    }

    class MyOnLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            //DragShadowBuilder sb = new View.DragShadowBuilder(findViewById(R.id.shadow));
            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);
            v.startDrag(data, sb, v, 0);
            // Esconde a imagem quando for arrastar a sua sombra
            // v.setVisibility(View.INVISIBLE);
            return (true);
        }
    }

    private  void randomObjects() {
        int[] vetor = new int[4];         // troca o valor para que nao seja validado de forma errada quando o vetor for iniciado com 0
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = 9;
        }
        for (int i = 0; i < 4; i++) {
            int numeroSorteado = Metodos.sortearNumero(4);
            boolean valida = Metodos.validaNumero(vetor, numeroSorteado);
            if (valida) {
                vetor[i] = numeroSorteado;
            } else {
                boolean encontrou = false;
                while (!encontrou) {
                    numeroSorteado = Metodos.sortearNumero(4);
                    valida = Metodos.validaNumero(vetor, numeroSorteado);
                    if (valida) {
                        vetor[i] = numeroSorteado;
                        encontrou = true;
                    }
                }
            }
        }

        image = new ImageView(this);
        image = (ImageView) findViewById(R.id.palavragrilo);
        image.setImageResource(Metodos.getDrawableContent(vetor[0],fase));
        image.setTag(Metodos.getDrawableContent(vetor[0],fase));

        image = new ImageView(this);
        image = (ImageView) findViewById(R.id.palavratrono);
        image.setImageResource(Metodos.getDrawableContent(vetor[1],fase));
        image.setTag(Metodos.getDrawableContent(vetor[1],fase));

        image = new ImageView(this);
        image = (ImageView) findViewById(R.id.palavratreno);
        image.setImageResource(Metodos.getDrawableContent(vetor[2],fase));
        image.setTag(Metodos.getDrawableContent(vetor[2],fase));

        image = new ImageView(this);
        image = (ImageView) findViewById(R.id.palavraporta);
        image.setImageResource(Metodos.getDrawableContent(vetor[3],fase));
        image.setTag(Metodos.getDrawableContent(vetor[3],fase));
    }

    class MyOnDragListener implements View.OnDragListener {
        private int num;

        // Construtor
        public MyOnDragListener(int num) {
            super();
            this.num = num;
        }

        // View 'v' é o parâmetro onde o objeto está sendo largado
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DROP:
                    Log.i("Script", num + " - ACTION_DROP");
                    // Move a imagem de um container para outro (6 linhas abaixo)
                    View view = (View) event.getLocalState();//aqui entra quem está sendo movido

                    // indice onde foi largada a imagem
                    int index = 0;
                    if (v.getId() == R.id.primeiro) // Clicou na primeira imagem
                        index = 0;
                    else if (v.getId() == R.id.segundo) // Clicou na segunda imagem
                        index = 1;
                    else if (v.getId() == R.id.terceiro) // Clicou na terceira imagem
                        index = 2;
                    else if (v.getId() == R.id.quarto) // Clicou na quarta imagem
                        index = 3;

                    String tagId = view.getTag().toString();
                    String palavra = Metodos.getDrawableString(Integer.parseInt(tagId), fase);

                    boolean acertou = validade(index, palavra);
                    if(acertou){
                        acerto++;
                        // faz o somatório
                        int total = Metodos.somaTotal(pontuacao, pontoAcerto, true);
                        setPontuacao(total);
                        Toast.makeText(Jogo_De_Ordenar_Palavra2_fase2.this, "VOCÊ ACERTOU!", Toast.LENGTH_SHORT).show();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    } else{
                        // mensagem de erro para o usuário
                        Toast.makeText(Jogo_De_Ordenar_Palavra2_fase2.this, "PALAVRA ERRADA!", Toast.LENGTH_SHORT).show();
                        int total = Metodos.somaTotal(pontuacao,pontoErro, false);
                        setPontuacao(total);
                    }

                    if (acerto == 4) {
                        // fala a palavra correta
                        if(getFase() <= Metodos.palavras.length && getFase() != Metodos.palavras.length - 1) {
                            ShowDialogNext(Jogo_De_Ordenar_Palavra2_fase2.this, R.drawable.icopala, "PARABÉNS!", "VOCÊ GANHOU!!");
                        }else{
                            ShowDialogRecreateGame(Jogo_De_Ordenar_Palavra2_fase2.this, R.drawable.icocasa, "PARABÉNS!", "VOCÊ CONCLUIU TODAS AS FASES!\n SUA PONTUAÇÃO: " + getPontuacao());
                        }
                    }
                    TextView mostra = (TextView)findViewById(R.id.txtPonto);
                    int pontuacaoAtual = getPontuacao();
                    mostra.setText(Integer.toString(pontuacaoAtual));
                    break;
            }
            return true;
        }
    }

    private boolean validade(int index, String palavra){
        boolean isValid = false;
        HashMap<Integer,String> map = hashMap();
        for (int key : map.keySet()) {
            if(palavra.equals(map.get(index))){
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    private HashMap<Integer,String> hashMap(){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0,"grilo");
        map.put(1,"trono");
        map.put(2,"treno");
        map.put(3,"porta");

        return map;
    }

    public void ShowDialogNext(final Activity act, @DrawableRes int desenho, String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle(titulo)
                .setMessage(mensagem)
                .setCancelable(false)
                .setIcon(desenho)
                .setPositiveButton("PRÓXIMA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        fase++;
                        setFase(fase);
                        if(getFase() <= Metodos.palavras.length && getFase() != Metodos.palavras.length) {
                            Intent nextActivity = new Intent(getBaseContext(), Jogo_De_Ordenar_Palavra2_fase2.class);
                            nextActivity.putExtra("fase", getFase());
                            nextActivity.putExtra("pontuacao", getPontuacao());
                            startActivity(nextActivity);
                            finish();
                        }
                    }
                }).setNegativeButton("SAIR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                act.finish();
            }
        });
        builder.create().show();        // create and show the alert dialog
    }

    public void ShowDialogRecreateGame(final Activity act, @DrawableRes int desenho, String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle(titulo)
                .setMessage(mensagem)
                .setCancelable(false)
                .setIcon(desenho)
                .setPositiveButton("REINICIAR FASES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        fase = 0;
                        setFase(fase);
                        pontuacao = 0;
                        setPontuacao(pontuacao);
                        Intent nextActivity = new Intent(getBaseContext(), Jogo_de_Ordenar_Palavra2.class);
                        nextActivity.putExtra("fase", getFase());
                        startActivity(nextActivity);
                        finish();
                    }
                }).setNegativeButton("SAIR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                act.finish();
            }
        });
        builder.create().show();        // create and show the alert dialog
    }
}