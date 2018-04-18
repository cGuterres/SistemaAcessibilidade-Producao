package com.dev2.sa.sistemaacessibilidade.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Christian on 17/04/2018.
 */

public class Configuracao implements Serializable {
    private int codigo;
    private int tipoUsuarioCodigo;
    private String chave;
    private String valor;
    private boolean ativo;
    private Date dataCriacao;

    public Configuracao(){

    }

    public Configuracao(int codigo, int tipoUsuarioCodigo, String chave, String valor, boolean ativo, Date dataCriacao) {
        this.codigo = codigo;
        this.tipoUsuarioCodigo = tipoUsuarioCodigo;
        this.chave = chave;
        this.valor = valor;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTipoUsuarioCodigo() {
        return tipoUsuarioCodigo;
    }

    public void setTipoUsuarioCodigo(int tipoUsuarioCodigo) {
        this.tipoUsuarioCodigo = tipoUsuarioCodigo;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
