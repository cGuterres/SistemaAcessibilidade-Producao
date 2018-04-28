package com.dev2.sa.sistemaacessibilidade.dao;

import android.util.Log;

import com.dev2.sa.sistemaacessibilidade.model.Usuario;
import com.dev2.sa.sistemaacessibilidade.service.UsuarioService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Christian on 17/04/2018.
 */

public class UsuarioDAO {
    private static final String BASE_URL = "https://dbtcc-420a.restdb.io/rest/";

    private UsuarioService service;

    public UsuarioDAO(){
        Converter.Factory jsonFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(jsonFactory).build();

        this.service = retrofit.create(UsuarioService.class);
    }

    public Usuario validaUsuario(String login, String senha) {
        Call<List<Usuario>> call = this.service.validaUsuario(login,senha);
        List<Usuario> usuario = null;

        try {
            Response<List<Usuario>> res = call.execute();

            if (res.isSuccessful()) {
                usuario = res.body();
            } else {
                String errorBody = res.errorBody().string();
                int errorCode = res.code();
                String errorMessage = res.message();

                Log.e(TAG, "Request not successful - "
                        + errorBody + ": "
                        + errorCode
                        + "(" + errorMessage + ")");
            }
        } catch (IOException exc){
            Log.e(TAG, "IO error during REST operation.", exc);
        }
        return usuario.get(0);
    }
}
