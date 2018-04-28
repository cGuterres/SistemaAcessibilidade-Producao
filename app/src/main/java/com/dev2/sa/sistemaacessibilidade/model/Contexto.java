package com.dev2.sa.sistemaacessibilidade.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 14/04/2018.
 */

public class Contexto {
    private int alunoId;
    private static Contexto contexto = null;
    private static List<Configuracao> configuracoes = null;
    private static Usuario usuario = null;

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

    public static List<Configuracao> getConfiguracoes() {
        if(configuracoes == null || configuracoes.size() == 0){
            return new ArrayList<Configuracao>();
        }
        return configuracoes;
    }

    public static void setConfiguracoes(List<Configuracao> configuracoes) {
        Contexto.configuracoes = configuracoes;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Contexto.usuario = usuario;
    }
}
