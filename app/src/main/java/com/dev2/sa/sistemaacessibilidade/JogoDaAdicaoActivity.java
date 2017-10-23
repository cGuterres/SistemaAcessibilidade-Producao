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

public class JogoDaAdicaoActivity extends AppCompatActivity {

    private final int PONTO_ACERTO = 10;
    private final int PONTO_ERRO = 5;
    private final int TOTAL_ACERTO = 8;
    private final int TOTAL_FASE = 3;
    private int acerto = 0,fase = 0;
    private int pontuacao = 0;
    private ImageView img;

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_da_adicao);

        Intent intent = getIntent();
        if(intent != null) {
            int nextPhase = intent.getIntExtra("fase", 0);
            if(nextPhase <= Metodos.palavras.length) {
                setFase(nextPhase);
            }
            int pontuacaoAtual = intent.getIntExtra("pontuacao", 0);
            setPontuacao(pontuacaoAtual);

            TextView txtPonto = (TextView)findViewById(R.id.txtPonto);
            if(txtPonto != null) {
                txtPonto.setText(Integer.toString(getPontuacao()));
            }
        }

        createElements();

        setTags(getFase());
    }

    private void setTags(int fase){
        int[] vetor = new int[8];
        // troca o valor para que nao seja validado de forma errada quando o vetor for iniciado com 0
        int max = 8, min = 0;
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = 100;
        }
        for (int i = 0; i < vetor.length; i++) {
            int numeroSorteado = Metodos.randomNumber(max,min);
            boolean valida = Metodos.validaNumero(vetor, numeroSorteado);
            if (valida) {
                if( fase == 0 && numeroSorteado <= 10){
                    vetor[i] = numeroSorteado;
                }else if(fase == 1 && numeroSorteado >= 10 && numeroSorteado <= 21){
                    vetor[i] = numeroSorteado;
                }else if(fase == 2 && numeroSorteado > 20){
                    vetor[i] = numeroSorteado;
                }
            } else {
                boolean encontrou = false;
                while (!encontrou) {
                    numeroSorteado = Metodos.randomNumber(max,min);
                    valida = Metodos.validaNumero(vetor, numeroSorteado);
                    if (valida) {
                        vetor[i] = numeroSorteado;
                        encontrou = true;
                    }
                }
            }
        }


        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num1);
        img.setImageResource(Metodos.SetNumberDrawable(vetor[0], fase));
        img.setTag(Metodos.SetNumberDrawable(vetor[0], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num2);
        img.setImageResource(Metodos.SetNumberDrawable(vetor[1], fase));
        img.setTag(Metodos.SetNumberDrawable(vetor[1], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num3);
        img.setImageResource(Metodos.SetNumberDrawable(vetor[2], fase));
        img.setTag(Metodos.SetNumberDrawable(vetor[2], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num4);
        img.setImageResource(Metodos.SetNumberDrawable(vetor[3], fase));
        img.setTag(Metodos.SetNumberDrawable(vetor[3], fase));


        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.colCNum1);
        img.setImageResource(Metodos.SetNumberDrawableJogoSoma(vetor[0], fase));
        img.setTag(Metodos.SetNumberDrawableJogoSoma(vetor[0], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.ColCNum2);
        img.setImageResource(Metodos.SetNumberDrawableJogoSoma(vetor[1], fase));
        img.setTag(Metodos.SetNumberDrawableJogoSoma(vetor[1], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.ColCNum3);
        img.setImageResource(Metodos.SetNumberDrawableJogoSoma(vetor[2], fase));
        img.setTag(Metodos.SetNumberDrawableJogoSoma(vetor[2], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.ColCNum4);
        img.setImageResource(Metodos.SetNumberDrawableJogoSoma(vetor[3], fase));
        img.setTag(Metodos.SetNumberDrawableJogoSoma(vetor[3], fase));
    }

    public static boolean validaNumero(int[] vetor, int value,int fase) {
        // valida as posições
        if(fase == 0 && value != 1 && value != 4 && value != 7 && value != 9){
            return false;
        } else if(fase == 1 && value != 11 && value != 14 && value != 17 && value != 20){
            return false;
        }else if(fase == 2){
            return false;
        }
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == value)
                // numero já sorteado
                return false;
        }
        // numero nao existe
        return true;
    }

    private void createElements(){
        findViewById(R.id.num1).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num2).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num3).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num4).setOnLongClickListener(new MyOnLongClickListener());
      /*  findViewById(R.id.num6).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num7).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num8).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num9).setOnLongClickListener(new MyOnLongClickListener());

        findViewById(R.id.primeiroE).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.segundoE).setOnDragListener(new MyOnDragListener(2));
        findViewById(R.id.terceiroE).setOnDragListener(new MyOnDragListener(3));
        findViewById(R.id.quartoE).setOnDragListener(new MyOnDragListener(4));
*/
        findViewById(R.id.primeiroD).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.segundoD).setOnDragListener(new MyOnDragListener(2));
        findViewById(R.id.terceiroD).setOnDragListener(new MyOnDragListener(3));
        findViewById(R.id.quartoD).setOnDragListener(new MyOnDragListener(4));
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
                    int index = 0, numCentral = 0;
                    //boolean ladoE = false;
                     boolean ladoD = false;
                    String tagV = "";
                    ImageView imageCenter = null;
                  /*  if (v.getId() == R.id.primeiroE) { // Clicou na primeira imagem
                        ladoE = true;
                        imageCenter = (ImageView)findViewById(R.id.colCNum1);
                        tagV = imageCenter.getTag().toString();
                    } else if (v.getId() == R.id.segundoE) { // Clicou na segunda imagem
                        ladoE = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum2);
                        tagV = imageCenter.getTag().toString();
                    } else if (v.getId() == R.id.terceiroE) { // Clicou na terceira imagem
                        ladoE = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum3);
                        tagV = imageCenter.getTag().toString();
                    } else if (v.getId() == R.id.quartoE) { // Clicou na quarta imagem
                        ladoE = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum4);
                        tagV = imageCenter.getTag().toString();
                    }
                    else     */ if(v.getId() == R.id.primeiroD){
                        ladoD = true;
                        imageCenter = (ImageView)findViewById(R.id.colCNum1);
                        tagV = imageCenter.getTag().toString();
                    }else if(v.getId() == R.id.segundoD){
                        ladoD = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum2);
                        tagV = imageCenter.getTag().toString();
                    }else if(v.getId() == R.id.terceiroD){
                        ladoD = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum3);
                        tagV = imageCenter.getTag().toString();
                    }else if(v.getId() == R.id.quartoD){
                        ladoD = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum4);
                        tagV = imageCenter.getTag().toString();
                    }

                    String tagId = view.getTag().toString();
                    numCentral = Metodos.getNumberForDrawableJogoSoma(Integer.parseInt(tagV), fase);
                    if(tagId.equals("10")){
                        tagId = Integer.toString(R.drawable.numerodez);
                    }
                    if(tagId.equals("8")){
                        tagId = Integer.toString(R.drawable.numerooito);
                    }
                    int numMovido = Metodos.getNumberForDrawableJogoSoma(Integer.parseInt(tagId),fase);
                    boolean acertou = false;
                   if(ladoD){
                        acertou = Metodos.ehSucessor(numCentral, numMovido);
                    }

                    if (acertou) {
                        acerto++;
                        // faz o somatório
                        int total = Metodos.somaTotal(pontuacao, PONTO_ACERTO, true);
                        setPontuacao(total);
                        Toast.makeText(JogoDaAdicaoActivity.this, "VOCÊ ACERTOU!", Toast.LENGTH_SHORT).show();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    } else {
                        // mensagem de erro para o usuário
                        Toast.makeText(JogoDaAdicaoActivity.this, "LETRA ERRADA!", Toast.LENGTH_SHORT).show();
                        int total = Metodos.somaTotal(pontuacao,PONTO_ERRO, false);
                        setPontuacao(total);
                    }
                    if (acerto == TOTAL_ACERTO) {
                        // fala a palavra correta
                        if(getFase() < TOTAL_FASE - 1) {
                            ShowDialogNext(JogoDaAdicaoActivity.this, R.drawable.icopala, "PARABÉNS!", "VOCÊ GANHOU!!");
                        }else{
                            ShowDialogRecreateGame(JogoDaAdicaoActivity.this, R.drawable.icocasa, "PARABÉNS!", "VOCÊ CONCLUIU TODAS AS FASES!\n SUA PONTUAÇÃO: " + getPontuacao());
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
                            Intent nextActivity = new Intent(getBaseContext(), JogoDosVizinhosActivity.class);
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
                        pontuacao = 0;
                        setPontuacao(pontuacao);
                    }
                }).setNegativeButton("SAIR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                act.finish();
            }
        });
        builder.create().show();        // create and show the alert dialog
    }
}
