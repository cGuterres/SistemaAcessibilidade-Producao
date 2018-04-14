package com.dev2.sa.sistemaacessibilidade;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dev2.sa.sistemaacessibilidade.dao.AlunoDAO;
import com.dev2.sa.sistemaacessibilidade.model.Aluno;

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
        public void onPostExecute(List<Aluno> notes) {
            alunoAdapter.addAll(notes);
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

        new AlunoLoaderTask().execute();
    }

    private void alunoSelecionado(int position) {
        Aluno aluno = alunoAdapter.getItem(position);
        Intent intent = new Intent(this, TelaActivity.class);

        intent.putExtra("aluno", aluno);
        startActivity(intent);
    }
}
