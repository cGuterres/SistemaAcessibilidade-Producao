package com.dev2.sa.sistemaacessibilidade;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jmedeisis.draglinearlayout.DragLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class Jogo_de_Ordenar_Palavra2 extends AppCompatActivity {

    private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    private final int lixoId = 2131493012;
    private final int fadaId = 2131493014;
    private final int olhoId = 2131493016;
    private final int setaId = 2131493018;

    public Integer getLixoId() {
        return lixoId;
    }

    public int getFadaId() {
        return fadaId;
    }

    public int getSetaId() {
        return setaId;
    }

    public int getOlhoId() {
        return olhoId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_de__ordenar__palavra2);

        /*findViewById(R.id.palavralixo).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.palavrafada).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.palavraolho).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.palavraseta).setOnTouchListener(new MyTouchListener());

        findViewById(R.id.moldura1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.moldura2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.moldura3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.moldura4).setOnTouchListener(new MyTouchListener());

        /*DragLinearLayout menujogo = (DragLinearLayout) findViewById(R.id.drag_drop_layout);
        for (int i = 0; i < menujogo.getChildCount(); i++) {
            View child = menujogo.getChildAt(i);
            menujogo.setViewDraggable(child, child);
        }

        DragLinearLayout menujogo = (DragLinearLayout) findViewById(R.id.drag_drop_layout);
        ArrayList<View> lst = new ArrayList<View>();
        int[] vetor = new int[menujogo.getChildCount()];
        // troca o valor para que nao seja validado de forma errada quando o vetor for iniciado com 0
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = 9;
        }
        for (int i = 0; i < menujogo.getChildCount(); i++) {
            int numeroSorteado = Metodos.sortearNumero(menujogo.getChildCount());
            boolean valida = Metodos.validaNumero(vetor, numeroSorteado);
            if (valida) {
                vetor[i] = numeroSorteado;
            } else {
                boolean encontrou = false;
                while (!encontrou) {
                    numeroSorteado = Metodos.sortearNumero(menujogo.getChildCount());
                    valida = Metodos.validaNumero(vetor, numeroSorteado);
                    if (valida) {
                        vetor[i] = numeroSorteado;
                        encontrou = true;
                    }
                }
            }
            lst.add(menujogo.getChildAt(numeroSorteado));
        }

        ImageView im1 = (ImageView) findViewById(R.id.palavralixo);
        im1.setImageResource(Metodos.getDrawablePhaseTwo(vetor[0]));
        im1.setTag(Metodos.getDrawablePhaseTwo(vetor[0]));

        ImageView im2 = (ImageView) findViewById(R.id.palavrafada);
        im2.setImageResource(Metodos.getDrawablePhaseTwo(vetor[1]));
        im2.setTag(Metodos.getDrawablePhaseTwo(vetor[1]));

        ImageView im3 = (ImageView) findViewById(R.id.palavraolho);
        im3.setImageResource(Metodos.getDrawablePhaseTwo(vetor[2]));
        im3.setTag(Metodos.getDrawablePhaseTwo(vetor[2]));

        ImageView im4 = (ImageView) findViewById(R.id.palavraseta);
        im4.setImageResource(Metodos.getDrawablePhaseTwo(vetor[3]));
        im4.setTag(Metodos.getDrawablePhaseTwo(vetor[3]));

        for (View v : lst) {
            menujogo.setViewDraggable(v, v);
    }
    */
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            String tag = view.getTag().toString();
            // pega a imagem que foi mexida
            String palavra = Metodos.getDrawableContentString(Integer.parseInt(tag));
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    if (view.getId() == getLixoId() && palavra.equals("palavralixo")) {
                        Toast.makeText(Jogo_de_Ordenar_Palavra2.this, "palavralixo", Toast.LENGTH_SHORT).show();
                    } else if (view.getId() == getFadaId() && palavra.equals("palavrafada")) {
                        Toast.makeText(Jogo_de_Ordenar_Palavra2.this, "palavrafada", Toast.LENGTH_SHORT).show();
                    } else if (view.getId() == getSetaId() && palavra.equals("palavraseta")) {
                        Toast.makeText(Jogo_de_Ordenar_Palavra2.this, "palavraseta", Toast.LENGTH_SHORT).show();
                    } else if (view.getId() == getOlhoId() && palavra.equals("palavraolho")) {
                        Toast.makeText(Jogo_de_Ordenar_Palavra2.this, "palavraolho", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    }

    private ArrayList<ImageView> getImages() {
        ArrayList<ImageView> lst = new ArrayList<ImageView>();
        DragLinearLayout menujogo = (DragLinearLayout) findViewById(R.id.drag_drop_layout);
        for (int i = 0; i < menujogo.getChildCount(); i++) {
            ImageView child = (ImageView) menujogo.getChildAt(i);
            lst.add(child);
        }
        return lst;
    }

    // metodo que retorna a ordem das palavras
    private void create() {
        ArrayList<String> lst = new ArrayList<String>();
        lst.add("lixo");
        lst.add("fada");
        lst.add("olho");
        lst.add("seta");
        //
    }
}
