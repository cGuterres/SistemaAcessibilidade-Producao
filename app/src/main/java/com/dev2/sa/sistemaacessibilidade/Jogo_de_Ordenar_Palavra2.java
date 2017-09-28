package com.dev2.sa.sistemaacessibilidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jmedeisis.draglinearlayout.DragLinearLayout;

public class Jogo_de_Ordenar_Palavra2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_de__ordenar__palavra2);

        DragLinearLayout menujogo = (DragLinearLayout) findViewById(R.id.drag_drop_layout);


        for (int i = 0; i < menujogo.getChildCount(); i++) {
            View child = menujogo.getChildAt(i);
            menujogo.setViewDraggable(child, child);
        }


    }







}
