package com.dev2.sa.sistemaacessibilidade.service;

import com.dev2.sa.sistemaacessibilidade.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Body;

/**
 * Created by Christian on 17/04/2018.
 */

public interface UsuarioService {
    @GET("usuario?q={ \"login\": \"\", \"senha\": \"\" }")
    @Headers("x-apikey: a76132d31039f02a3a12c639028e1d5f98b6a")
    Call<Usuario> validaUsuario(@Body String login, String senha);
}
