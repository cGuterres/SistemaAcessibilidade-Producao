package com.dev2.sa.sistemaacessibilidade;

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

        mp = MediaPlayer.create(this, R.raw.creditosdev);
     /*   mp = MediaPlayer.create(this, R.raw.apaeviamao);
        mp = MediaPlayer.create(this, R.raw.desenvol);
        mp = MediaPlayer.create(this, R.raw.christian);
        mp = MediaPlayer.create(this, R.raw.guilherme);
        mp = MediaPlayer.create(this, R.raw.levy);    */
        mp.start();


        //  mp.stop();
      //  playMusic();
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
