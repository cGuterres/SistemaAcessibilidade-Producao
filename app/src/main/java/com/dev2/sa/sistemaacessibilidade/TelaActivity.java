package com.dev2.sa.sistemaacessibilidade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TelaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela);
    }

    public void eventosFases(View view){

        if (view.getId() == R.id.jogo_das_pas)
            startActivity(new Intent(this, JogoDasPasActivity.class));
        else if (view.getId() == R.id.jogo_das_frutas)
            startActivity(new Intent(this, JogoDasFrutasActivity.class));
        else if (view.getId() == R.id.jogo_de_ordenar)
            startActivity(new Intent(this, Jogo_de_Ordenar_Palavra2.class));
        else if (view.getId() == R.id.jogo_de_ordenar_palavra)
            startActivity(new Intent(this, JogoDeOrdernarActivity.class));


    }




}
