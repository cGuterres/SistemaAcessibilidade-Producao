package com.dev2.sa.sistemaacessibilidade.dao;



import android.util.Log;

import com.dev2.sa.sistemaacessibilidade.model.Aluno;
import com.dev2.sa.sistemaacessibilidade.service.AlunoService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Christian on 13/04/2018.
 */

public class AlunoDAO {
    private static final String BASE_URL = "https://dbtcc-420a.restdb.io/rest/";

    private AlunoService service;

    public AlunoDAO(){
        Converter.Factory jsonFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(jsonFactory).build();

        this.service = retrofit.create(AlunoService.class);
    }

    public List<Aluno> listAlunos() {
        Call<List<Aluno>> call = this.service.listaAlunos();
        List<Aluno> alunos = null;

        try {
            Response<List<Aluno>> res = call.execute();

            if (res.isSuccessful()) {
                alunos = res.body();
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
        return alunos;
    }
}
