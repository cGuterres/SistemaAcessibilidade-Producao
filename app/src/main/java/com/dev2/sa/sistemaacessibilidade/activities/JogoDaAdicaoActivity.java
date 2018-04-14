package com.dev2.sa.sistemaacessibilidade.activities;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev2.sa.sistemaacessibilidade.utils.Metodos;
import com.dev2.sa.sistemaacessibilidade.R;

public class JogoDaAdicaoActivity extends AppCompatActivity {

    private final int PONTO_ACERTO = 10;
    private final int PONTO_ERRO = 5;
    private final int TOTAL_ACERTO = 4;
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

        createElements();// cria elementos que podem ser mexidos no layout

        setTags(getFase());// metodo usado para validar os drawlable
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
                vetor[i] = numeroSorteado;
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
        img.setImageResource(Metodos.SetNumberDrawable(vetor[0], 0));
        img.setTag(Metodos.SetNumberDrawable(vetor[0], 0));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num2);
        img.setImageResource(Metodos.SetNumberDrawable(vetor[1], 0));
        img.setTag(Metodos.SetNumberDrawable(vetor[1], 0));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num3);
        img.setImageResource(Metodos.SetNumberDrawable(vetor[2], 0));
        img.setTag(Metodos.SetNumberDrawable(vetor[2], 0));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num4);
        img.setImageResource(Metodos.SetNumberDrawable(vetor[3], 0));
        img.setTag(Metodos.SetNumberDrawable(vetor[3], 0));

        // é adicionado as equações de acordo com os valores randomicos do vetor
        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.colCNum1);
        img.setImageResource(Metodos.SetNumberDrawableJogoSoma(vetor[0], getFase()));
        img.setTag(Metodos.SetNumberDrawableJogoSoma(vetor[0], getFase()));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.ColCNum2);
        img.setImageResource(Metodos.SetNumberDrawableJogoSoma(vetor[1], getFase()));
        img.setTag(Metodos.SetNumberDrawableJogoSoma(vetor[1], getFase()));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.ColCNum3);
        img.setImageResource(Metodos.SetNumberDrawableJogoSoma(vetor[2], getFase()));
        img.setTag(Metodos.SetNumberDrawableJogoSoma(vetor[2], getFase()));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.ColCNum4);
        img.setImageResource(Metodos.SetNumberDrawableJogoSoma(vetor[3], getFase()));
        img.setTag(Metodos.SetNumberDrawableJogoSoma(vetor[3], getFase()));
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

    // cria elementos que podem ser mexidos no layout
    private void createElements(){
        findViewById(R.id.num1).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num2).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num3).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num4).setOnLongClickListener(new MyOnLongClickListener());

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
            // seta a visibilidade de quem está sendo movido para invisível
            v.setVisibility(View.INVISIBLE);
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
            View view = (View) event.getLocalState();//aqui entra quem está sendo movido
            switch (action) {
                // Quando tem ação de drop deixa a sombra sempre visível
                case DragEvent.ACTION_DRAG_ENDED:
                    //qualquer evento de drag que acabar vai setar a figura para visível, independente do lugar que a figura cair
                    view.setVisibility(View.VISIBLE);
                    break;

                case DragEvent.ACTION_DROP:
                    Log.i("Script", num + " - ACTION_DROP");
                    // Move a imagem de um container para outro (6 linhas abaixo)


                    // fluxo para pegar a imagem da soma correspondente ao valor que foi movido
                    int colDrawableId = 0;
                    String tagViewDaSoma = "";
                    ImageView viewDaSoma = null;
                    if (v.getId() == R.id.primeiroD){// Clicou na primeira imagem
                        viewDaSoma = (ImageView)findViewById(R.id.colCNum1);
                        if(viewDaSoma.getTag() != null){
                            tagViewDaSoma = viewDaSoma.getTag().toString();
                        }
                    } else if (v.getId() == R.id.segundoD){// Clicou na primeira imagem
                        viewDaSoma = (ImageView)findViewById(R.id.ColCNum2);
                        if(viewDaSoma.getTag() != null){
                            tagViewDaSoma = viewDaSoma.getTag().toString();
                        }
                    } else if (v.getId() == R.id.terceiroD){// Clicou na primeira imagem
                        viewDaSoma = (ImageView)findViewById(R.id.ColCNum3);
                        if(viewDaSoma.getTag() != null){
                            tagViewDaSoma = viewDaSoma.getTag().toString();
                        }
                    }else if (v.getId() == R.id.quartoD){// Clicou na primeira imagem
                        viewDaSoma = (ImageView)findViewById(R.id.ColCNum4);
                        if(viewDaSoma.getTag() != null){
                            tagViewDaSoma = viewDaSoma.getTag().toString();
                        }
                    }
                    // variavel equacao referente a imagem da coluna da esquerda
                    int equacao = Metodos.getNumberForDrawableJogoSoma(Integer.parseInt(tagViewDaSoma),getFase());
                    String tagId = view.getTag().toString();
                    // numero que foi movido pelo usuario
                    int numMovido = Metodos.getNumberForDrawable(Integer.parseInt(tagId),0);
                    boolean acertou = valida(equacao, numMovido);
                    if (acertou) {
                        // chama o som correspondente a soma
                        Metodos.sound(Integer.parseInt(tagId), JogoDaAdicaoActivity.this);
                        acerto++;
                        // faz o somatório
                        int total = Metodos.somaTotal(pontuacao, PONTO_ACERTO, true);
                        setPontuacao(total);
                        Toast.makeText(JogoDaAdicaoActivity.this, "VOCÊ ACERTOU!", Toast.LENGTH_SHORT).show();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                       // view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    } else {
                        // mensagem de erro para o usuário
                        Toast.makeText(JogoDaAdicaoActivity.this, "SOMA ERRADA!", Toast.LENGTH_SHORT).show();
                        int total = Metodos.somaTotal(pontuacao,PONTO_ERRO, false);
                        setPontuacao(total);
                    }
                    //volta com a visibilidade de quem está sendo movido para voltar a qualquer lugar
                    view.setVisibility(View.VISIBLE);
                    if (acerto == TOTAL_ACERTO) {
                        // fala a palavra correta
                        if(getFase() < TOTAL_FASE - 1) {
                            dialogMessageResult(true, JogoDaAdicaoActivity.this);
                        }else{
                            dialogMessageResultFinal(true, JogoDaAdicaoActivity.this);
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

    private boolean valida(int equacao, int numero){
        return equacao == numero;
    }

    // metodo responsavel por reiniciar o jogo da memória, iniciando da fase 0
    public void dialogMessageResultFinal(boolean acertou, final Activity act){
        AlertDialog.Builder start_dialog = new AlertDialog.Builder(this);

        TextView start_dialog_desc = new TextView(this);
        if(acertou)
            start_dialog_desc.setBackgroundResource(R.drawable.tela_acertou);
        else
            start_dialog_desc.setBackgroundResource(R.drawable.tela_errou);

        start_dialog_desc.setGravity(Gravity.CENTER);
        start_dialog_desc.setTextColor(Color.WHITE);
        start_dialog.setView(start_dialog_desc);

        start_dialog.setPositiveButton("REINICIAR FASES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                fase = 0;
                setFase(fase);
                pontuacao = 0;
                setPontuacao(pontuacao);
                Intent nextActivity = new Intent(getBaseContext(), JogoDaAdicaoActivity.class);
                startActivity(nextActivity);
                finish();
            }
        }).setNegativeButton("SAIR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                act.finish();
            }
        });

        AlertDialog alert = start_dialog.create();
        alert.show();
    }

    // dialog responsavel por exibir imagem de acerto ao final
    public void dialogMessageResult(boolean acertou, final Activity act){
        AlertDialog.Builder start_dialog = new AlertDialog.Builder(this);

        TextView start_dialog_desc = new TextView(this);
        if(acertou)
            start_dialog_desc.setBackgroundResource(R.drawable.tela_acertou);
        else
            start_dialog_desc.setBackgroundResource(R.drawable.tela_errou);

        start_dialog_desc.setGravity(Gravity.CENTER);
        start_dialog_desc.setTextColor(Color.WHITE);
        start_dialog.setView(start_dialog_desc);

        start_dialog.setPositiveButton("PRÓXIMA", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                fase++;
                setFase(fase);
                if(getFase() <= TOTAL_FASE - 1) {
                    Intent nextActivity = new Intent(getBaseContext(), JogoDaAdicaoActivity.class);
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

        AlertDialog alert = start_dialog.create();
        alert.show();
    }
}
