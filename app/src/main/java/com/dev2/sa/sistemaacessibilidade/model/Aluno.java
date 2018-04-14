package com.dev2.sa.sistemaacessibilidade.model;

import java.io.Serializable;

/**
 * Created by Christian on 13/04/2018.
 */

public class Aluno implements Serializable {
    private int codigo;
    private String nome;

    public Aluno(){

    }

    public Aluno(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
