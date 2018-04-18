package com.dev2.sa.sistemaacessibilidade.model;

import java.io.Serializable;

/**
 * Created by Christian on 17/04/2018.
 */

public enum TipoUsuario implements Serializable {
    ADMIN(1),
    PROFESSOR(2);

    private final int valor;
    TipoUsuario(int valorOpcao){
        valor = valorOpcao;
    }
    public int getValor(){
        return valor;
    }
}
