package com.dev2.sa.sistemaacessibilidade;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jmedeisis.draglinearlayout.DragLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class Jogo_de_Ordenar_Palavra2 extends AppCompatActivity {

    private int fase = 0, acerto = 0;
    private final int PONTO_ACERTO = 10;
    private final int PONTO_ERRO = 5;
    private final int TOTAL_ACERTO = 4;
    private final int TOTAL_FASE = 3;
    private int pontuacao = 0;

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_de__ordenar__palavra2);

        Intent intent = getIntent();
        if(intent != null) {
            int nextPhase = intent.getIntExtra("fase", 0);
            if(nextPhase <= 3) {
                setFase(nextPhase);
            }
            int pontuacaoAtual = intent.getIntExtra("pontuacao", 0);
            setPontuacao(pontuacaoAtual);

            TextView txtPonto = (TextView)findViewById(R.id.txtPonto);
            if(txtPonto != null) {
                txtPonto.setText(Integer.toString(getPontuacao()));
            }
        }

        findViewById(R.id.palavra1).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavra2).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavra3).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavra4).setOnLongClickListener(new MyOnLongClickListener());


        findViewById(R.id.primeiro).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.segundo).setOnDragListener(new MyOnDragListener(2));
        findViewById(R.id.terceiro).setOnDragListener(new MyOnDragListener(3));
        findViewById(R.id.quarto).setOnDragListener(new MyOnDragListener(4));

        randomObjects(getFase());

      }

      private  void randomObjects(int fase) {
          int[] vetor = new int[4];         // troca o valor para que nao seja validado de forma errada quando o vetor for iniciado com 0
          for (int i = 0; i < vetor.length; i++) {
              vetor[i] = 9;
          }
          for (int i = 0; i < 4; i++) {
              int numeroSorteado = Metodos.sortearNumero(4);
              boolean valida = Metodos.validaNumero(vetor, numeroSorteado);
              if (valida) {
                  vetor[i] = numeroSorteado;
              } else {
                  boolean encontrou = false;
                  while (!encontrou) {
                      numeroSorteado = Metodos.sortearNumero(4);
                      valida = Metodos.validaNumero(vetor, numeroSorteado);
                      if (valida) {
                          vetor[i] = numeroSorteado;
                          encontrou = true;
                      }
                  }
              }
          }
          if(fase == 0) {
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra1);
              image.setImageResource(Metodos.getDrawableContent(vetor[0], fase));
              image.setTag(Metodos.getDrawableContent(vetor[0], fase));

              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.img1);
              image.setImageResource(R.drawable.img_lixo);

              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra2);
              image.setImageResource(Metodos.getDrawableContent(vetor[1], fase));
              image.setTag(Metodos.getDrawableContent(vetor[1], fase));

              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.imag2);
              image.setImageResource(R.drawable.img_seta);

              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra3);
              image.setImageResource(Metodos.getDrawableContent(vetor[2], fase));
              image.setTag(Metodos.getDrawableContent(vetor[2], fase));
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.img3);
              image.setImageResource(R.drawable.img_olho);

              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra4);
              image.setImageResource(Metodos.getDrawableContent(vetor[3], fase));
              image.setTag(Metodos.getDrawableContent(vetor[3], fase));
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.img4);
              image.setImageResource(R.drawable.img_fada);
          } else if(fase == 1){
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra1);
              image.setImageResource(Metodos.getDrawableContent(vetor[0], fase));
              image.setTag(Metodos.getDrawableContent(vetor[0], fase));
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.img1);
              image.setImageResource(R.drawable.img_grilo);

              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra2);
              image.setImageResource(Metodos.getDrawableContent(vetor[1], fase));
              image.setTag(Metodos.getDrawableContent(vetor[1], fase));
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.imag2);
              image.setImageResource(R.drawable.img_trono);

              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra3);
              image.setImageResource(Metodos.getDrawableContent(vetor[2], fase));
              image.setTag(Metodos.getDrawableContent(vetor[2], fase));
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.img3);
              image.setImageResource(R.drawable.img_cebola);

              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra4);
              image.setImageResource(Metodos.getDrawableContent(vetor[3], fase));
              image.setTag(Metodos.getDrawableContent(vetor[3], fase));
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.img4);
              image.setImageResource(R.drawable.img_porta);
          }else{
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra1);
              image.setImageResource(Metodos.getDrawableContent(vetor[0], fase));
              image.setTag(Metodos.getDrawableContent(vetor[0], fase));
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.img1);
              image.setImageResource(R.drawable.img_colher);

              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra2);
              image.setImageResource(Metodos.getDrawableContent(vetor[1], fase));
              image.setTag(Metodos.getDrawableContent(vetor[1], fase));
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.imag2);
              image.setImageResource(R.drawable.img_tomate);

              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra3);
              image.setImageResource(Metodos.getDrawableContent(vetor[2], fase));
              image.setTag(Metodos.getDrawableContent(vetor[2], fase));
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.img3);
              image.setImageResource(R.drawable.img_treno);

              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.palavra4);
              image.setImageResource(Metodos.getDrawableContent(vetor[3], fase));
              image.setTag(Metodos.getDrawableContent(vetor[3], fase));
              image = new ImageView(this);
              image = (ImageView) findViewById(R.id.img4);
              image.setImageResource(R.drawable.img_cavalo);
          }
      }

    class MyOnLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            //DragShadowBuilder sb = new View.DragShadowBuilder(findViewById(R.id.shadow));
            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);
            v.startDrag(data, sb, v, 0);
            // Esconde a imagem quando for arrastar a sua sombra
            // v.setVisibility(View.INVISIBLE);
            return (true);
        }
    }

    class MyOnDragListener implements View.OnDragListener {
        private int num;

        // Construtor
        public MyOnDragListener(int num) {
            super();
            this.num = num;
        }

        // View 'v' é o parâmetro onde o objeto está sendo largado
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DROP:
                    Log.i("Script", num + " - ACTION_DROP");
                    // Move a imagem de um container para outro (6 linhas abaixo)
                    View view = (View) event.getLocalState();//aqui entra quem está sendo movido

                    // indice onde foi largada a imagem
                    int index = 0;
                    if (v.getId() == R.id.primeiro) // Clicou na primeira imagem
                        index = 0;
                    else if (v.getId() == R.id.segundo) // Clicou na segunda imagem
                        index = 1;
                    else if (v.getId() == R.id.terceiro) // Clicou na terceira imagem
                        index = 2;
                    else if (v.getId() == R.id.quarto) // Clicou na quarta imagem
                        index = 3;

                    String tagId = view.getTag().toString();
                    String palavra = Metodos.getDrawableString(Integer.parseInt(tagId),fase);

                    boolean acertou = validade(index, palavra, getFase());
                    if(acertou){
                        // busca o som da palavra correspondente
                        Metodos.sound(Integer.parseInt(tagId),Jogo_de_Ordenar_Palavra2.this);
                        acerto++;
                        // faz o somatório
                        int total = Metodos.somaTotal(pontuacao, PONTO_ACERTO, true);
                        setPontuacao(total);
                        Toast.makeText(Jogo_de_Ordenar_Palavra2.this, "VOCÊ ACERTOU!", Toast.LENGTH_SHORT).show();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    } else{
                        // mensagem de erro para o usuário
                        Toast.makeText(Jogo_de_Ordenar_Palavra2.this, "PALAVRA ERRADA!", Toast.LENGTH_SHORT).show();
                        int total = Metodos.somaTotal(pontuacao,PONTO_ERRO, false);
                        setPontuacao(total);
                    }

                    if (acerto == TOTAL_ACERTO) {
                        // fala a palavra correta
                        if(getFase() < TOTAL_FASE - 1) {
                            ShowDialogNext(Jogo_de_Ordenar_Palavra2.this, R.drawable.icopala, "PARABÉNS!", "VOCÊ GANHOU!!");
                        }else{
                            ShowDialogRecreateGame(Jogo_de_Ordenar_Palavra2.this, R.drawable.icocasa, "PARABÉNS!", "VOCÊ CONCLUIU TODAS AS FASES!\n SUA PONTUAÇÃO: " + getPontuacao());
                        }
                    }
                    TextView mostra = (TextView)findViewById(R.id.txtPonto);
                    int pontuacaoAtual = getPontuacao();
                    mostra.setText(Integer.toString(pontuacaoAtual));
                    break;
            }
            return true;
        }
    }

    private boolean validade(int index, String palavra, int fase){
        boolean isValid = false;
        HashMap<Integer,String> map = hashMap(fase);
        for (int key : map.keySet()) {
            if(palavra.equals(map.get(index))){
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    private HashMap<Integer,String> hashMap(int fase){
        HashMap<Integer, String> map = new HashMap<>();
        if(fase == 0) {
            map.put(0, "lixo");
            map.put(1, "seta");
            map.put(2, "olho");
            map.put(3, "fada");
        }else if(fase == 1){
            map.put(0,"grilo");
            map.put(1,"trono");
            map.put(2,"cebola");
            map.put(3,"porta");
        }else{
            map.put(0,"colher");
            map.put(1,"tomate");
            map.put(2,"treno");
            map.put(3,"cavalo");
        }
        return map;
    }

    public void ShowDialogNext(final Activity act, @DrawableRes int desenho, String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle(titulo)
                .setMessage(mensagem)
                .setCancelable(false)
                .setIcon(desenho)
                .setPositiveButton("PRÓXIMA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        fase++;
                        setFase(fase);
                        if(getFase() <= TOTAL_FASE - 1) {
                            Intent nextActivity = new Intent(getBaseContext(), Jogo_de_Ordenar_Palavra2.class);
                            nextActivity.putExtra("fase", getFase());
                            nextActivity.putExtra("pontuacao", getPontuacao());
                            startActivity(nextActivity);
                            finish();
                        }
                    }
                }).setNegativeButton("SAIR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                act.finish();
            }
        });
        builder.create().show();        // create and show the alert dialog
    }

    public void ShowDialogRecreateGame(final Activity act, @DrawableRes int desenho, String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle(titulo)
                .setMessage(mensagem)
                .setCancelable(false)
                .setIcon(desenho)
                .setPositiveButton("REINICIAR FASES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        fase = 0;
                        setFase(fase);
                        pontuacao = 0;
                        setPontuacao(pontuacao);
                        Intent nextActivity = new Intent(getBaseContext(), Jogo_de_Ordenar_Palavra2.class);
                        nextActivity.putExtra("fase", getFase());
                        startActivity(nextActivity);
                        finish();
                    }
                }).setNegativeButton("SAIR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                act.finish();
            }
        });
        builder.create().show();        // create and show the alert dialog
    }
}
