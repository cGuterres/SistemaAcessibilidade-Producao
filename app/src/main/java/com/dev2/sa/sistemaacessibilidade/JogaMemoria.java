package com.dev2.sa.sistemaacessibilidade;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.DisplayMetrics;
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

    public JogaMemoria(Context context, int linha, int coluna, int imagemDrawableId, int fase) {
        super(context);

        this.linha = linha;
        this.coluna = coluna;
        this.imageDrawableId = imagemDrawableId;
        getDeviceDensity(context);
        image = AppCompatDrawableManager.get().getDrawable(context, imagemDrawableId);
        imageVirada = AppCompatDrawableManager.get().getDrawable(context, R.drawable.versocarta);

        setBackground(imageVirada);

        GridLayout.LayoutParams parameters = new GridLayout.LayoutParams(GridLayout.spec(linha), GridLayout.spec(coluna));
        // metodo para sabe a Resolução - CORRETO - 05.11.2017
        // float density = getResources().getDisplayMetrics().density;

        if ( fase == 0) {

            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:
                    //deviceDensity =  0.75 + " ldpi";
                    parameters.width = (int) getResources().getDisplayMetrics().density * 5;
                    parameters.height = (int) getResources().getDisplayMetrics().density * 5;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:         //deviceDensity =  1.0 + " mdpi";
                    // MDPI - TABLET
                    parameters.width = (int) getResources().getDisplayMetrics().density * 150;
                    parameters.height = (int) getResources().getDisplayMetrics().density * 300;

                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    //deviceDensity =  1.5 + " hdpi";
                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    //deviceDensity =  2.0 + " xhdpi";
                    parameters.width = (int) getResources().getDisplayMetrics().density * 90;
                    parameters.height = (int) getResources().getDisplayMetrics().density * 180;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:
                    // deviceDensity =  3.0 + " xxhdpi";
                    parameters.width = (int) getResources().getDisplayMetrics().density * 80;
                    parameters.height = (int) getResources().getDisplayMetrics().density * 160;

                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    //deviceDensity =  4.0 + " xxxhdpi";
                    break;
                default:
                    //deviceDensity = "Not found";
            }
        }

        if (fase == 1) {

            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:
                    //deviceDensity =  0.75 + " ldpi";
                    parameters.width = (int) getResources().getDisplayMetrics().density * 5;
                    parameters.height = (int) getResources().getDisplayMetrics().density * 5;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:         //deviceDensity =  1.0 + " mdpi";
                    // MDPI - TABLET
                    parameters.width = (int) getResources().getDisplayMetrics().density * 145;
                    parameters.height = (int) getResources().getDisplayMetrics().density * 233;

                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    //deviceDensity =  1.5 + " hdpi";
                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    //deviceDensity =  2.0 + " xhdpi" J5;
                    parameters.width = (int) getResources().getDisplayMetrics().density * 90;
                    parameters.height = (int) getResources().getDisplayMetrics().density * 135;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:
                    // deviceDensity =  3.0 + " xxhdpi moto g5";
                    parameters.width = (int) getResources().getDisplayMetrics().density * 85;
                    parameters.height = (int) getResources().getDisplayMetrics().density * 125;

                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    //deviceDensity =  4.0 + " xxxhdpi";
                    break;
                default:
                    //deviceDensity = "Not found";
            }
        }
        setLayoutParams(parameters);
    }

// metodo para sabe a Resolução
    public static void getDeviceDensity(Context context){

       String deviceDensity = "";
        switch (context.getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                deviceDensity =  0.75 + " ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                deviceDensity =  1.0 + " mdpi";

                break;
            case DisplayMetrics.DENSITY_HIGH:
                deviceDensity =  1.5 + " hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                deviceDensity =  2.0 + " xhdpi";

                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                deviceDensity =  3.0 + " xxhdpi";

                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                deviceDensity =  4.0 + " xxxhdpi";
                break;
            default:
                deviceDensity = "Not found";
        }
        //return deviceDensity;
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
