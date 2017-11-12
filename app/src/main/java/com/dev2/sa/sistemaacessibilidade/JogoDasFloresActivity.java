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
    private int pontos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_das_flores);

        // Pego o elemento flor_azul e seto como um elemento clicavel
        findViewById(R.id.flor_azul).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.flor_laranja).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.flor_verde).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.flor_azul2);
        findViewById(R.id.flor_laranja2);
        findViewById(R.id.flor_verde2);

        // Pego o layout topleft e seto como um drag
        findViewById(R.id.topleft).setOnDragListener(new MyOnDragListener(1)); // flor azul
        findViewById(R.id.topright).setOnDragListener(new MyOnDragListener(2));// flor verde 2
        findViewById(R.id.centerleft).setOnDragListener(new MyOnDragListener(3)); // flor laranja
        findViewById(R.id.centerrigth).setOnDragListener(new MyOnDragListener(4));// flor azul 2
        findViewById(R.id.bottomleft).setOnDragListener(new MyOnDragListener(5)); // flor verde
        findViewById(R.id.bottomright).setOnDragListener(new MyOnDragListener(6));// flor azul 2
    }

    // classe que implementa o click longo da imagem
    class MyOnLongClickListener implements OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            // Permite que o OnDrag receba ou não o elemento que está em drag
            ClipData data = ClipData.newPlainText("simple_text", "text");
            // sombra da imagem
            DragShadowBuilder sb = new DragShadowBuilder(v);
            // Esconde a imagem quando for arrastar a sua sombra
            v.setVisibility(View.INVISIBLE);
            v.startDrag(data, sb, v, 0);
            return(true);
        }
    }

    // Classe que implementa o metodo para arrastar a imagem
    class MyOnDragListener implements OnDragListener {
        private int num;

        // Construtor
        public MyOnDragListener(int num){
            super();
            this.num = num;
        }

        // v = MyOnDragListener
        // event = startDrag
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            boolean hit = false;
            // Cria a imagem que acessa a v (imagem principal) do startDrag
            View view = (View) event.getLocalState();
                 switch (action) {
                    // Quando tem ação de drop deixa a sombra sempre visível
                    case DragEvent.ACTION_DRAG_ENDED:
                        view.setVisibility(View.VISIBLE);
                        break;
                    // Realiza a movimentação das imagens de um layout para o outro
                    case DragEvent.ACTION_DROP:
                        if(view.getId() == R.id.flor_laranja && v.getId() == R.id.bottomright){
                            // O layout atual recebe a imagem
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            // Remove a imagem do layout atual
                            oldparent.removeView(view);
                            // Cria um layout novo
                            LinearLayout newParent = (LinearLayout)v;
                            // Adiciona a imagem no novo layout
                            newParent.addView(view);
                            // Deixa a imagem visivel
                            view.setVisibility(View.VISIBLE);
                            // Bloqueia a imagem
                            view.setEnabled(false);
                            // Acerto é verdadeiro
                            hit = true;
                            // Soma 1 ponto
                            pontos++;
                        } else if(view.getId() == R.id.flor_verde && v.getId() == R.id.topright){
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newParent = (LinearLayout)v;
                            newParent.addView(view);
                            view.setVisibility(View.VISIBLE);
                            view.setEnabled(false);
                            hit = true;
                            pontos++;
                        } else if(view.getId() == R.id.flor_azul && v.getId() == R.id.centerrigth){
                            ////////////////////////////// SOM
                            //Metodos.sound(R.drawable.flor_azul, JogoDasFloresActivity.this);
                            LinearLayout oldparent = (LinearLayout) view.getParent();
                            oldparent.removeView(view);
                            LinearLayout newParent = (LinearLayout)v;
                            newParent.addView(view);
                            view.setVisibility(View.VISIBLE);
                            view.setEnabled(false);
                            hit = true;
                            pontos++;
                        } else{
                            // Senão acertar exibe a mensagem de erro
                            Toast.makeText(JogoDasFloresActivity.this, "VOCÊ ERROU!", Toast.LENGTH_SHORT).show();
                            // Deixa a imagem visivel
                            view.setVisibility(View.VISIBLE);
                        }
                        if(pontos <= 2 && hit) {
                            // Se o número de acertos for menor ou igual a 2, exibe a mensagem de acerto
                            Metodos.ShowHitMessage(JogoDasFloresActivity.this, "VOCÊ ACERTOU!");
                        }
                        if(pontos == 3) {
                            // Se o número de acertos for igual a 3, exibe o dialog de vitoria e as opções de voltar para o menu ou reiniciar o jogo
                            Metodos.ShowDialog(JogoDasFloresActivity.this, R.drawable.flor_verde, "PARABÉNS", "VOCÊ GANHOU!");
                        }
                        break;
                }
            return true;
            }

        }

    }


