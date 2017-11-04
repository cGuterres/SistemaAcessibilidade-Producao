package com.dev2.sa.sistemaacessibilidade;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.GridLayout;

/**
 * Created by Christian on 27/10/2017.
 */

public class JogaMemoria extends android.support.v7.widget.AppCompatButton {
    private int linha;
    private int coluna;
    private int imageDrawableId;

    private boolean virada = false;
    private boolean desvirada = false;

    private Drawable image;
    private Drawable imageVirada;

    public JogaMemoria(Context context, int linha, int coluna, int imagemDrawableId){
        super(context);
        this.linha = linha;
        this.coluna = coluna;
        this.imageDrawableId = imagemDrawableId;

        image = AppCompatDrawableManager.get().getDrawable(context, imagemDrawableId);
        imageVirada = AppCompatDrawableManager.get().getDrawable(context, R.drawable.versocarta);

        setBackground(imageVirada);

        GridLayout.LayoutParams parameters = new GridLayout.LayoutParams(GridLayout.spec(linha), GridLayout.spec(coluna));
        parameters.width = (int)getResources().getDisplayMetrics().density * 80;
        parameters.height = (int)getResources().getDisplayMetrics().density * 80;

        setLayoutParams(parameters);
    }

    public boolean isVirada() {
        return virada;
    }

    public void setVirada(boolean virada) {
        this.virada = virada;
    }

    public boolean isDesvirada() {
        return desvirada;
    }

    public void setDesvirada(boolean desvirada) {
        this.desvirada = desvirada;
    }

    public int getImageDrawableId() {
        return imageDrawableId;
    }

    public void girar(){
        // se a imagem está desvirada nao faz nada
        if(isDesvirada())
            return;

        // se tá virada a imagem
        if(isVirada()){
            // coloca a imagem padrão com ?
            setBackground(imageVirada);
            virada = false;

        }else {
            // se nao tá virada coloca seta a imagem da figura
            setBackground(image);
            virada = true;
        }
    }
}
