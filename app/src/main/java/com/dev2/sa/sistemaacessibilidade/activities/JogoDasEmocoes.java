package com.dev2.sa.sistemaacessibilidade.activities;

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

import com.dev2.sa.sistemaacessibilidade.utils.Metodos;
import com.dev2.sa.sistemaacessibilidade.R;

public class JogoDasEmocoes extends AppCompatActivity {

    private final int PONTO_ACERTO = 10;
    private final int PONTO_ERRO = 5;
    private final int TOTAL_ACERTO = 4;
    private final int TOTAL_FASE = 3;
    private int acerto = 0,fase = 0;
    private int pontuacao = 0;
    private ImageView img;
    private String type = "";
    private String palavraSorteada = "";

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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getPalavraSorteada() {
        return palavraSorteada;
    }

    public void setPalavraSorteada(String palavraSorteada) {
        this.palavraSorteada = palavraSorteada;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_das_emocoes);

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
        String type = getTypeInicio();
        setType(type);
        String palavra = sorteiaPalavra(type);
        setPalavraSorteada(palavra);
        TextView txtPonto = (TextView)findViewById(R.id.txtPalavra);
        if(txtPonto != null) {
            txtPonto.setText(palavra);
        }
        ImageView img4 = (ImageView)findViewById(R.id.imageView4);
        if(type.equals("f")){
            img4.setImageResource(R.drawable.corpomenina);
        }else{
            img4.setImageResource(R.drawable.corpomenino);
        }
        setTags(getFase(), type, palavra);
    }

    private String sorteiaPalavra(String type) {
        int max = 0, min = 0;
        max = type.equals("m") ? 9 : 5;
        int numeroSorteado = Metodos.randomNumber(max, min);
        if(type.equals("f")){
            return Metodos.emocoesMenina[numeroSorteado];
        }else{
            return Metodos.emocoesMenino[numeroSorteado];
        }
    }

    private String getTypeInicio(){
        int numeroSorteado = Metodos.randomNumber(1, 0);
        if(numeroSorteado == 0){
            return "f";
        }else{
            return "m";
        }
    }

    private void setTags(int fase, String type, String palavra){
        int count = 0;
        if(type.equals("f")){
            count = 5;
        }else{
            count = 9;
        }
        int[] vetor = new int[4];
        // troca o valor para que nao seja validado de forma errada quando o vetor for iniciado com 0
        int max = count, min = 0;
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = 100;
        }
        for (int i = 0; i < vetor.length; i++) {
            int numeroSorteado = Metodos.randomNumber(max, min);
            boolean valida = Metodos.validaNumero(vetor, numeroSorteado);
            if (valida) {
                vetor[i] = numeroSorteado;
            } else {
                boolean encontrou = false;
                while (!encontrou) {
                    numeroSorteado = Metodos.randomNumber(max, min);
                    valida = Metodos.validaNumero(vetor, numeroSorteado);
                    if (valida) {
                        vetor[i] = numeroSorteado;
                        encontrou = true;
                    }
                }
            }
        }
        int posicaoRosto = getRosto(palavra, type);
        boolean tem = false;
        for(int i = 0; i < vetor.length; i++){
            if(vetor[i] == posicaoRosto){
                tem = true;
                break;
            }
        }

        if(!tem){
            int newRandom = Metodos.randomNumber(3, min);
            vetor[newRandom] = posicaoRosto;
        }

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num1);
        img.setImageResource(Metodos.setDrawableEmotion(vetor[0], type));
        img.setTag(Metodos.setDrawableEmotion(vetor[0], type));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num2);
        img.setImageResource(Metodos.setDrawableEmotion(vetor[1], type));
        img.setTag(Metodos.setDrawableEmotion(vetor[1], type));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num3);
        img.setImageResource(Metodos.setDrawableEmotion(vetor[2], type));
        img.setTag(Metodos.setDrawableEmotion(vetor[2], type));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.num4);
        img.setImageResource(Metodos.setDrawableEmotion(vetor[3], type));
        img.setTag(Metodos.setDrawableEmotion(vetor[3], type));
    }

    public int getRosto(String palavra, String type){
        if(type.equals("f")){
            switch (palavra){
                case "Feliz":
                    return 0;
                case "Má":
                    return 1;
                case "Brava":
                    return 2;
                case "Medo":
                    return  3;
                case "Raiva":
                    return  4;
                case "Triste":
                    return 5;
            }
        }else{
            switch (palavra){
                case "Assustado":
                    return 0;
                case "Bravo":
                    return 1;
                case "Chorando":
                    return 2;
                case "Feliz":
                    return  3;
                case "Medo":
                    return  4;
                case "Orando":
                    return 5;
                case "Raiva":
                    return  6;
                case "Sorrindo":
                    return 7;
                case "Vergonha":
                    return 8;
                case "Triste":
                    return 9;
            }
        }
        return 0;
    }

    // cria elementos que podem ser mexidos no layout
    private void createElements(){
        findViewById(R.id.num1).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num2).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num3).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.num4).setOnLongClickListener(new MyOnLongClickListener());

        findViewById(R.id.lnlCenter).setOnDragListener(new MyOnDragListener(1));
        ImageView imgCenter = (ImageView)findViewById(R.id.imgCenter);
        if(imgCenter != null){
            imgCenter.setVisibility(View.GONE);
        }
    }

    class MyOnLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);
            // seta a visibilidade de quem está sendo movido para invisível
            v.setVisibility(View.INVISIBLE);
            v.startDrag(data, sb, v, 0);
            //
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

                    String tagId = view.getTag().toString();
                    String rostoMovido = Metodos.getPalavraRosto(Integer.parseInt(tagId), getType());

                    boolean acertou = false;
                    if(rostoMovido.equals(getPalavraSorteada())){
                        acertou = true;
                    }
                    if (acertou) {
                        acerto++;
                        int rawId = Metodos.getSomEmocao(rostoMovido, getType());
                        Metodos.defaultSound(rawId, JogoDasEmocoes.this);
                        // faz o somatório
                        int total = Metodos.somaTotal(pontuacao, PONTO_ACERTO, true);
                        setPontuacao(total);
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    } else {
                        // mensagem de erro para o usuário
                        Toast.makeText(JogoDasEmocoes.this, "EMOÇÃO ERRADA!", Toast.LENGTH_SHORT).show();
                        int total = Metodos.somaTotal(pontuacao,PONTO_ERRO, false);
                        setPontuacao(total);
                    }
                    //volta com a visibilidade de quem está sendo movido para voltar a qualquer lugar
                    view.setVisibility(View.VISIBLE);
                    if(acertou) {
                        ShowDialogNext(JogoDasEmocoes.this, R.drawable.icotrofeu, "PARABÉNS!!", "VOÇÊ GANHOU!!");
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
                .setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent nextActivity = new Intent(getBaseContext(), JogoDasEmocoes.class);
                        nextActivity.putExtra("pontuacao", getPontuacao());
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