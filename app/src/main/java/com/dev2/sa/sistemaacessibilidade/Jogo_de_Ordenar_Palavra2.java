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

public class Jogo_de_Ordenar_Palavra2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_de__ordenar__palavra2);

        findViewById(R.id.palavralixo).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.palavrafada).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.palavraolho).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.palavraseta).setOnTouchListener(new MyTouchListener());

        findViewById(R.id.moldura1).setOnDragListener(new MyDragListener());
        findViewById(R.id.moldura2).setOnDragListener(new MyDragListener());
        findViewById(R.id.moldura3).setOnDragListener(new MyDragListener());
        findViewById(R.id.moldura4).setOnDragListener(new MyDragListener());

        /*DragLinearLayout menujogo = (DragLinearLayout) findViewById(R.id.drag_drop_layout);
        for (int i = 0; i < menujogo.getChildCount(); i++) {
            View child = menujogo.getChildAt(i);
            menujogo.setViewDraggable(child, child);
        }*/

        DragLinearLayout menujogo = (DragLinearLayout) findViewById(R.id.drag_drop_layout);
        ArrayList<View> lst = new ArrayList<View>();
        int[] vetor = new int[menujogo.getChildCount()];
        // troca o valor para que nao seja validado de forma errada quando o vetor for iniciado com 0
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = 9;
        }
        for (int i = 0; i < menujogo.getChildCount(); i++) {
            int numeroSorteado = Metodos.sortearNumero(menujogo.getChildCount());
            boolean valida = validaNumero(vetor, numeroSorteado);
            if (valida) {
                vetor[i] = numeroSorteado;
            } else {
                boolean encontrou = false;
                while (!encontrou) {
                    numeroSorteado = Metodos.sortearNumero(menujogo.getChildCount());
                    valida = validaNumero(vetor, numeroSorteado);
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

        ImageView im2 = (ImageView) findViewById(R.id.palavrafada);
        im2.setImageResource(Metodos.getDrawablePhaseTwo(vetor[1]));

        ImageView im3 = (ImageView) findViewById(R.id.palavraolho);
        im3.setImageResource(Metodos.getDrawablePhaseTwo(vetor[2]));

        ImageView im4 = (ImageView) findViewById(R.id.palavraseta);
        im4.setImageResource(Metodos.getDrawablePhaseTwo(vetor[3]));

        for(View v : lst) {
            menujogo.setViewDraggable(v, v);
        }
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                /*ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.setVisibility(View.INVISIBLE);*/
                DragLinearLayout menujogo = (DragLinearLayout) findViewById(R.id.drag_drop_layout);
                for (int i = 0; i < menujogo.getChildCount(); i++) {
                    View child = menujogo.getChildAt(i);
                    menujogo.setViewDraggable(child, child);
                }

                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
        View draggedView;
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    draggedView = (View) event.getLocalState();
                    //draggedView.setVisibility(View.INVISIBLE);
                    Toast.makeText(Jogo_de_Ordenar_Palavra2.this, "ACTION_DRAG_STARTED", Toast.LENGTH_SHORT).show();

                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:

                    Toast.makeText(Jogo_de_Ordenar_Palavra2.this, "ACTION_DROP", Toast.LENGTH_SHORT).show();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
    }


    private boolean validaNumero(int[] vetor, int value) {
        // valida as posições
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == value)
                // numero já sorteado
                return false;
        }
        // numero nao existe
        return true;
    }


    private ArrayList<ImageView> getImages() {
        ArrayList<ImageView> lst = new ArrayList<ImageView>();
        DragLinearLayout menujogo = (DragLinearLayout) findViewById(R.id.drag_drop_layout);
        for (int i = 0; i < menujogo.getChildCount(); i++) {
            ImageView child = (ImageView) menujogo.getChildAt(i);
            //menujogo.setViewDraggable(child, child);
            lst.add(child);
        }
        return lst;
    }

    // metodo que retorna a ordem das palavras
    private ArrayList<String> getOrdem() {
        ArrayList<String> lst = new ArrayList<String>();
        lst.add("lixo");
        lst.add("fada");
        lst.add("olho");
        lst.add("seta");
        //
        return lst;
    }
}
