package com.dev2.sa.sistemaacessibilidade.model;

/**
 * Created by Christian on 14/04/2018.
 */

public class Contexto {

    private int alunoId;
    private static Contexto contexto = null;

    public static Contexto getContexto(){
        if(contexto == null){
           return contexto = new Contexto();
        }
        return contexto;
    }

    public static void setContexto(Contexto contexto) {
        Contexto.contexto = contexto;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }
}
