package com.dev2.sa.sistemaacessibilidade.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dev2.sa.sistemaacessibilidade.R;

public class TelaActivity extends AppCompatActivity {

    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela);

        mp.stop();
        playMusic();
    }

    public void eventosFases(View view) {

        if (view.getId() == R.id.jogo_das_pas)
            startActivity(new Intent(this, JogoDasPasActivity2.class));
        else if (view.getId() == R.id.jogo_das_frutas)
            startActivity(new Intent(this, JogoDasFrutasActivity.class));
        else if (view.getId() == R.id.jogo_de_ordenar)
            startActivity(new Intent(this, Jogo_de_Ordenar_Palavra2.class));
        else if (view.getId() == R.id.jogo_de_ordenar_palavra)
            startActivity(new Intent(this, JogoDeOrdernarActivity.class));
        else if(view.getId() == R.id.outra_fase) {
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