package com.dev2.sa.sistemaacessibilidade.service;

import com.dev2.sa.sistemaacessibilidade.model.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface AlunoService {
    @GET("aluno")
    @Headers("x-apikey: a76132d31039f02a3a12c639028e1d5f98b6a")
    Call<List<Aluno>> listaAlunos();
}
