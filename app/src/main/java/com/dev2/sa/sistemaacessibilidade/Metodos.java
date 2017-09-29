package com.dev2.sa.sistemaacessibilidade;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Christian on 26/09/2017.
 */

public class Metodos {
    // Define a variável responsável pela execução do áudio do c.
    public static MediaPlayer toque;

    // palavras do jogo
    public static String[] palavras = {"CASA", "MALA", "UVA"};

    // vetor com os ids das drawables
    public  static int[] imagensCentro = {R.drawable.casinha, R.drawable.mala, R.drawable.cadeira, R.drawable.uva};

    // alfabeto para utilização nos jogos
    public static String[] letras = {"A", "B", "C", "D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W", "X","Y","Z"};

    // Retorna a ImageView da respectiva posição da lista (Imagem do letra).
    public static int getImagem(char tmpLetra, Context context){
        String letra = Character.toString(tmpLetra);
        int imagem = context.getResources().getIdentifier("letra" + letra.toLowerCase(), "drawable", context.getPackageName());
        return imagem;
    }

    // Retorna a ImageView da respectiva posição da lista (Imagem do letra).
    public static int getImagemMoldura(String letra, Context context){
        int imagem = context.getResources().getIdentifier(letra, "drawable", context.getPackageName());
        return imagem;
    }

    // Retorna o DRAWABLE (imagem) pelo nome do letra.
    public static int getNome(String letra, Context context){
        int imagem = context.getResources().getIdentifier(letra, "drawable", context.getPackageName());
        return imagem;
    }

    // Retorna o RAW (áudio) pelo nome do letra.
    public static int getSom(String letra, Context context){
        int audio = context.getResources().getIdentifier(letra, "raw", context.getPackageName());
        return audio;
    }

    // Retorna um valor aleatório dentro do limite.
    public static int sortearNumero(int limite){
        Random valor = new Random();
        int aleatorio = valor.nextInt(limite);
        return aleatorio;
    }

    // sorteia a palavra para iniciar o jogo
    public static String sorteiaPalavraJogo(){
        Random random = new Random();
        int valor = random.nextInt(palavras.length);
        //
        return palavras[valor];
    }

    // Método usado para executar áudio dos letra e do toque dos botões.
    public static void chamarSomletra(String letra, Context context) {
        pararSomletra();
        int audio = Metodos.getSom(letra, context);
        toque = MediaPlayer.create(context, audio);
        toque.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
                toque.stop();
                toque.release();
                toque = null;
            }
        });
        toque.start();
    }

    // Método: Parar o som caso esteja em execução.
    public static void pararSomletra() {
        if (toque != null && toque.isPlaying())
            toque.stop();
    }

    // Remove um elemento do array e retorna o próprio array.
    public static String[] removerDaLista(String[] objetosAux, String letra) {
        List<String> list = new ArrayList<String>(Arrays.asList(objetosAux));
        list.remove(letra);
        objetosAux = list.toArray(new String[0]);
        return objetosAux;
    }

    public static String sorteiraPalavraJogo(int index){
        return palavras[index];
    }
}
