package com.dev2.sa.sistemaacessibilidade;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class JogoDasPasActivity2 extends Activity {

    private final int PONTO_ACERTO = 10;
    private final int PONTO_ERRO = 5;
    private final int TOTAL_ACERTO = 8;
    private final int TOTAL_FASE = 3;
    private int acerto = 0,fase = 0;
    private int pontuacao = 0;
    private ImageView img;

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_das_pas2);
        setContentView(R.layout.activity_jogo_das_pas2);
    }

    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (action) {
            case DragEvent.ACTION_DROP:
                // Move a imagem de um container para outro (6 linhas abaixo)
                View view = (View) event.getLocalState();//aqui entra quem está sendo movido

                // indice onde foi largada a imagem
                int index = 0;
                if (v.getId() == R.id.bld1) // Clicou na primeira imagem
                    index = 0;
                else if (v.getId() == R.id.bld2) // Clicou na segunda imagem
                    index = 1;
                else if (v.getId() == R.id.bld3) // Clicou na terceira imagem
                    index = 2;
                else if (v.getId() == R.id.bld4) // Clicou na quarta imagem
                    index = 3;

                String tagId = view.getTag().toString();
                String palavra = Metodos.getDrawableString(Integer.parseInt(tagId),fase);

                boolean acertou = validade(index, palavra, getFase());
                if(acertou){
                    acerto++;
                    // faz o somatório
                    int total = Metodos.somaTotal(pontuacao, PONTO_ACERTO, true);
                    setPontuacao(total);
                    Toast.makeText(JogoDasPasActivity2.this, "VOCÊ ACERTOU!", Toast.LENGTH_SHORT).show();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    view.setEnabled(false);
                    v.setEnabled(false);
                } else{
                    // mensagem de erro para o usuário
                    Toast.makeText(JogoDasPasActivity2.this, "PALAVRA ERRADA!", Toast.LENGTH_SHORT).show();
                    int total = Metodos.somaTotal(pontuacao,PONTO_ERRO, false);
                    setPontuacao(total);
                }

                int pontuacaoAtual = getPontuacao();
                break;
        }
        return true;
    }



    private boolean validade(int index, String palavra, int fase){
        boolean isValid = false;
        HashMap<Integer,String> map = hashMap(fase);
        for (int key : map.keySet()) {
            if(palavra.equals(map.get(index))){
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    private HashMap<Integer,String> hashMap(int fase){
        HashMap<Integer, String> map = new HashMap<>();
        if(fase == 0) {
            map.put(0, "balde_azul");
            map.put(1, "balde_vermelho");
            map.put(2, "balde_verde");
        }else if(fase == 1){
            map.put(0,"pa_roxa");
            map.put(1,"pa_rosa");
            map.put(2,"pa_vinho");
            map.put(3,"pa_laranja");
        }
        return map;
    }


}
