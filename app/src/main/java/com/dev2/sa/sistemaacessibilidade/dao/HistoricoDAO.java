package com.dev2.sa.sistemaacessibilidade.dao;

import android.util.Log;

import com.dev2.sa.sistemaacessibilidade.model.Historico;
import com.dev2.sa.sistemaacessibilidade.service.JogoService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Christian on 14/04/2018.
 */

public class HistoricoDAO {
    private static final String BASE_URL = "https://dbtcc-420a.restdb.io/rest/";
    private JogoService service;

    public HistoricoDAO(){
        Converter.Factory jsonFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(jsonFactory).build();

        this.service = retrofit.create(JogoService.class);
    }

    public Historico inserirJogada(Historico historico) {
        Call<Historico> call = this.service.inserirJogada(historico);
        Historico novaJogada = null;

        try {
            Response<Historico> res = call.execute();

            if (res.isSuccessful()) {
                novaJogada = res.body();
            } else {
                String errorBody = res.errorBody().string();
                int errorCode = res.code();
                String errorMessage = res.message();

                Log.e(TAG, "Request not successful - "
                        + errorBody + ": "
                        + errorCode
                        + " (" + errorMessage + ")");
            }
        } catch (IOException exc) {
            Log.e(TAG, "IO error during REST operation.", exc);
        }
        return novaJogada;
    }
}
