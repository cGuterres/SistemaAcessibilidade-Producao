package com.dev2.sa.sistemaacessibilidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
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

public class JogoDasPasActivity extends Activity {
    View pa_amarela_view, balde_view;
    LinearLayout pa_layout, balde_layout;
    HashMap<String, String> Balde_Pa = new HashMap<>(14,1);
    int pontos = 0;
    int controle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_das_pas);

        findViewById(R.id.pa_amarela).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.pa_vermelha).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.pa_azul).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.balde_azul);
        findViewById(R.id.balde_vermelho);
        findViewById(R.id.balde_amarelo);

        findViewById(R.id.topleft).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.topright).setOnDragListener(new MyOnDragListener(2));
        findViewById(R.id.centerleft).setOnDragListener(new MyOnDragListener(3));
        findViewById(R.id.centerrigth).setOnDragListener(new MyOnDragListener(4));
        findViewById(R.id.bottomleft).setOnDragListener(new MyOnDragListener(5));
        findViewById(R.id.bottomright).setOnDragListener(new MyOnDragListener(6));

        Balde_Pa.put(String.valueOf(R.id.pa_amarela), String.valueOf(R.id.centerrigth));
        Balde_Pa.put(String.valueOf(R.id.pa_azul), String.valueOf(R.id.topright));
        Balde_Pa.put(String.valueOf(R.id.pa_vermelha), String.valueOf(R.id.bottomright));

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
                    case DragEvent.ACTION_DRAG_STARTED:
                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                            return (true);
                        }
                        return (false);
                    case DragEvent.ACTION_DRAG_ENTERED:

                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:

                        break;
                    case DragEvent.ACTION_DRAG_EXITED:

                        break;

                    case DragEvent.ACTION_DROP:
                        if(view.getId() == R.id.pa_amarela && v.getId() == R.id.centerrigth){
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            hit = true;
                            oldparent.removeView(view);
                            LinearLayout newParent = (LinearLayout)v;
                            newParent.addView(view);
                            pontos++;
                        }
                        else if(view.getId() == R.id.pa_vermelha && v.getId() == R.id.bottomright){
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            hit = true;
                            oldparent.removeView(view);
                            LinearLayout newParent = (LinearLayout)v;
                            newParent.addView(view);
                            pontos++;
                        }
                        else if(view.getId() == R.id.pa_azul && v.getId() == R.id.topright){
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            hit = true;
                            oldparent.removeView(view);
                            LinearLayout newParent = (LinearLayout)v;
                            newParent.addView(view);
                            pontos++;
                        }
                        else{
                            Toast.makeText(JogoDasPasActivity.this, "Você errou!", Toast.LENGTH_SHORT).show();
                        }
                        if(pontos <= 2 && hit) {
                            Metodos.ShowHitMessage(JogoDasPasActivity.this, "Acerto!");
                        }
                        if(pontos == 3) {
                            Metodos.ShowDialog(JogoDasPasActivity.this, R.drawable.pa_amarela, "Vitória", "Parabéns! Você ganhou!");
                        }
                        hit = false;
                        break;
                }
            return true;
            }
        }
    }


