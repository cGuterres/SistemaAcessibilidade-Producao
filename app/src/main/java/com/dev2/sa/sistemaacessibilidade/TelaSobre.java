package com.dev2.sa.sistemaacessibilidade;

import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TelaSobre extends AppCompatActivity {


    MediaPlayer mp = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sobre);
/*
        mp = MediaPlayer.create(this, R.raw.creditosdev);
       mp = MediaPlayer.create(this, R.raw.apaeviamao);
        mp = MediaPlayer.create(this, R.raw.desenvol);
        mp = MediaPlayer.create(this, R.raw.christian);
        mp = MediaPlayer.create(this, R.raw.guilherme);
        mp = MediaPlayer.create(this, R.raw.levy);
        mp.start();*/


        Thread t = new Thread(){
            @Override
            public void run(){

                try{
                    Thread.sleep(3000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                for (int i = 0; i < getList().length; i++) {
                                    Thread.sleep(2000);
                                    mp = getList()[i];

                                    mp.start();
                            }
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    });
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        };
        t.start();
    }

    public MediaPlayer[] getList(){
        MediaPlayer[] vet = new MediaPlayer[6];
        vet[0] = MediaPlayer.create(this, R.raw.creditosdev);
        vet[1] = MediaPlayer.create(this, R.raw.apaeviamao);
        vet[2] = MediaPlayer.create(this, R.raw.desenvol);
        vet[3] = MediaPlayer.create(this, R.raw.christian);
        vet[4] = MediaPlayer.create(this, R.raw.guilherme);
        vet[5] = MediaPlayer.create(this, R.raw.levy);
        return vet;
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.mp.stop();
    }


    private void playMusic(){
        this.mp = MediaPlayer.create(this, R.raw.creditosdev);
        this.mp.start();
        // this.mp.setLooping(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(this.mp != null && !this.mp.isPlaying()) {
            playMusic();
        }
    }

}
