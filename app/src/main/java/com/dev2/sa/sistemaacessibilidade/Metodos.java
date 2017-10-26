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
    public static String[] palavras = {"UVA", "CASA", "MALA", "PATO", "MACACO"};

    // vetor com os ids das drawables
    public static int[] imagensCentro = {R.drawable.uva, R.drawable.casinha, R.drawable.mala, R.drawable.pato, R.drawable.macaco};

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

    // retorna som correspondente a fase
    public static int getSomFase(int fase){
        switch (fase) {
            case 0:
                return R.raw.uva;
            case 1:
                return R.raw.casa;
            case 2:
                return R.raw.mala;
            case 3:
                return R.raw.pato;
            case 4:
                return R.raw.macaco;
            default:
                break;
        }
        return 0;
    }

    // Retorna o RAW (áudio) pelo nome do letra.
    public static int getSom(String letra) {
        switch (letra){
            case "A":
                return R.raw.a;
            case "B":
                return R.raw.b;
            case "C":
                return R.raw.c;
            case "D":
                return R.raw.d;
            case "E":
                return R.raw.e;
            case "F":
                return R.raw.f;
            case "G":
                return R.raw.g;
            case "H":
                return R.raw.h;
            case "I":
                return R.raw.i;
            case "J":
                return R.raw.k;
            case "K":
                return R.raw.k;
            case "L":
                return R.raw.l;
            case "M":
                return R.raw.m;
            case "N":
                return R.raw.n;
            case "O":
                return R.raw.o;
            case "P":
                return R.raw.p;
            case "Q":
                return R.raw.q;
            case "R":
                return R.raw.r;
            case "S":
                return R.raw.s;
            case "T":
                return R.raw.t;
            case "U":
                return R.raw.u;
            case "V":
                return R.raw.v;
            case "X":
                return R.raw.x;
            case "W":
                return R.raw.w;
            case "Y":
                return R.raw.y;
            case "Z":
                return R.raw.z;
            default:
                break;
        }
        return 0;
    }

    // Retorna um valor aleatório dentro do limite.
    public static int sortearNumero(int limite) {
        Random valor = new Random();
        int aleatorio = valor.nextInt(limite);
        return aleatorio;
    }

    public static int randomNumber(int max, int min){
        Random v = new Random();
        return v.nextInt((max - min) + 1) + min;
    }

    // Método usado para executar áudio dos letra e do toque dos botões.
    public static void chamarSomPalavra(int fase, Context context) {
        pararSomletra();
        int audio = Metodos.getSomFase(fase);
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

    public static void chamarSomletra(String letra, Context context) {
        //pararSomletra();
        int audio = Metodos.getSom(letra);
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

    public static int getDrawableContent(int drawableId, int fase) {

        if(fase == 0) {
            switch (drawableId) {
                case 0:
                    return R.drawable.lixo;
                case 1:
                    return R.drawable.seta;
                case 2:
                    return R.drawable.fada;
                case 3:
                    return R.drawable.olho;
                default:
                    break;
            }
        }else if(fase == 1){
            switch (drawableId) {
                case 0:
                    return R.drawable.grilo;
                case 1:
                    return R.drawable.trono;
                case 2:
                    return R.drawable.cebola;
                case 3:
                    return R.drawable.porta;
                default:
                    break;
            }
        }else{
            switch (drawableId) {
                case 0:
                    return R.drawable.colher;
                case 1:
                    return R.drawable.tomate;
                case 2:
                    return R.drawable.treno;
                case 3:
                    return R.drawable.cavalo;
                default:
                    break;
            }
        }
        return 0;
    }

    public static String getDrawableString(int drawableId, int fase){
        if(fase == 0) {
            switch (drawableId) {
                case R.drawable.lixo:
                    return "lixo";
                case R.drawable.seta:
                    return "seta";
                case R.drawable.olho:
                    return "olho";
                case R.drawable.fada:
                    return "fada";
                default:
                    break;
            }
        }else if (fase == 1){
            switch (drawableId){
                case R.drawable.grilo:
                    return "grilo";
                case R.drawable.trono:
                    return "trono";
                case R.drawable.cebola:
                    return "cebola";
                case R.drawable.porta:
                    return "porta";
                default:
                    break;
            }
        }else{
            switch (drawableId){
                case R.drawable.colher:
                    return "colher";
                case R.drawable.tomate:
                    return "tomate";
                case R.drawable.treno:
                    return "treno";
                case R.drawable.cavalo:
                    return "cavalo";
                default:
                    break;
            }
        }
        return "";
    }

    public static void ShowDialog(final Activity act, @DrawableRes int desenho, String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle(titulo)
                .setMessage(mensagem)
                .setCancelable(false)
                .setIcon(desenho)
                .setPositiveButton("REINICIAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        act.recreate();
                    }
                }).setNegativeButton("MENU", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                act.finish();
            }
        });//.setNeutralButton("Middle", new DialogInterface.OnClickListener() {
            //@Override
            //public void onClick(DialogInterface dialog, int which) {
              //  Toast.makeText(act, "Middle button clicked!", Toast.LENGTH_SHORT).show();
            //}
        //});
        builder.create().show();        // create and show the alert dialog
    }

    public static int getDrawablePhaseTwo(int index) {
        switch (index) {
                case 0:
                    return R.drawable.lixo;
                case 1:
                    return R.drawable.fada;
                case 2:
                    return R.drawable.olho;
                case 3:
                    return R.drawable.seta;
            default:
                break;
        }
        return 0;
    }

    public static int getDrawablePhaseTwo1(int index) {
        switch (index) {
            case 0:
                return R.drawable.grilo;
            case 1:
                return R.drawable.trono;
            case 2:
                return R.drawable.treno;
            case 3:
                return R.drawable.porta;
            default:
                break;
        }
        return 0;
    }

    public static int getDrawablePhaseTwo2(int index) {
        switch (index) {
            case 0:
                return R.drawable.colher;
            case 1:
                return R.drawable.tomate;
            case 2:
                return R.drawable.cebola;
            case 3:
                return R.drawable.cavalo;
            default:
                break;
        }
        return 0;
    }

    public static void ShowHitMessage(Activity act, String message){
        Toast.makeText(act, message, Toast.LENGTH_SHORT).show();
    }

    public static boolean validaNumero(int[] vetor, int value) {
        // valida as posições
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == value)
                // numero já sorteado
                return false;
        }
        // numero nao existe
        return true;
    }

    // check = true - acerto
    //check = false - erro
    public static int somaTotal(int total, int valor, boolean check){
        if(check){
            total += valor;
        }else{
            total -= valor;
            if(total < 0){
                total = 0;
            }
        }
        return total;
    }

    public static boolean ehSucessor(int central, int numero){
        return (central + 1) == numero;
    }

    public static boolean ehAntecessor(int central, int numero){
        return (central - 1) == numero;
    }

    public static int SetNumberDrawable(int number, int fase){
        if(fase == 0){
            switch (number){
                case 0:
                    return R.drawable.numerozero;
                case 1:
                    return R.drawable.numeroum;
                case 2:
                    return R.drawable.numerodois;
                case 3:
                    return R.drawable.numerotres;
                case 4:
                    return R.drawable.numeroquatro;
                case 5:
                    return R.drawable.numerocinco;
                case 6:
                    return R.drawable.numeroseis;
                case 7:
                    return R.drawable.numerosete;
                case 8:
                    return R.drawable.numerooito;
                case 9:
                    return R.drawable.numeronove;
                case 10:
                    return R.drawable.numerodez;
                default:
                    break;
            }
        }else if(fase == 1){
            switch (number){
                case 10:
                    return R.drawable.numerodez;
                case 11:
                    return R.drawable.numeroonze;
                case 12:
                    return R.drawable.numerodoze;
                case 13:
                    return R.drawable.numerotreze;
                case 14:
                    return R.drawable.numeroquatorze;
                case 15:
                    return R.drawable.numeroquinze;
                case 16:
                    return R.drawable.numerodezeseis;
                case 17:
                    return R.drawable.numerodezesete;
                case 18:
                    return R.drawable.numerodezoito;
                case 19:
                    return R.drawable.numerodezenove;
                case 20:
                    return R.drawable.numerovinte;
                case 21:
                    return R.drawable.numerovinteum;
                default:
                    break;
            }
        }else if(fase == 2){
            switch (number){
                case 20:
                    return R.drawable.numerovinte;
                case 21:
                    return R.drawable.numerovinteum;
                case 22:
                    return R.drawable.numerovintedois;
                case 23:
                    return R.drawable.numerovintetres;
                case 24:
                    return R.drawable.numerovintequatro;
                case 25:
                    return R.drawable.numerovintecinco;
                case 26:
                    return R.drawable.numerovinteseis;
                case 27:
                    return R.drawable.numerovintesete;
                case 28:
                    return R.drawable.numerovinteoito;
                case 29:
                    return R.drawable.numerovintenove;
                case 30:
                    return R.drawable.numerotrinta;
                default:
                    break;
            }
        }
        return 0;
    }

    public static int getNumberForDrawable(int drawableId, int fase){
        if(fase == 0){
            switch (drawableId){
                case R.drawable.numerozero:
                    return 0;
                case R.drawable.numeroum:
                    return 1;
                case R.drawable.numerodois:
                    return 2;
                case R.drawable.numerotres:
                    return 3;
                case R.drawable.numeroquatro:
                    return 4;
                case R.drawable.numerocinco:
                    return 5;
                case R.drawable.numeroseis:
                    return 6;
                case R.drawable.numerosete:
                    return 7;
                case R.drawable.numerooito:
                    return 8;
                case R.drawable.numeronove:
                    return 9;
                case R.drawable.numerodez:
                    return 10;
                default:
                    break;
            }
        }else if(fase == 1){
            switch (drawableId){
                case R.drawable.numerodez:
                    return 10;
                case R.drawable.numeroonze:
                    return 11;
                case R.drawable.numerodoze:
                    return 12;
                case R.drawable.numerotreze:
                    return 13;
                case R.drawable.numeroquatorze:
                    return 14;
                case R.drawable.numeroquinze:
                    return 15;
                case R.drawable.numerodezeseis:
                    return 16;
                case R.drawable.numerodezesete:
                    return 17;
                case R.drawable.numerodezoito:
                    return 18;
                case R.drawable.numerodezenove:
                    return 19;
                case R.drawable.numerovinte:
                    return 20;
                case R.drawable.numerovinteum:
                    return 21;
                default:
                    break;
            }
        }else{
            switch (drawableId){
                case R.drawable.numerovinte:
                    return 20;
                case R.drawable.numerovinteum:
                    return 21;
                case R.drawable.numerovintedois:
                    return 22;
                case R.drawable.numerovintetres:
                    return 23;
                case R.drawable.numerovintequatro:
                    return 24;
                case R.drawable.numerovintecinco:
                    return 25;
                case R.drawable.numerovinteseis:
                    return 26;
                case R.drawable.numerovintesete:
                    return 27;
                case R.drawable.numerovinteoito:
                    return 28;
                case R.drawable.numerovintenove:
                    return 29;
                case R.drawable.numerotrinta:
                    return 30;
                default:
                    break;
            }
        }
        return 0;
    }

    public static String getDrawableShovelString(int drawableId, int fase){
        if(fase == 0) {
            switch (drawableId) {
                case R.drawable.balde_amarelo:
                    return "balde_amarelo";
                case R.drawable.balde_azul:
                    return "balde_azul";
                case R.drawable.balde_vermelho:
                    return "balde_vermelho";
                default:
                    break;
            }
        }else if (fase == 1){
            switch (drawableId){
                case R.drawable.balde_laranja:
                    return "balde_laranja";
                case R.drawable.balde_rosa:
                    return "balde_rosa";
                case R.drawable.balde_roxo:
                    return "cebola";
                case R.drawable.balde_vinho:
                    return "balde_vinho";
                default:
                    break;
            }
        }
        return "";
    }

    //ajustar imagens das somas
    public static int SetNumberDrawableJogoSoma(int number, int fase) {
        if (fase == 0) {
            switch (number) {
                case 0 :
                    return R.drawable.somazero;
                case 1:
                    return R.drawable.somaum;
                case 2:
                    return R.drawable.somadois;
                case 3:
                    return R.drawable.somatres;
                case 4:
                    return R.drawable.somaquatro;
                case 5:
                    return R.drawable.somacinco;
                case 6:
                    return R.drawable.somaseis;
                case 7:
                    return R.drawable.somasete;
                case 8:
                    return R.drawable.somaoito;
                default:
                    break;
            }
        } else if (fase == 1) {
            switch (number) {
                case 0:
                    return R.drawable.somazero;
                case 1:
                    return R.drawable.somaumflor;
                case 2:
                    return R.drawable.somadoisf;
                case 3:
                    return R.drawable.somatresf;
                case 4:
                    return R.drawable.somaquatrof;
                case 5:
                    return R.drawable.somacincof;
                case 6:
                    return R.drawable.somacseisf;
                case 7:
                    return R.drawable.somacsetef;
                case 8:
                    return R.drawable.somacoitof;
                default:
                    break;
            }
        } else if (fase == 2) {
            switch (number) {
                case 0:
                    return R.drawable.somazero;
                case 1:
                    return R.drawable.somaumg;
                case 2:
                    return R.drawable.somadoisg;
                case 3:
                    return R.drawable.somatresg;
                case 4:
                    return R.drawable.somaquatrog;
                case 5:
                    return R.drawable.somacincog;
                case 6:
                    return R.drawable.somaseisg;
                case 7:
                    return R.drawable.somaseteg;

                case 8:
                    return R.drawable.somaoitog;
                default:
                    break;
            }
        }
        return 0;
    }

    public static int getNumberForDrawableJogoSoma(int drawableId, int fase) {
        if (fase == 0) {
            switch (drawableId) {
                case R.drawable.somazero:
                    return 0;
                case R.drawable.somaum:
                    return 1;
                case R.drawable.somadois:
                    return 2;
                case R.drawable.somatres:
                    return 3;
                case R.drawable.somaquatro:
                    return 4;
                case R.drawable.somacinco:
                    return 5;
                case R.drawable.somaseis:
                    return 6;
                case R.drawable.somasete:
                    return 7;
                case R.drawable.somaoito:
                    return 8;
                default:
                    break;
            }
        } else if (fase == 1) {
            switch (drawableId) {
                case R.drawable.somazero:
                    return 0;
                case R.drawable.somaumflor:
                    return 1;
                case R.drawable.somadoisf:
                    return 2;
                case R.drawable.somatresf:
                    return 3;
                case R.drawable.somaquatrof:
                    return 4;
                case R.drawable.somacincof:
                    return 5;
                case R.drawable.somacseisf:
                    return 6;
                case R.drawable.somacsetef:
                    return 7;
                case R.drawable.somacoitof:
                    return 8;
                default:
                    break;
            }
        } else if (fase == 2) {
            switch (drawableId) {
                case R.drawable.somazero:
                    return 0;
                case R.drawable.somaumg:
                    return 1;
                case R.drawable.somadoisg:
                    return 2;
                case R.drawable.somatresg:
                    return 3;
                case R.drawable.somaquatrog:
                    return 4;
                case R.drawable.somacincog:
                    return 5;
                case R.drawable.somaseisg:
                    return 6;
                case R.drawable.somaseteg:
                    return 7;
                case R.drawable.somaoitog:
                    return 8;
                default:
                    break;
            }
        }
        return 0;
    }

    public static int getResultDrawable(int drawableId){
        switch (drawableId){
            case R.drawable.numerozero:
                return 0;
            case R.drawable.somaum:
                return 1;
            case R.drawable.somadois:
                return 2;
            case R.drawable.somatres:
                return 3;
            case R.drawable.somaquatro:
                return 4;
            case R.drawable.somacinco:
                return 5;
            case R.drawable.somaseis:
                return 6;
            case R.drawable.somasete:
                return 7;
            case R.drawable.somaoito:
                return 8;
            default:
                break;
        }
        return 0;
    }
}
