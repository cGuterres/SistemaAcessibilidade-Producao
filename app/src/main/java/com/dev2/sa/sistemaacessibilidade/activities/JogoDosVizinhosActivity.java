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

public class JogoDosVizinhosActivity extends AppCompatActivity {

    private final int PONTO_ACERTO = 10;
    private final int PONTO_ERRO = 5;
    private final int TOTAL_ACERTO = 8;
    private final int TOTAL_FASE = 3;
    private final int ARRAY_SORT = 8;
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
        setContentView(R.layout.activity_jogo_dos_vizinhos);

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
        int[] vetor = new int[ARRAY_SORT];
        // troca o valor para que nao seja validado de forma errada quando o vetor for iniciado com 0
        int max = 0, min = 0;
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = 100;
        }
        if(fase == 0){
            max = 7;
        }else if(fase == 1){
            min = 13;
            max = 20;
        }else{
            min = 23;
            max = 30;
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

        int[] newVetor = new int[vetor.length];
        if(fase == 0) {
            for (int j = 0; j < vetor.length; j++) {
                newVetor[j] = vetor[j];
                if (vetor[j] == 1) {
                    newVetor[j] = 8;
                }
                if (vetor[j] == 4) {
                    newVetor[j] = 8;
                }
                if (vetor[j] == 7) {
                    newVetor[j] = 10;
                }
            }
        }else if(fase == 1){
            for (int j = 0; j < vetor.length; j++) {
                newVetor[j] = vetor[j];
                if (vetor[j] == 14) {
                    newVetor[j] = 10;
                }
                if (vetor[j] == 17) {
                    newVetor[j] = 12;
                }
                if (vetor[j] == 20) {
                    newVetor[j] = 21;
                }
            }
        }else if(fase == 2){
            for (int j = 0; j < vetor.length; j++) {
                newVetor[j] = vetor[j];
                if (vetor[j] == 26) {
                    newVetor[j] = 20;
                }
                if (vetor[j] == 24) {
                    newVetor[j] = 25;
                }
                if(vetor[j] == 29){
                    newVetor[j] = 22;
                }
            }
        }

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num1);
        img.setImageResource(Metodos.SetNumberDrawable(newVetor[0], fase));
        img.setTag(Metodos.SetNumberDrawable(newVetor[0], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num2);
        img.setImageResource(Metodos.SetNumberDrawable(newVetor[1], fase));
        img.setTag(Metodos.SetNumberDrawable(newVetor[1], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num3);
        img.setImageResource(Metodos.SetNumberDrawable(newVetor[2], fase));
        img.setTag(Metodos.SetNumberDrawable(newVetor[2], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num4);
        img.setImageResource(Metodos.SetNumberDrawable(newVetor[3], fase));
        img.setTag(Metodos.SetNumberDrawable(newVetor[3], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num6);
        img.setImageResource(Metodos.SetNumberDrawable(newVetor[4], fase));
        img.setTag(Metodos.SetNumberDrawable(newVetor[4], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num7);
        img.setImageResource(Metodos.SetNumberDrawable(newVetor[5], fase));
        img.setTag(Metodos.SetNumberDrawable(newVetor[5], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num8);
        img.setImageResource(Metodos.SetNumberDrawable(newVetor[6], fase));
        img.setTag(Metodos.SetNumberDrawable(newVetor[6], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num9);
        img.setImageResource(Metodos.SetNumberDrawable(newVetor[7], fase));
        img.setTag(Metodos.SetNumberDrawable(newVetor[7], fase));

        int[] vetorCentral = SetNumeroCentral(fase);
        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.colCNum1);
        img.setImageResource(Metodos.SetNumberDrawable(vetorCentral[0], fase));
        img.setTag(Metodos.SetNumberDrawable(vetorCentral[0], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.ColCNum2);
        img.setImageResource(Metodos.SetNumberDrawable(vetorCentral[1], fase));
        img.setTag(Metodos.SetNumberDrawable(vetorCentral[1], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.ColCNum3);
        img.setImageResource(Metodos.SetNumberDrawable(vetorCentral[2], fase));
        img.setTag(Metodos.SetNumberDrawable(vetorCentral[2], fase));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.ColCNum4);
        img.setImageResource(Metodos.SetNumberDrawable(vetorCentral[3], fase));
        img.setTag(Metodos.SetNumberDrawable(vetorCentral[3], fase));
    }

    private int[] SetNumeroCentral(int fase){
        int[] vetor = new int[4];
        int max = 0, min = 0;
        if(fase == 0){
            max = 9;
        }else if(fase == 1){
            min = 10;
            max = 21;
        }else{
            min = 21;
            max = 30;
        }
        for (int i = 0; i < vetor.length; i++) {
            int numeroSorteado = Metodos.randomNumber(max,min);
            boolean valida = validaNumero(vetor, numeroSorteado, fase);
            if (valida) {
                vetor[i] = numeroSorteado;
            } else {
                boolean encontrou = false;
                while (!encontrou) {
                    numeroSorteado = Metodos.randomNumber(max,min);
                    valida = validaNumero(vetor, numeroSorteado, fase);
                    if (valida) {
                        vetor[i] = numeroSorteado;
                        encontrou = true;
                    }
                }
            }
        }
        return vetor;
    }

    public static boolean validaNumero(int[] vetor, int value,int fase) {
        // valida as posições
        if(fase == 0 && value != 1 && value != 4 && value != 7 && value != 9){
            return false;
        } else if(fase == 1 && value != 11 && value != 14 && value != 17 && value != 20){
            return false;
        }else if(fase == 2 && value != 21 && value != 24 && value != 26 && value != 29){
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
        findViewById(R.id.num6).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num7).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num8).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num9).setOnLongClickListener(new MyOnLongClickListener());

        findViewById(R.id.primeiroE).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.segundoE).setOnDragListener(new MyOnDragListener(2));
        findViewById(R.id.terceiroE).setOnDragListener(new MyOnDragListener(3));
        findViewById(R.id.quartoE).setOnDragListener(new MyOnDragListener(4));

        findViewById(R.id.primeiroD).setOnDragListener(new MyOnDragListener(5));
        findViewById(R.id.segundoD).setOnDragListener(new MyOnDragListener(6));
        findViewById(R.id.terceiroD).setOnDragListener(new MyOnDragListener(7));
        findViewById(R.id.quartoD).setOnDragListener(new MyOnDragListener(8));
    }

    class MyOnLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            //DragShadowBuilder sb = new View.DragShadowBuilder(findViewById(R.id.shadow));
            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);
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
                case DragEvent.ACTION_DRAG_ENDED:
                    //qualquer evento de drag que acabar vai setar a figura para visível, independente do lugar que a figura cair
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DROP:
                    Log.i("Script", num + " - ACTION_DROP");
                    // Move a imagem de um container para outro (6 linhas abaixo)
                    // indice onde foi largada a imagem
                    int index = 0, numCentral = 0;
                    boolean ladoE = false, ladoD = false;
                    String tagV = "";
                    ImageView imageCenter = null;
                    if (v.getId() == R.id.primeiroE) { // Clicou na primeira imagem e largou na view da esquerda
                        ladoE = true;
                        imageCenter = (ImageView)findViewById(R.id.colCNum1);
                        tagV = imageCenter.getTag().toString();
                    } else if (v.getId() == R.id.segundoE) { // Clicou na segunda imagem e largou na view da esquerda
                        ladoE = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum2);
                        tagV = imageCenter.getTag().toString();
                    } else if (v.getId() == R.id.terceiroE) { // Clicou na terceira imagem e largou na view da esquerda
                        ladoE = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum3);
                        tagV = imageCenter.getTag().toString();
                    } else if (v.getId() == R.id.quartoE) { // Clicou na quarta imagem e largou na view da esquerda
                        ladoE = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum4);
                        tagV = imageCenter.getTag().toString();
                    }
                    else if(v.getId() == R.id.primeiroD){ // clicou na primeira e largou na view da direita
                        ladoD = true;
                        imageCenter = (ImageView)findViewById(R.id.colCNum1);
                        tagV = imageCenter.getTag().toString();
                    }else if(v.getId() == R.id.segundoD){ // clicou na segunda e largou na view da direita
                        ladoD = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum2);
                        tagV = imageCenter.getTag().toString();
                    }else if(v.getId() == R.id.terceiroD){ // clicou na terceira e largou na view da direita
                        ladoD = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum3);
                        tagV = imageCenter.getTag().toString();
                    }else if(v.getId() == R.id.quartoD){ // clicou na quarta e largou na view da direita
                        ladoD = true;
                        imageCenter = (ImageView)findViewById(R.id.ColCNum4);
                        tagV = imageCenter.getTag().toString();
                    }
                    String tagId = view.getTag().toString();
                    numCentral = Metodos.getNumberForDrawable(Integer.parseInt(tagV), fase);
                    if(tagId.equals("10")){
                        tagId = Integer.toString(R.drawable.numerodez);
                    }
                    if(tagId.equals("8")){
                        tagId = Integer.toString(R.drawable.numerooito);
                    }
                    // numero que foi movido pelo usuario
                    int numMovido = Metodos.getNumberForDrawable(Integer.parseInt(tagId),fase);
                    boolean acertou = false;
                    // verifica se a imagem foi largada no lado esquerdo e chama o metodo é antecessor
                    if(ladoE){
                        acertou = Metodos.ehAntecessor(numCentral, numMovido);
                    }else if(ladoD){
                        acertou = Metodos.ehSucessor(numCentral, numMovido);
                    }
                    if (acertou) {
                        // chama o som do numero correspondente
                        Metodos.sound(Integer.parseInt(tagId), JogoDosVizinhosActivity.this);
                        acerto++;
                        // faz o somatório
                        int total = Metodos.somaTotal(pontuacao, PONTO_ACERTO, true);
                        setPontuacao(total);
                        Toast.makeText(JogoDosVizinhosActivity.this, "VOCÊ ACERTOU!", Toast.LENGTH_SHORT).show();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    } else {
                        // mensagem de erro para o usuário
                        Toast.makeText(JogoDosVizinhosActivity.this, "OPA! VIZINHO ERRADO!", Toast.LENGTH_SHORT).show();
                        int total = Metodos.somaTotal(pontuacao,PONTO_ERRO, false);
                        setPontuacao(total);
                    }
                    view.setVisibility(View.VISIBLE);
                    if (acerto == TOTAL_ACERTO) {
                        // fala a palavra correta
                        if(getFase() < TOTAL_FASE - 1) {
                            dialogMessageResult(true, JogoDosVizinhosActivity.this);
                        }else{
                            dialogMessageResultFinal(true, JogoDosVizinhosActivity.this);
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
                pontuacao = 0;
                setPontuacao(pontuacao);
                Intent nextActivity = new Intent(getBaseContext(), JogoDosVizinhosActivity.class);
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

        AlertDialog alert = start_dialog.create();
        alert.show();
    }
}
