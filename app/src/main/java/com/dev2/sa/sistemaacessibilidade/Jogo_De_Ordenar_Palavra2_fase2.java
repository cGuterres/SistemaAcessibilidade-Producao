package com.dev2.sa.sistemaacessibilidade;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;

        import com.jmedeisis.draglinearlayout.DragLinearLayout;

        import java.util.ArrayList;

public class Jogo_De_Ordenar_Palavra2_fase2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo__de__ordenar__palavra2_fase2);

        findViewById(R.id.grilo);
        findViewById(R.id.trono);
        findViewById(R.id.treno);
        findViewById(R.id.porta);

        DragLinearLayout menujogo = (DragLinearLayout) findViewById(R.id.drag_drop_layout_fase2);
        ArrayList<View> lst = new ArrayList<View>();
        int[] vetor = new int[menujogo.getChildCount()];
        // troca o valor para que nao seja validado de forma errada quando o vetor for iniciado com 0
        for(int i = 0; i < vetor.length; i++){
            vetor[i] = 9;
        }
        for(int i = 0; i < menujogo.getChildCount(); i++){
            int numeroSorteado = Metodos.sortearNumero(menujogo.getChildCount());
            boolean valida = validaNumero(vetor, numeroSorteado);
            if(valida){
                vetor[i] = numeroSorteado;
            }else{
                boolean encontrou = false;
                while(!encontrou){
                    numeroSorteado = Metodos.sortearNumero(menujogo.getChildCount());
                    valida = validaNumero(vetor, numeroSorteado);
                    if(valida){
                        vetor[i] = numeroSorteado;
                        encontrou = true;
                    }
                }
            }
            lst.add(menujogo.getChildAt(numeroSorteado));
        }

        ImageView im1 = (ImageView)findViewById(R.id.palavragrilo);
        im1.setImageResource(Metodos.getDrawablePhaseTwo1(vetor[0]));

        ImageView im2 = (ImageView)findViewById(R.id.palavratrono);
        im2.setImageResource(Metodos.getDrawablePhaseTwo1(vetor[1]));

        ImageView im3 = (ImageView)findViewById(R.id.palavratreno);
        im3.setImageResource(Metodos.getDrawablePhaseTwo1(vetor[2]));

        ImageView im4 = (ImageView)findViewById(R.id.palavraporta);
        im4.setImageResource(Metodos.getDrawablePhaseTwo1(vetor[3]));

        for(View v : lst){
            menujogo.setViewDraggable(v, v);
        }
        /*
        DragLinearLayout menujogo = (DragLinearLayout) findViewById(R.id.drag_drop_layout);
        for (int i = 0; i < menujogo.getChildCount(); i++) {
            View child = menujogo.getChildAt(i);
            menujogo.setViewDraggable(child, child);
        }*/
    }

    private boolean validaNumero(int[] vetor, int value){
        // valida as posições
        for(int i = 0; i < vetor.length; i++){
            if(vetor[i] == value)
                // numero já sorteado
                return false;
        }
        // numero nao existe
        return true;
    }


    private ArrayList<ImageView> getImages(){
        ArrayList<ImageView> lst = new ArrayList<ImageView>();
        DragLinearLayout menujogo = (DragLinearLayout) findViewById(R.id.drag_drop_layout_fase2);
        for (int i = 0; i < menujogo.getChildCount(); i++) {
            ImageView child = (ImageView)menujogo.getChildAt(i);
            //menujogo.setViewDraggable(child, child);
            lst.add(child);
        }
        return lst;
    }
}