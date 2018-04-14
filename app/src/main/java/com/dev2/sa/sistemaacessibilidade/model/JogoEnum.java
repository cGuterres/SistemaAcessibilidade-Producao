package com.dev2.sa.sistemaacessibilidade.model;

import java.io.Serializable;

/**
 * Created by Christian on 13/04/2018.
 */

public enum JogoEnum implements Serializable {
    JOGO_DAS_FRUTAS(1),
    JOGO_DAS_LETRAS(2),
    JOGO_DOS_NOMES(3),
    JOGO_DAS_PAS(4),
    JOGO_DOS_VIZINHOS(5),
    JOGO_DA_SOMA(6),
    JOGO_DAS_FLORES(7),
    JOGO_DA_MEMORIA(8),
    JOGO_DAS_EMOCOES(9),
    JOGO_DO_CORPO(10);

    private final int valor;
    JogoEnum(int valorOpcao){
        valor = valorOpcao;
    }
    public int getValor(){
        return valor;
    }
}
