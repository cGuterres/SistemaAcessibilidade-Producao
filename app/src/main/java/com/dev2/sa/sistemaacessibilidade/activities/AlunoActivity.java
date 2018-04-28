package com.dev2.sa.sistemaacessibilidade.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dev2.sa.sistemaacessibilidade.R;
import com.dev2.sa.sistemaacessibilidade.dao.AlunoDAO;
import com.dev2.sa.sistemaacessibilidade.dao.ConfiguracaoDAO;
import com.dev2.sa.sistemaacessibilidade.model.Aluno;
import com.dev2.sa.sistemaacessibilidade.model.Configuracao;
import com.dev2.sa.sistemaacessibilidade.model.Contexto;

import java.util.List;

public class AlunoActivity extends AppCompatActivity {

    private class AlunoLoaderTask extends AsyncTask<Void, Void, List<Aluno>> {

        @Override
        public List<Aluno> doInBackground(Void... params) {
            AlunoDAO dao = new AlunoDAO();
            List<Aluno> alunos = null;

            alunos = dao.listAlunos();
            return alunos;
        }

        @Override
        public void onPostExecute(List<Aluno> alunos) {
            alunoAdapter.addAll(alunos);
        }
    }

    private class ConfiguracaoLoaderTask extends AsyncTask<Void, Void, List<Configuracao>> {

        @Override
        public List<Configuracao> doInBackground(Void... params) {
            ConfiguracaoDAO dao = new ConfiguracaoDAO();
            List<Configuracao> configuracoes = null;

            if(Contexto.getUsuario() != null){
                configuracoes = dao.listConfiguracoes(Contexto.getUsuario().getTipoUsuarioCodigo());
            }
            return configuracoes;
        }

        @Override
        public void onPostExecute(List<Configuracao> configs) {
            if(configs != null && configs.size() > 0)
            Contexto.setConfiguracoes(configs);
        }
    }

    private ArrayAdapter<Aluno> alunoAdapter;
    private ListView alunosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        this.alunoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        this.alunosList = (ListView) findViewById(R.id.alunosList);
        this.alunosList.setAdapter(alunoAdapter);
        this.alunosList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                alunoSelecionado(position);
            }
        });
        //busca na nuvem os alunos
        new AlunoLoaderTask().execute();
        //busca no ws as configurações
        new ConfiguracaoLoaderTask().execute();
    }

    private void alunoSelecionado(int position) {
        //inicia o contexto da aplicação
        Contexto contexto = new Contexto();
        Aluno aluno = alunoAdapter.getItem(position);
        contexto.setAlunoId(aluno.getCodigo());
        contexto.setContexto(contexto);
        Intent intent = new Intent(this, TelaActivity.class);

        intent.putExtra("aluno", aluno);
        startActivity(intent);
        finish();
    }
}
