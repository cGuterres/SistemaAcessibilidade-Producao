package com.dev2.sa.sistemaacessibilidade.service;

import com.dev2.sa.sistemaacessibilidade.model.Configuracao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Christian on 17/04/2018.
 */

public interface ConfiguracaoService {
    @GET("configuracao")
    @Headers("x-apikey: a76132d31039f02a3a12c639028e1d5f98b6a")
    Call<List<Configuracao>> listaConfiguracoes();
}
