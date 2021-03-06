package com.dev2.sa.sistemaacessibilidade.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dev2.sa.sistemaacessibilidade.JogoDaMemoriaActivity;
import com.dev2.sa.sistemaacessibilidade.R;

public class TelaActivity2 extends AppCompatActivity {

    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        mp.stop();
        playMusic();
    }

    public void eventosFases(View view) {
        if (view.getId() == R.id.vizinhos)
            startActivity(new Intent(this, JogoDosVizinhosActivity.class));
        else if (view.getId() == R.id.jogodaAdicao)
            startActivity(new Intent(this, JogoDaAdicaoActivity.class));
        else if (view.getId() == R.id.jogo_flores)
            startActivity(new Intent(this, JogoDasFloresActivity.class));
        else if (view.getId() == R.id.jogo_memoria)
            startActivity(new Intent(this, JogoDaMemoriaActivity.class));
        else if(view.getId() == R.id.retorna_fase) {
            if(this.mp.isPlaying()){
                this.mp.stop();
            }
            startActivity(new Intent(this, TelaActivity.class));
        }else if(view.getId() == R.id.btn_vaipraproxima){
            if(this.mp.isPlaying()){
                this.mp.stop();
            }
            startActivity(new Intent(this, TelaActivity3.class));
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