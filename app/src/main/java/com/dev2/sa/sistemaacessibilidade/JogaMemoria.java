package com.dev2.sa.sistemaacessibilidade;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
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
        double polegada = 0;
        if(context != null){
            polegada = getScreenInches(context);
        }
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
        // de acordo com a fase seta as dimensões de acordo com o equipamento em que aplicação esta rodando.
        if ( fase == 0) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:
                    //deviceDensity =  0.75 + " ldpi";
                    parameters.width = (int) getResources().getDisplayMetrics().density * 5;
                    parameters.height = (int) getResources().getDisplayMetrics().density * 5;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:         //deviceDensity =  1.0 + " mdpi";
                    // MDPI - TABLET
                    // ******************tablet 07 polegadas*********************
                    if(polegada < 8) {
                        parameters.width = (int) getResources().getDisplayMetrics().density * 150;
                        parameters.height = (int) getResources().getDisplayMetrics().density * 300;
                    }


                    // ******************tablet 10 polegadas*********************
                    if(polegada > 8) {
                        parameters.width = (int) getResources().getDisplayMetrics().density * 200;
                        parameters.height = (int) getResources().getDisplayMetrics().density * 350;
                    }

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
                    parameters.width = (int) getResources().getDisplayMetrics().density * 125;
                    parameters.height = (int) getResources().getDisplayMetrics().density * 250;
                    break;
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
                    // ******************tablet 7 polegadas*********************
                    if(polegada < 8) {
                        parameters.width = (int) getResources().getDisplayMetrics().density * 145;
                        parameters.height = (int) getResources().getDisplayMetrics().density * 233;
                    }
                    // ******************tablet 10 polegadas*********************
                    if(polegada > 8) {

                        parameters.width = (int) getResources().getDisplayMetrics().density * 190;
                        parameters.height = (int) getResources().getDisplayMetrics().density * 280;
                    }
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
                    parameters.width = (int) getResources().getDisplayMetrics().density * 130;
                    parameters.height = (int) getResources().getDisplayMetrics().density * 195;
                    break;
            }
        }
        setLayoutParams(parameters);
    }

    public static Point getScreenSize(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int w = wm.getDefaultDisplay().getWidth();
        int h = wm.getDefaultDisplay().getHeight();
        //displayMetrics = context.getResources().getDisplayMetrics();
        return new Point(w, h);

    }

    //pega a polegada da tela
    public static double getScreenInches(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        double wi=(double)width/(double)dm.xdpi;
        double hi=(double)height/(double)dm.ydpi;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
        double screenInches = Math.sqrt(x+y);
        return screenInches;
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
