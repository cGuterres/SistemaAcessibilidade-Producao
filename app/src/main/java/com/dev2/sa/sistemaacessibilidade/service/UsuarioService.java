package com.dev2.sa.sistemaacessibilidade.service;

import android.support.annotation.RequiresApi;

import com.dev2.sa.sistemaacessibilidade.model.Usuario;

import java.util.List;

import okhttp3.Callback;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Christian on 17/04/2018.
 */

public interface UsuarioService {
    @GET("usuario?q")
    @Headers("x-apikey: a76132d31039f02a3a12c639028e1d5f98b6a")
    Call<List<Usuario>> validaUsuario(@Path(value = "login", encoded = true) String login, @Path(value = "senha", encoded = true) String senha);
}
