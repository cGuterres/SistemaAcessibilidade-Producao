package com.dev2.sa.sistemaacessibilidade.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dev2.sa.sistemaacessibilidade.R;
import com.dev2.sa.sistemaacessibilidade.dao.UsuarioDAO;
import com.dev2.sa.sistemaacessibilidade.model.Usuario;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private class ValidaUsuarioTask extends AsyncTask<Usuario, Void, Usuario> {

        @Override
        public Usuario doInBackground(Usuario... params) {
            UsuarioDAO dao = new UsuarioDAO();

            return dao.validaUsuario(params[0].getLogin(), params[0].getSenha());
        }

        @Override
        public void onPostExecute(Usuario usuario) {
            Intent intent = new Intent();

            //intent.putExtra("usuario", usuario);
            //setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    private EditText txtLogin, txtSenha;
    private Text lblLogin, lblSenha;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtLogin = (EditText)findViewById(R.id.txtLogin);
        txtSenha = (EditText)findViewById(R.id.txtSenha);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaUsuario(txtLogin.getText().toString(), txtSenha.getText().toString());
            }
        });
    }

    public void validaUsuario(String login, String senha){
        Usuario u = new Usuario();
        u.setLogin(login);
        u.setSenha(senha);

        new ValidaUsuarioTask().execute(u);
    }
}
