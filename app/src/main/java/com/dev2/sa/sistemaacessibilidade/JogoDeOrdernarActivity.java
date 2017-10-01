package com.dev2.sa.sistemaacessibilidade;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.util.Log;
import android.view.DragEvent;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class JogoDeOrdernarActivity extends Activity {

    // imagens da tela
    ImageView img1, img2, img3, img4, img5, img6, imgCenter;
    LinearLayout ln1, ln2, ln3, ln4, ln5, ln6, lnCenter;
    int pontos = 0;
    int fase = 0;
    String palavra = "";
    char[] ordemCerta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_de_ordernar);

        findViewById(R.id.letra1).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.letra2).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.letra3).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.letra4).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.letra5).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.letra6).setOnLongClickListener(new MyOnLongClickListener());

        findViewById(R.id.top_layout);
        findViewById(R.id.top_layout1);

        //seta imagem para ordernar as palavras
        setImageCenter(fase);

        // palavra
        palavra = Metodos.sorteiraPalavraJogo(fase);
        ordemCerta = new char[palavra.length()];
        setLetrasImagem(palavra);

        findViewById(R.id.primeiro).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.segundo).setOnDragListener(new MyOnDragListener(2));
        findViewById(R.id.terceiro).setOnDragListener(new MyOnDragListener(3));
        findViewById(R.id.quarto).setOnDragListener(new MyOnDragListener(4));
        findViewById(R.id.quinto).setOnDragListener(new MyOnDragListener(5));
        findViewById(R.id.sexto).setOnDragListener(new MyOnDragListener(6));
    }

    private void setLetrasImagem(String palavra) {
        char[] array = palavra.toCharArray();

        if (array.length == 3) {

            img1 = (ImageView) findViewById(R.id.letra1);
            img1.setImageResource(Metodos.getDrawableId(array[1]));
            ordemCerta[0] = array[0];

            img2 = (ImageView) findViewById(R.id.letra2);
            img2.setImageResource(Metodos.getDrawableId(array[0]));
            ordemCerta[1] = array[1];

            img3 = (ImageView) findViewById(R.id.letra3);
            img3.setImageResource(Metodos.getDrawableId(array[2]));
            ordemCerta[2] = array[2];

            img4 = (ImageView) findViewById(R.id.letra4);
            img4.setVisibility(View.INVISIBLE);
            ln4 = (LinearLayout) findViewById(R.id.quarto);
            ln4.setVisibility(View.INVISIBLE);

            img5 = (ImageView) findViewById(R.id.letra5);
            img5.setVisibility(View.INVISIBLE);
            ln5 = (LinearLayout) findViewById(R.id.quinto);
            ln5.setVisibility(View.INVISIBLE);

            img6 = (ImageView) findViewById(R.id.letra6);
            img6.setVisibility(View.INVISIBLE);
            ln6 = (LinearLayout) findViewById(R.id.sexto);
            ln6.setVisibility(View.INVISIBLE);

            // seta configurações para o layout com 3 letras
            lnCenter = (LinearLayout) findViewById(R.id.yellowLayout);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(265, 30, 0, 30);
            lnCenter.setLayoutParams(layoutParams);

        } else if (array.length == 4) {
            img1 = (ImageView) findViewById(R.id.letra1);
            img1.setImageResource(Metodos.getDrawableId(array[1]));
            ordemCerta[0] = array[0];

            img2 = (ImageView) findViewById(R.id.letra2);
            img2.setImageResource(Metodos.getDrawableId(array[0]));
            ordemCerta[1] = array[1];

            img3 = (ImageView) findViewById(R.id.letra3);
            img3.setImageResource(Metodos.getDrawableId(array[3]));
            ordemCerta[2] = array[2];

            img4 = (ImageView) findViewById(R.id.letra4);
            img4.setImageResource(Metodos.getDrawableId(array[2]));
            ordemCerta[3] = array[3];

            lnCenter = (LinearLayout) findViewById(R.id.yellowLayout);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(160, 30, 0, 30);
            lnCenter.setLayoutParams(layoutParams);

            img5 = (ImageView) findViewById(R.id.letra5);
            img5.setVisibility(View.INVISIBLE);
            ln5 = (LinearLayout) findViewById(R.id.quinto);
            ln5.setVisibility(View.INVISIBLE);

            img6 = (ImageView) findViewById(R.id.letra6);
            img6.setVisibility(View.INVISIBLE);

            ln6 = (LinearLayout) findViewById(R.id.sexto);
            ln6.setVisibility(View.INVISIBLE);
        } else if (array.length == 5) {

            img1 = (ImageView) findViewById(R.id.letra1);
            img1.setImageResource(Metodos.getDrawableId(array[3]));
            ordemCerta[0] = array[0];

            img2 = (ImageView) findViewById(R.id.letra2);
            img2.setImageResource(Metodos.getDrawableId(array[4]));
            ordemCerta[1] = array[1];

            img3 = (ImageView) findViewById(R.id.letra3);
            img3.setImageResource(Metodos.getDrawableId(array[1]));
            ordemCerta[2] = array[2];

            img4 = (ImageView) findViewById(R.id.letra4);
            img4.setImageResource(Metodos.getDrawableId(array[0]));
            ordemCerta[3] = array[3];

            img5 = (ImageView) findViewById(R.id.letra5);
            img5.setImageResource(Metodos.getDrawableId(array[2]));
            ordemCerta[4] = array[4];


            lnCenter = (LinearLayout) findViewById(R.id.yellowLayout);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(130, 25, 0, 30);
            lnCenter.setLayoutParams(layoutParams);

            img6 = (ImageView) findViewById(R.id.letra6);
            img6.setVisibility(View.INVISIBLE);

            ln6 = (LinearLayout) findViewById(R.id.sexto);
            ln6.setVisibility(View.INVISIBLE);
        } else {
            img1 = (ImageView) findViewById(R.id.letra1);
            img1.setImageResource(Metodos.getDrawableId(array[3]));
            ordemCerta[0] = array[0];

            img2 = (ImageView) findViewById(R.id.letra2);
            img2.setImageResource(Metodos.getDrawableId(array[4]));
            ordemCerta[1] = array[1];

            img3 = (ImageView) findViewById(R.id.letra3);
            img3.setImageResource(Metodos.getDrawableId(array[1]));
            ordemCerta[2] = array[2];

            img4 = (ImageView) findViewById(R.id.letra4);
            img4.setImageResource(Metodos.getDrawableId(array[5]));
            ordemCerta[3] = array[3];

            img5 = (ImageView) findViewById(R.id.letra5);
            img5.setImageResource(Metodos.getDrawableId(array[2]));
            ordemCerta[4] = array[4];

            img6 = (ImageView) findViewById(R.id.letra6);
            img6.setImageResource(Metodos.getDrawableId(array[0]));
            ordemCerta[5] = array[5];
        }
    }

    private void setImageCenter(int index) {
        imgCenter = (ImageView) findViewById(R.id.molduraparaimagem);
        imgCenter.setImageResource(Metodos.imagensCentro[index]);
    }

    private boolean validaLetra(char letra, int index) {
        boolean isValid = false;
        char[] array = palavra.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[index] == letra) {
                isValid = true;
            }
        }
        return isValid;
    }

    class MyOnLongClickListener implements OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            //DragShadowBuilder sb = new View.DragShadowBuilder(findViewById(R.id.shadow));
            DragShadowBuilder sb = new View.DragShadowBuilder(v);
            v.startDrag(data, sb, v, 0);
            // Esconde a imagem quando for arrastar a sua sombra
            // v.setVisibility(View.INVISIBLE);
            return (true);
        }
    }

    class MyOnDragListener implements OnDragListener {
        private int num;

        // Construtor
        public MyOnDragListener(int num) {
            super();
            this.num = num;
        }

        // View 'v' é o parâmetro onde o objeto está sendo largado
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // Log para entender o funcionamento (Android Monitor)
                    Log.i("Script", num + " - ACTION_DRAG_STARTED");
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return (true);
                    }
                    return (false);
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.i("Script", num + " - ACTION_DRAG_ENTERED");
                    //v.setBackgroundColor(Color.YELLOW);
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.i("Script", num + " - ACTION_DRAG_LOCATION");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("Script", num + " - ACTION_DRAG_EXITED");
                    //v.setBackgroundColor(Color.BLUE);
                    break;
                case DragEvent.ACTION_DROP:
                    Log.i("Script", num + " - ACTION_DROP");
                    // Move a imagem de um container para outro (6 linhas abaixo)
                    View view = (View) event.getLocalState();//aqui entra quem está sendo movido

                    // indice onde foi largada a imagem
                    int index = 0;
                    if (v.getId() == R.id.primeiro && view.getId() == R.id.letra1) // Clicou na primeira imagem
                        index = 0;
                    else if (v.getId() == R.id.segundo && view.getId() == R.id.letra2) // Clicou na segunda imagem
                        index = 1;
                    else if (v.getId() == R.id.terceiro && view.getId() == R.id.letra3) // Clicou na terceira imagem
                        index = 2;
                    else if (v.getId() == R.id.quarto && view.getId() == R.id.letra4) // Clicou na quarta imagem
                        index = 3;
                    else if (v.getId() == R.id.quinto && view.getId() == R.id.letra5) // Clicou na quinta imagem
                        index = 4;
                    else if (v.getId() == R.id.sexto && view.getId() == R.id.letra6) // Clicou na sexra imagem
                        index = 5;


                    // AQUI TEM QUE DESCOBRIR QUAL FOI A LETRA QUE O USUARIO PEGOU!!!! DRAWABLE!!!

                    char letra = ordemCerta[index];

                    boolean acertou = validaLetra(letra, index);
                    if (acertou) {
                        pontos++;
                        Toast.makeText(JogoDeOrdernarActivity.this, "ACERTOU!!", Toast.LENGTH_SHORT).show();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        pontos++;
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    } else {
                        // mensagem de erro para o usuário
                        Toast.makeText(JogoDeOrdernarActivity.this, "LETRA ERRADA!!", Toast.LENGTH_SHORT).show();
                    }
                    if (pontos == palavra.length()) {
                        Toast.makeText(JogoDeOrdernarActivity.this, "PARABÉNS, VOCÊ GANHOU!!!", Toast.LENGTH_SHORT).show();
                        //AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        //builder.setMessage("TESTE").setPositiveButton("husahduihsaud", new  DialogInterface.OnclickListener());
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.i("Script", num + " - ACTION_DRAG_ENDED");
                    //v.setBackgroundColor(Color.BLUE);
                    break;
            }
            return true;
        }
    }

}
