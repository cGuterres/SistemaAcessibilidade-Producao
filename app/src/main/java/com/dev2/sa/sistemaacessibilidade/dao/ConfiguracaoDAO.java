package com.dev2.sa.sistemaacessibilidade.dao;

import android.util.Log;

import com.dev2.sa.sistemaacessibilidade.model.Configuracao;
import com.dev2.sa.sistemaacessibilidade.service.ConfiguracaoService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Christian on 17/04/2018.
 */

public class ConfiguracaoDAO {
    private static final String BASE_URL = "https://dbtcc-420a.restdb.io/rest/";

    private ConfiguracaoService service;

    public ConfiguracaoDAO(){
        Converter.Factory jsonFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(jsonFactory).build();

        this.service = retrofit.create(ConfiguracaoService.class);
    }

    public List<Configuracao> listConfiguracoes(int tipoUsuarioCodigo) {
        Call<List<Configuracao>> call = this.service.listaConfiguracoes(tipoUsuarioCodigo);
        List<Configuracao> configs = null;

        try {
            Response<List<Configuracao>> res = call.execute();

            if (res.isSuccessful()) {
                configs = res.body();
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
        return configs;
    }
}
