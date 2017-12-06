package com.dev2.sa.sistemaacessibilidade;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TelaActivity3 extends AppCompatActivity {

    MediaPlayer mp = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);

        mp.stop();
        playMusic();
    }

    public void eventosFases(View view) {
        if (view.getId() == R.id.emocoes)
            startActivity(new Intent(this, JogoDasEmocoes.class));
        else if (view.getId() == R.id.corpo)
            startActivity(new Intent(this, JodoDoCorpo.class));
        else if (view.getId() == R.id.sobre)
            startActivity(new Intent(this, TelaSobre.class));
        else if(view.getId() == R.id.retorna_fase) {
            if(this.mp.isPlaying()){
                this.mp.stop();
            }
            startActivity(new Intent(this, TelaActivity2.class));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.mp.stop();
    }

    public void botaonmute(View view) {
        if(this.mp != null && this.mp.isPlaying()){
            this.mp.stop();
        }else{
            playMusic();
        }
    }

    private void playMusic(){
        this.mp = MediaPlayer.create(this, R.raw.menu);
        this.mp.start();
        this.mp.setLooping(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(this.mp != null && !this.mp.isPlaying()) {
            playMusic();
        }
    }
}
