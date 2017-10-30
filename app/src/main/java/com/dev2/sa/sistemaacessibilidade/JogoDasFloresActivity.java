package com.dev2.sa.sistemaacessibilidade;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class JogoDasFloresActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_das_flores);

        // Pego o elemento flor e seto como um clicavel e declaro como um objeto
        findViewById(R.id.flor_azul).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.flor_laranja).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.flor_verde).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.flor_azul2);
        findViewById(R.id.flor_laranja2);
        findViewById(R.id.flor_verde);

        // Pego o layout e seto como um drag e declaro como um objeto
        findViewById(R.id.topleft).setOnDragListener(new MyOnDragListener(1)); // flor azul
        findViewById(R.id.topright).setOnDragListener(new MyOnDragListener(2));// flor verde 2
        findViewById(R.id.centerleft).setOnDragListener(new MyOnDragListener(3)); // flor laranja
        findViewById(R.id.centerrigth).setOnDragListener(new MyOnDragListener(4));// flor azul 2
        findViewById(R.id.bottomleft).setOnDragListener(new MyOnDragListener(5)); // flor verde
        findViewById(R.id.bottomright).setOnDragListener(new MyOnDragListener(6));// flor azul 2

    }

    class MyOnLongClickListener implements OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            //DragShadowBuilder sb = new View.DragShadowBuilder(findViewById(R.id.shadow));
            DragShadowBuilder sb = new DragShadowBuilder(v);
            v.startDrag(data, sb, v, 0);
            // Esconde a imagem quando for arrastar a sua sombra
            //v.setVisibility(View.INVISIBLE);
            return(true);
        }
    }

    class MyOnDragListener implements OnDragListener {
        private int num;
        private int pontos = 0;

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

                        if(view.getId() == R.id.flor_laranja && v.getId() == R.id.bottomright){
                            hit = true;
                            // comentario
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newParent = (LinearLayout)v;
                            view.setEnabled(false);
                            newParent.addView(view);
                            pontos++;
                        }
                        else if(view.getId() == R.id.flor_verde && v.getId() == R.id.topright){
                            hit=true;
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newParent = (LinearLayout)v;
                            view.setEnabled(false);
                            newParent.addView(view);
                            pontos++;
                        }
                        else if(view.getId() == R.id.flor_azul && v.getId() == R.id.centerrigth){
                            hit = true;
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newParent = (LinearLayout)v;
                            newParent.addView(view);
                            view.setEnabled(false);
                            pontos++;
                        }
                        else{
                            Toast.makeText(JogoDasFloresActivity.this, "VOCÊ ERROU!", Toast.LENGTH_SHORT).show();
                        }
                        if(pontos <= 2 && hit) {
                            Metodos.ShowHitMessage(JogoDasFloresActivity.this, "VOCÊ ACERTOU!");
                        }
                        if(pontos == 3) {
                            Metodos.ShowDialog(JogoDasFloresActivity.this, R.drawable.flor_verde, "PARABÉNS", "VOCÊ GANHOU!");
                        }
                        hit = false;
                            break;
                }
            return true;
            }

        }

    }


