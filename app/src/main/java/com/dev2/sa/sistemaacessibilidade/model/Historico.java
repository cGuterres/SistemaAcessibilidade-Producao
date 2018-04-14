package com.dev2.sa.sistemaacessibilidade.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Christian on 13/04/2018.
 */

public class Historico implements Serializable {
    private int codigo;
    private int jogoCodigo;
    private int alunoCodigo;
    private double pontuacao;
    private Date dataCriacao;

    public Historico(){

    }

    public Historico(int codigo, int jogoCodigo, int alunoCodigo, double pontuacao, Date dataCriacao){
        this.codigo = codigo;
        this.jogoCodigo = jogoCodigo;
        this.alunoCodigo = alunoCodigo;
        this.pontuacao = pontuacao;
        this.dataCriacao = dataCriacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getJogoCodigo() {
        return jogoCodigo;
    }

    public void setJogoCodigo(int jogoCodigo) {
        this.jogoCodigo = jogoCodigo;
    }

    public int getAlunoCodigo() {
        return alunoCodigo;
    }

    public void setAlunoCodigo(int alunoCodigo) {
        this.alunoCodigo = alunoCodigo;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
