package com.dev2.sa.sistemaacessibilidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ClipData;
import android.content.ClipDescription;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
//PRIORIDADES: 1MSG DE ERRO 2RANDOM
public class JogoDasFrutasActivity extends AppCompatActivity {
    View sombraAbacaxi;
    HashMap<String, String> Balde_Pa = new HashMap<>(14,1);
    int pontos = 0;
    int controle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_das_frutas);

        findViewById(R.id.abacaxi_colorido).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.laranja_colorida).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.uva_colorida).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.abacaxi_preto);
        findViewById(R.id.uva_preta);
        findViewById(R.id.laranja_preta);


        findViewById(R.id.topleft).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.topright).setOnDragListener(new MyOnDragListener(2));//laranja preta
        findViewById(R.id.centerleft).setOnDragListener(new MyOnDragListener(3));
        findViewById(R.id.centerrigth).setOnDragListener(new MyOnDragListener(4));//abacaxi preto
        findViewById(R.id.bottomleft).setOnDragListener(new MyOnDragListener(5));
        findViewById(R.id.bottomright).setOnDragListener(new MyOnDragListener(6));//uva preta




        Balde_Pa.put(String.valueOf(R.id.uva_colorida), String.valueOf(R.id.bottomright));
        Balde_Pa.put(String.valueOf(R.id.abacaxi_colorido), String.valueOf(R.id.centerrigth));
        Balde_Pa.put(String.valueOf(R.id.laranja_colorida), String.valueOf(R.id.topright));

    }

    class MyOnLongClickListener implements OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            //DragShadowBuilder sb = new View.DragShadowBuilder(findViewById(R.id.shadow));
            DragShadowBuilder sb = new View.DragShadowBuilder(v);
            v.startDrag(data, sb, v, 0);
            // Esconde a imagem quando for arrastar a sua sombra
            //v.setVisibility(View.INVISIBLE);
            return(true);
        }
    }

    class MyOnDragListener implements OnDragListener {
        private int num;

        // Construtor
        public MyOnDragListener(int num){
            super();
            this.num = num;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            boolean hit = false;
            View view = (View) event.getLocalState();//aqui entra quem está sendo movido
                 switch (action) {

                    case DragEvent.ACTION_DROP:

                        if(view.getId() == R.id.laranja_colorida && v.getId() == R.id.topright){
                            hit = true;
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newParent = (LinearLayout)v;
                            newParent.addView(view);
                            pontos++;
                        }
                        else if(view.getId() == R.id.uva_colorida && v.getId() == R.id.bottomright){
                            hit=true;
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newParent = (LinearLayout)v;
                            newParent.addView(view);
                            pontos++;
                        }
                        else if(view.getId() == R.id.abacaxi_colorido && v.getId() == R.id.centerrigth){
                            hit = true;

                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newParent = (LinearLayout)v;
                            newParent.addView(view);
                            pontos++;
                        }
                        else{
                            Toast.makeText(JogoDasFrutasActivity.this, "Você errou!", Toast.LENGTH_SHORT).show();
                        }
                        if(pontos <= 2 && hit) {
                            Metodos.ShowHitMessage(JogoDasFrutasActivity.this, "Você acerto!");
                        }
                        if(pontos == 3) {
                            Metodos.ShowDialog(JogoDasFrutasActivity.this, R.drawable.pa_amarela, "Parabéns", "Você ganhou!");
                        }
                        hit = false;
                            break;
                }
            return true;
            }

        }

    }


