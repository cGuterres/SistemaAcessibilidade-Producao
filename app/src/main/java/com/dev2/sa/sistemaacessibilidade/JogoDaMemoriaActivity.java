package com.dev2.sa.sistemaacessibilidade;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class JogoDaMemoriaActivity extends AppCompatActivity implements View.OnClickListener {

    private int acerto = 0;
    private final int PONTO_ACERTO = 10;
    private final int PONTO_ERRO = 5;
    private int TOTAL_ACERTO = 6;
    private int pontuacao = 0;
    private int fase = 0;
    private final int TOTAL_FASE = 2;

    private int numeroTotal;
    private JogaMemoria[] botoes;

    private int[] buttonGraphiLocations;
    private int[] buttonGraphics;

    private JogaMemoria btn1;
    private JogaMemoria btn2;
    private boolean jogando = false;

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
        setContentView(R.layout.activity_jogo_da_memoria);

        Intent intent = getIntent();
        if(intent != null) {
            int nextPhase = intent.getIntExtra("fase", 0);
            if(nextPhase <= 3) {
                setFase(nextPhase);
            }
            int pontuacaoAtual = intent.getIntExtra("pontuacao", 0);
            setPontuacao(pontuacaoAtual);

            /*TextView txtPonto = (TextView)findViewById(R.id.txtPonto);
            if(txtPonto != null) {
                txtPonto.setText(Integer.toString(getPontuacao()));
            }*/
        }

        GridLayout grid = (GridLayout)findViewById(R.id.grid_layout);
        int numColunas = grid.getColumnCount();
        int numLinhas = 3;
        if(getFase() == 1){
            numLinhas = 4;
            TOTAL_ACERTO = 8;
        }

        numeroTotal = numColunas * numLinhas;
        botoes = new JogaMemoria[numeroTotal];

        buttonGraphics = new int[numeroTotal / 2];

        // adiciona as imagens de cada fase
        setImagemFase(buttonGraphics, getFase());

        // cria um array com o tamanho total de opções
        buttonGraphiLocations = new int[numeroTotal];

        embaralharImagens(getFase());
        // cria os botoes de acordo com a quantidade total de imagens
        for(int l = 0; l < numLinhas; l++){
            for(int c = 0; c < numColunas; c++){
                // calculo para pegar o indice de um a um (linha*coluna)
                int index = l * numColunas + c;
                JogaMemoria obj = new JogaMemoria(this, l, c, buttonGraphics[buttonGraphiLocations[index]], getFase());
                obj.setId(View.generateViewId());
                // seta o botão como clicavel
                obj.setOnClickListener(this);
                //adiciona o botão(imagem) na tela
                botoes[index] = obj;
                grid.addView(obj);
            }
        }

    }

    private void setImagemFase(final int[] buttonGraphics, int fase){
        // adiciona no array de imagens dos animais de cada fase
        if(fase == 0){
            buttonGraphics[0] = R.drawable.rato;
            buttonGraphics[1] = R.drawable.tartaruga;
            buttonGraphics[2] = R.drawable.cachorro;
            buttonGraphics[3] = R.drawable.girafa;
            buttonGraphics[4] = R.drawable.tigre;
            buttonGraphics[5] = R.drawable.elefante;
        }else if (fase == 1){
            buttonGraphics[0] = R.drawable.zebra;
            buttonGraphics[1] = R.drawable.vaca;
            buttonGraphics[2] = R.drawable.porcoquinho;
            buttonGraphics[3] = R.drawable.leao;
            buttonGraphics[4] = R.drawable.jacare;
            buttonGraphics[5] = R.drawable.gato;
            buttonGraphics[6] = R.drawable.rato;
            buttonGraphics[7] = R.drawable.tartaruga;
        }
    }

    private void embaralharImagens(int fase){
        Random r = new Random();
        int value = 12;
        if(fase == 1){
            value = 16;
        }
        // laço para adicionar duas vezes o mesmo indice no vetor de localizações
        for(int i = 0; i < numeroTotal; i++){
            buttonGraphiLocations[i] = i % (numeroTotal / 2);
        }

        // laço que embaralha o array de imagens...
        for(int i = 0; i < numeroTotal; i++){
            int aux = buttonGraphiLocations[i];
            int numeroSorte = r.nextInt(value);
            // na posição de i altera para o numero sorteade de acordo com o tamanho da fase
            buttonGraphiLocations[i] = buttonGraphiLocations[numeroSorte];
            buttonGraphiLocations[numeroSorte] = aux;
        }
    }

    @Override
    public void onClick(View view) {
        // se tem alguem jogando nao faz nada
        if(jogando)
            return;

        // cast da view com o botão que foi clicado
        JogaMemoria botao = (JogaMemoria)view;
        if(botao.isDesvirada())
            return;

        // btn1 = jogador 1.. se tá null pode jogar
        if(btn1 == null){
            btn1 = botao;
            btn1.girar();
            return;
        }

        if(btn1.getId() == botao.getId()){
            return;
        }

        // se a imagem do btn for igual a imagem da view clicada
        if(btn1.getImageDrawableId() == botao.getImageDrawableId()){
            // gira a imagem
            botao.girar();

            // seta as duas imagens como desviradas
            botao.setDesvirada(true);
            btn1.setDesvirada(true);

            // seta as imagens para nao ser mais mexidas
            btn1.setEnabled(false);
            botao.setEnabled(false);

            // retorna o btn1 para null, para seguir para a proxima jogada
            btn1 = null;
            acerto++;
            int total = Metodos.somaTotal(pontuacao, PONTO_ACERTO, true);
            setPontuacao(total);
            Toast.makeText(JogoDaMemoriaActivity.this, "VOCÊ ACERTOU!", Toast.LENGTH_SHORT).show();

            if(acerto == TOTAL_ACERTO){
                if(getFase() < TOTAL_FASE - 1) {
                    ShowDialogNext(JogoDaMemoriaActivity.this, R.drawable.icopala, "PARABÉNS!", "VOCÊ GANHOU!!");
                }else{
                    ShowDialogRecreateGame(JogoDaMemoriaActivity.this, R.drawable.icogato, "PARABÉNS!", "VOCÊ CONCLUIU O JOGO DA MEMÓRIA!\n SUA PONTUAÇÃO: " + getPontuacao());
                }
            }
            return;
        }else{
            //caso o usuário erre..
            int total = Metodos.somaTotal(pontuacao,PONTO_ERRO, false);
            setPontuacao(total);
            Toast.makeText(JogoDaMemoriaActivity.this, "OPS!! VOCÊ ERROU!!", Toast.LENGTH_SHORT).show();
            btn2 = botao;
            btn2.girar();
            jogando = true;

            // thread de 1 segundo para deixar as duas imagens desviradas (erradas)
            Thread t = new Thread(){
                @Override
                public void run(){
                    try{

                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    btn2.girar();
                                    btn1.girar();
                                    btn1 = null;
                                    btn2 = null;
                                    jogando = false;
                                }catch (Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                        });
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            };
            t.start();
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
                        if(getFase() <= TOTAL_FASE - 1) {
                            Intent nextActivity = new Intent(getBaseContext(), JogoDaMemoriaActivity.class);
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
                .setPositiveButton("REINICIAR JOGO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        pontuacao = 0;
                        setPontuacao(pontuacao);
                        Intent nextActivity = new Intent(getBaseContext(), JogoDaMemoriaActivity.class);
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
