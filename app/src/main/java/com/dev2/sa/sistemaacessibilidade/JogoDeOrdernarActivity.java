package com.dev2.sa.sistemaacessibilidade;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class JogoDeOrdernarActivity extends Activity  {

    int pontos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_de_ordernar);

        findViewById(R.id.imagem_op1).setOnLongClickListener(new MyOnLongClickListener());
        /*findViewById(R.id.imagem_op2).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.imagem_op3).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.imagem_op4).setOnLongClickListener(new MyOnLongClickListener());*/

        findViewById(R.id.leyout_direito1);
        /*findViewById(R.id.leyout_direito);
        findViewById(R.id.leyout_esquerdo);
        findViewById(R.id.leyout_esquerdo1);*/



        //findViewById(R.id.leyout_direito).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.leyout_direito1).setOnDragListener(new MyOnDragListener(2));
        //findViewById(R.id.leyout_esquerdo).setOnDragListener(new MyOnDragListener(3));
        //findViewById(R.id.leyout_esquerdo1).setOnDragListener(new MyOnDragListener(4));
    }


    class MyOnLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            //DragShadowBuilder sb = new View.DragShadowBuilder(findViewById(R.id.shadow));
            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);
            v.startDrag(data, sb, v, 0);
            // Esconde a imagem quando for arrastar a sua sombra
            // v.setVisibility(View.INVISIBLE);
            return (true);
        }
    }

    // View 'v' é o parâmetro onde o objeto está sendo largado
    class MyOnDragListener implements View.OnDragListener {
        private int num;

        // Construtor
        public MyOnDragListener(int num) {
            super();
            this.num = num;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // Log para entender o funcionamento (Android Monitor)
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return (true);
                    }
                    return (false);
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundColor(Color.YELLOW);
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundColor(Color.BLUE);
                    break;
                case DragEvent.ACTION_DROP:
                    // Move a imagem de um container para outro (6 linhas abaixo)
                    View view = (View) event.getLocalState();//aqui entra quem está sendo movido
                    if (view.getId() == R.id.imagem_op1 && v.getId() == R.id.leyout_direito1) {
                        Toast.makeText(JogoDeOrdernarActivity.this, "ACERTOU!!", Toast.LENGTH_SHORT).show();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        pontos++;
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    } /*else if (view.getId() == R.id.imagem_op2 && v.getId() == R.id.leyout_esquerdo1) {
                        Toast.makeText(JogoDeOrdernarActivity.this, "ACERTOU!!!", Toast.LENGTH_SHORT).show();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        pontos++;
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    } else if (view.getId() == R.id.imagem_op3 && v.getId() == R.id.leyout_direito) {
                        Toast.makeText(JogoDeOrdernarActivity.this, "ACERTOU!!!", Toast.LENGTH_SHORT).show();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        pontos++;
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    } else if (view.getId() == R.id.imagem_op4 && v.getId() == R.id.leyout_esquerdo) {
                        Toast.makeText(JogoDeOrdernarActivity.this, "ACERTOU!!!", Toast.LENGTH_SHORT).show();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        pontos++;
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    }*/
                    if (pontos == 4) {
                        Toast.makeText(JogoDeOrdernarActivity.this, "PARABÉNS, VOCÊ GANHOU!!!", Toast.LENGTH_SHORT).show();
                        //AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        //builder.setMessage("TESTE").setPositiveButton("husahduihsaud", new  DialogInterface.OnclickListener());
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundColor(Color.BLUE);
                    break;
            }
            return true;
        }
    }
}


