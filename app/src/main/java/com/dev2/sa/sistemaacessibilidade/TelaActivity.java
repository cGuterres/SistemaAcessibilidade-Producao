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

        if (view.getId() == R.id.fase_1)
            startActivity(new Intent(this, JogoDasPasActivity.class));
        else if (view.getId() == R.id.fase_2)
            startActivity(new Intent(this, JogoDeOrdernarActivity.class));
    }




}
