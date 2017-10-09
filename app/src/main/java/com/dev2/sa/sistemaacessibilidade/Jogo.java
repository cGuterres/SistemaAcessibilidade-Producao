package com.dev2.sa.sistemaacessibilidade;

/**
 * Created by GUILHERME on 09/10/2017.
 */

public class Jogo {
    private int pontuacao;
    private final int pontoAcerto = 10;
    private final int pontoErro = 5;

    public int getPontoAcerto() {
        return pontoAcerto;
    }

    public int getPontoErro() {
        return pontoErro;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    // check = true - acerto
    //check = false - erro
    public int somaTotal(int valor, boolean check){
        if(check){
            this.pontuacao += valor;
        }else{
            this.pontuacao -= valor;
        }
        return this.pontuacao;
    }
}
