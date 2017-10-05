package com.dev2.sa.sistemaacessibilidade;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


/**
 * Created by Christian on 26/09/2017.
 */

public class Metodos {
    // Define a variável responsável pela execução do áudio do c.
    public static MediaPlayer toque;

    // palavras do jogo
    public static String[] palavras = {"UVA", "CASA", "MALA", "PATO"};

    // vetor com os ids das drawables
    public static int[] imagensCentro = {R.drawable.uva, R.drawable.casinha, R.drawable.mala, R.drawable.pato};

    // alfabeto para utilização nos jogos
    public static String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    // Retorna a ImageView da respectiva posição da lista (Imagem do letra).
    public static int getImagem(char tmpLetra, Context context) {
        String letra = Character.toString(tmpLetra);
        int imagem = context.getResources().getIdentifier("letra" + letra.toLowerCase(), "drawable", context.getPackageName());
        return imagem;
    }

    // Retorna a ImageView da respectiva posição da lista (Imagem do letra).
    public static int getImagemMoldura(String letra, Context context) {
        int imagem = context.getResources().getIdentifier(letra, "drawable", context.getPackageName());
        return imagem;
    }

    // Retorna o DRAWABLE (imagem) pelo nome do letra.
    public static int getNome(String letra, Context context) {
        int imagem = context.getResources().getIdentifier(letra, "drawable", context.getPackageName());
        return imagem;
    }

    // Retorna o RAW (áudio) pelo nome do letra.
    public static int getSom(String letra, Context context) {
        int audio = context.getResources().getIdentifier(letra, "raw", context.getPackageName());
        return audio;
    }

    // Retorna um valor aleatório dentro do limite.
    public static int sortearNumero(int limite) {
        Random valor = new Random();
        int aleatorio = valor.nextInt(limite);
        return aleatorio;
    }

    // Método usado para executar áudio dos letra e do toque dos botões.
    public static void chamarSomletra(String letra, Context context) {
        pararSomletra();
        int audio = Metodos.getSom(letra, context);
        toque = MediaPlayer.create(context, audio);
        toque.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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

    // sorteia a palavra do jogo
    public static String sorteiraPalavraJogo(int index) {
        return palavras[index];
    }

    // funcao que retorna a imagem da letra
    public static int getDrawableId(char letra) {
        switch (letra) {
            case 'A':
                return R.drawable.letraa;
            case 'B':
                return R.drawable.letrab;
            case 'C':
                return R.drawable.letrac;
            case 'D':
                return R.drawable.letrad;
            case 'E':
                return R.drawable.letrae;
            case 'F':
                return R.drawable.letraf;
            case 'G':
                return R.drawable.letrag;
            case 'H':
                return R.drawable.letrah;
            case 'I':
                return R.drawable.letrai;
            case 'J':
                return R.drawable.letraj;
            case 'K':
                return R.drawable.letrak;
            case 'L':
                return R.drawable.letral;
            case 'M':
                return R.drawable.letram;
            case 'N':
                return R.drawable.letran;
            case 'O':
                return R.drawable.letrao;
            case 'P':
                return R.drawable.letrap;
            case 'Q':
                return R.drawable.letraq;
            case 'R':
                return R.drawable.letrar;
            case 'S':
                return R.drawable.letras;
            case 'T':
                return R.drawable.letrat;
            case 'U':
                return R.drawable.letrau;
            case 'V':
                return R.drawable.letrav;
            case 'X':
                return R.drawable.letrax;
            case 'W':
                return R.drawable.letraw;
            case 'Y':
                return R.drawable.letray;
            case 'Z':
                return R.drawable.letraz;
            default:
                break;
        }
        return 1;
    }

    public static char getDrawableId(int drawableId) {
        switch (drawableId) {
            case R.drawable.letraa:
                return 'A';
            case R.drawable.letrab:
                return 'B';
            case R.drawable.letrac:
                return 'C';
            case R.drawable.letrad:
                return 'D';
            case R.drawable.letrae:
                return 'E';
            case R.drawable.letraf:
                return 'F';
            case R.drawable.letrag:
                return 'G';
            case R.drawable.letrah:
                return 'H';
            case R.drawable.letrai:
                return 'I';
            case R.drawable.letraj:
                return 'J';
            case R.drawable.letrak:
                return 'K';
            case R.drawable.letral:
                return 'L';
            case R.drawable.letram:
                return 'M';
            case R.drawable.letran:
                return 'N';
            case R.drawable.letrao:
                return 'O';
            case R.drawable.letrap:
                return 'P';
            case R.drawable.letraq:
                return 'Q';
            case R.drawable.letrar:
                return 'R';
            case R.drawable.letras:
                return 'S';
            case R.drawable.letrat:
                return 'T';
            case R.drawable.letrau:
                return 'U';
            case R.drawable.letrav:
                return 'V';
            case R.drawable.letrax:
                return 'X';
            case R.drawable.letraw:
                return 'W';
            case R.drawable.letray:
                return 'Y';
            case R.drawable.letraz:
                return 'Z';
            default:
                break;
        }
        return 1;
    }

    public static void ShowDialog(final Activity act, @DrawableRes int desenho, String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle(titulo)
                .setMessage(mensagem)
                .setCancelable(false)
                .setIcon(desenho)
                .setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        act.recreate();
                    }
                }).setNegativeButton("Menu", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                act.finish();
            }
        }).setNeutralButton("Middle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(act, "Middle button clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();        // create and show the alert dialog
    }
}
