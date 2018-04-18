package com.dev2.sa.sistemaacessibilidade.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Christian on 17/04/2018.
 */

public class Usuario implements Serializable {
    private int codigo;
    private int tipoUsuarioCodigo;
    private String login;
    private String senha;
    private boolean ativo;
    private Date dataCriacao;

    public Usuario(){

    }

    public Usuario(int codigo, int tipoUsuarioCodigo, String login, String senha, boolean ativo, Date dataCriacao) {
        this.codigo = codigo;
        this.tipoUsuarioCodigo = tipoUsuarioCodigo;
        this.login = login;
        this.senha = senha;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
