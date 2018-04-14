package com.dev2.sa.sistemaacessibilidade.activities;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dev2.sa.sistemaacessibilidade.utils.Metodos;
import com.dev2.sa.sistemaacessibilidade.R;


public class JogoDasFloresActivity2 extends Activity {
    private int pontos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_da_flores2);

        // Pego o elemento flor_azul e seto como um elemento clicavel
        findViewById(R.id.flor_rosa).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.flor_verde_limao).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.flor_roxa).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.flor_vermelha).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.flor_rosa2);
        findViewById(R.id.flor_verde_limao2);
        findViewById(R.id.flor_roxa2);
        findViewById(R.id.flor_vermelha2);

        // Pego o layout topleft e seto como um drag
        findViewById(R.id.topleft).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.topright).setOnDragListener(new MyOnDragListener(2));
        findViewById(R.id.centerleft).setOnDragListener(new MyOnDragListener(3));
        findViewById(R.id.centerrigth).setOnDragListener(new MyOnDragListener(4));
        findViewById(R.id.bottomleft).setOnDragListener(new MyOnDragListener(5));
        findViewById(R.id.bottomright).setOnDragListener(new MyOnDragListener(6));
        findViewById(R.id.finalleft).setOnDragListener(new MyOnDragListener(7));
        findViewById(R.id.finalright).setOnDragListener(new MyOnDragListener(8));
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
                    if(view.getId() == R.id.flor_rosa && v.getId() == R.id.bottomright){
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
                    } else if(view.getId() == R.id.flor_vermelha && v.getId() == R.id.finalright){
                        LinearLayout oldparent = (LinearLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent = (LinearLayout)v;
                        newParent.addView(view);
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        hit = true;
                        pontos++;
                    } else if(view.getId() == R.id.flor_verde_limao && v.getId() == R.id.centerrigth){
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
                    } else if(view.getId() == R.id.flor_roxa && v.getId() == R.id.topright){
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
                        Toast.makeText(JogoDasFloresActivity2.this, "VOCÊ ERROU!", Toast.LENGTH_SHORT).show();
                        // Deixa a imagem visivel
                        view.setVisibility(View.VISIBLE);
                    }
                    if(pontos <= 3 && hit) {
                        // Se o número de acertos for menor ou igual a 2, exibe a mensagem de acerto
                        Metodos.ShowHitMessage(JogoDasFloresActivity2.this, "VOCÊ ACERTOU!");
                        ////////////////////////////// SOM
                        Metodos.sound(R.raw.sound_success, JogoDasFloresActivity2.this);
                    }
                    if(pontos == 4) {
                        // Se o número de acertos for igual a 3, exibe o dialog de vitoria e as opções de voltar para o menu ou reiniciar o jogo
                        //Metodos.ShowDialog(JogoDasFloresActivity2.this, R.drawable.flor_verde, "PARABÉNS", "VOCÊ GANHOU!");
                        // Para chamar a proxima fase
                        ShowDialogRecreateGame(JogoDasFloresActivity2.this, R.drawable.icotrofeu, "PARABÉNS", "VOCÊ GANHOU!");
                        ////////////////////////////// SOM
                        Metodos.sound(R.raw.sound_aplausos, JogoDasFloresActivity2.this);

                    }
                    break;
            }
            return true;
        }

    }

    public void ShowDialogRecreateGame(final Activity act, @DrawableRes int desenho, String titulo, String mensagem) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(act);
        builder.setTitle(titulo)
                .setMessage(mensagem)
                .setCancelable(false)
                .setIcon(desenho)
                .setPositiveButton("REINICIAR FASES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent nextActivity = new Intent(getBaseContext(), JogoDasFloresActivity.class);
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


