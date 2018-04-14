package com.dev2.sa.sistemaacessibilidade.service;

import com.dev2.sa.sistemaacessibilidade.model.Historico;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Christian on 14/04/2018.
 */

public interface JogoService {
    @POST("historico")
    @Headers("x-apikey: a76132d31039f02a3a12c639028e1d5f98b6a")
    Call<Historico> inserirJogada(@Body Historico historico);
}
