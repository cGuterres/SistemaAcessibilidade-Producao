package com.dev2.sa.sistemaacessibilidade.activities;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev2.sa.sistemaacessibilidade.utils.Metodos;
import com.dev2.sa.sistemaacessibilidade.R;

import java.util.HashMap;

public class JogoDasPasActivity2 extends Activity {

    private final int PONTO_ACERTO = 10;
    private final int PONTO_ERRO = 5;
    private int TOTAL_ACERTO = 3;
    private final int TOTAL_FASE = 2;
    private int acerto = 0, fase = 0;
    private int pontuacao = 0;
    private ImageView img;

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_das_pas2);

        Intent intent = getIntent();
        if (intent != null) {
            int nextPhase = intent.getIntExtra("fase", 0);
            if (nextPhase <= 3) {
                setFase(nextPhase);
            }
            int pontuacaoAtual = intent.getIntExtra("pontuacao", 0);
            setPontuacao(pontuacaoAtual);

            TextView txtPonto = (TextView) findViewById(R.id.txtPonto);
            if (txtPonto != null) {
                txtPonto.setText(Integer.toString(getPontuacao()));
            }
        }

        if(getFase() == 1){
            TOTAL_ACERTO = 3;
        }

        CreateElements();
        setTags(getFase());

    }

    class MyOnDragListener implements View.OnDragListener {
        private int num;

        // Construtor
        public MyOnDragListener(int num) {
            super();
            this.num = num;
        }

        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            View view = (View) event.getLocalState();//aqui entra quem está sendo movido
            switch (action) {
                case DragEvent.ACTION_DRAG_ENDED:
                    //qualquer evento de drag que acabar vai setar a figura para visível, independente do lugar que a figura cair
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DROP:
                    // Move a imagem de um container para outro (6 linhas abaixo)
                    // indice onde foi largada a imagem
                    int index = 0;
                    if (v.getId() == R.id.primeiroBalde) // Clicou na primeira imagem
                        index = 0;
                    else if (v.getId() == R.id.segundoBalde) // Clicou na segunda imagem
                        index = 1;
                    else if (v.getId() == R.id.terceiroBalde) // Clicou na terceira imagem
                        index = 2;
//                    else if (v.getId() == R.id.quartoBalde) // Clicou na quarta imagem
//                        index = 3;

                    String tagId = view.getTag().toString();
                    String pa = Metodos.setDrawableShovel(Integer.parseInt(tagId), fase);

                    boolean acertou = validade(index, pa, getFase());
                    if (acertou) {
                        acerto++;
                        Metodos.sound(R.raw.sound_success, JogoDasPasActivity2.this);
                        // faz o somatório
                        int total = Metodos.somaTotal(pontuacao, PONTO_ACERTO, true);
                        setPontuacao(total);
                        Toast.makeText(JogoDasPasActivity2.this, "VOCÊ ACERTOU!", Toast.LENGTH_SHORT).show();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);
                    } else {
                        // mensagem de erro para o usuário
                        Toast.makeText(JogoDasPasActivity2.this, "PÁ ERRADA!", Toast.LENGTH_SHORT).show();
                        int total = Metodos.somaTotal(pontuacao, PONTO_ERRO, false);
                        setPontuacao(total);
                    }
                    view.setVisibility(View.VISIBLE);
                    if (acerto == TOTAL_ACERTO) {
                        // fala a palavra correta
                        if (getFase() < TOTAL_FASE - 1) {
                            Metodos.sound(R.raw.sound_aplausos, JogoDasPasActivity2.this);
                            ShowDialogNext(JogoDasPasActivity2.this, R.drawable.icopala, "PARABÉNS!", "VOCÊ GANHOU!!");
                        } else {
                            Metodos.sound(R.raw.sound_aplausos, JogoDasPasActivity2.this);
                            ShowDialogRecreateGame(JogoDasPasActivity2.this, R.drawable.icocasa, "PARABÉNS!", "VOCÊ CONCLUIU TODAS AS FASES!\n SUA PONTUAÇÃO: " + getPontuacao());
                        }
                    }
                    TextView mostra = (TextView) findViewById(R.id.txtPonto);
                    int pontuacaoAtual = getPontuacao();
                    mostra.setText(Integer.toString(pontuacaoAtual));
                    break;
            }
            return true;
        }
    }

    class MyOnLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            //DragShadowBuilder sb = new View.DragShadowBuilder(findViewById(R.id.shadow));
            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);
            v.setVisibility(View.INVISIBLE);
            v.startDrag(data, sb, v, 0);
            // Esconde a imagem quando for arrastar a sua sombra
            // v.setVisibility(View.INVISIBLE);
            return (true);
        }
    }

    private boolean validade(int index, String palavra, int fase) {
        boolean isValid = false;
        HashMap<Integer, String> map = hashMap(fase);
        for (int key : map.keySet()) {
            if (palavra.equals(map.get(index))) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    private HashMap<Integer, String> hashMap(int fase) {
        HashMap<Integer, String> map = new HashMap<>();
        if (fase == 0) {
            map.put(0, "pa_azul");
            map.put(1, "pa_vermelha");
            map.put(2, "pa_amarela");
        } else if (fase == 1) {
            map.put(0, "pa_rosa");
            map.put(1, "pa_roxa");
            map.put(2, "pa_laranja");
//            map.put(3, "pa_vinho");
        }
        return map;
    }

    public void CreateElements() {
        findViewById(R.id.pa1).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.pa2).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.pa3).setOnLongClickListener(new MyOnLongClickListener());
//        findViewById(R.id.pa4).setOnLongClickListener(new MyOnLongClickListener());

        findViewById(R.id.primeiroBalde).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.segundoBalde).setOnDragListener(new MyOnDragListener(2));
        findViewById(R.id.terceiroBalde).setOnDragListener(new MyOnDragListener(3));
//        findViewById(R.id.quartoBalde).setOnDragListener(new MyOnDragListener(4));

    }

    private void setTags(int fase) {
        int vetor[] = null;
        int max = 0, min = 0;
        max = 2;
        int tamanho = 3;
        //tamanho do vetor que será criado

        vetor = new int[tamanho];

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = 100;
        }

        for (int i = 0; i < vetor.length; i++) {
            int numeroSorteado = Metodos.randomNumber(max, min);
            boolean valida = Metodos.validaNumero(vetor, numeroSorteado);
            if (valida) {
                vetor[i] = numeroSorteado;
            } else {
                boolean encontrou = false;
                while (!encontrou) {
                    numeroSorteado = Metodos.randomNumber(max, min);
                    valida = Metodos.validaNumero(vetor, numeroSorteado);
                    if (valida) {
                        vetor[i] = numeroSorteado;
                        encontrou = true;
                    }
                }
            }
        }

        if (fase == 0) {

            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.pa1);
            img.setImageResource(Metodos.getDrawableShovel(vetor[0], fase));
            img.setTag(Metodos.getDrawableShovel(vetor[0], fase));

            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.pa2);
            img.setImageResource(Metodos.getDrawableShovel(vetor[1], fase));
            img.setTag(Metodos.getDrawableShovel(vetor[1], fase));

            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.pa3);
            img.setImageResource(Metodos.getDrawableShovel(vetor[2], fase));
            img.setTag(Metodos.getDrawableShovel(vetor[2], fase));

            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.bld1);
            img.setImageResource(R.drawable.balde_azul);

            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.bld2);
            img.setImageResource(R.drawable.balde_vermelho);

            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.bld3);
            img.setImageResource(R.drawable.balde_amarelo);
        } else if (fase == 1) {
            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.pa1);
            img.setImageResource(Metodos.getDrawableShovel(vetor[0], fase));
            img.setTag(Metodos.getDrawableShovel(vetor[0], fase));

            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.pa2);
            img.setImageResource(Metodos.getDrawableShovel(vetor[1], fase));
            img.setTag(Metodos.getDrawableShovel(vetor[1], fase));

            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.pa3);
            img.setImageResource(Metodos.getDrawableShovel(vetor[2], fase));
            img.setTag(Metodos.getDrawableShovel(vetor[2], fase));

            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.bld1);
            img.setImageResource(R.drawable.balde_rosa);

            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.bld2);
            img.setImageResource(R.drawable.balde_roxo);

            img = new ImageView(this);
            img = (ImageView) findViewById(R.id.bld3);
            img.setImageResource(R.drawable.balde_laranja);

//            img = new ImageView(this);
//            img = (ImageView) findViewById(R.id.pa4);
//            img.setImageResource(Metodos.getDrawableShovel(vetor[3], fase));
//            img.setTag(Metodos.getDrawableContent(vetor[3], fase));

//            img = new ImageView(this);
//            img = (ImageView) findViewById(R.id.bld4);
//            img.setImageResource(R.drawable.balde_vinho);
        }
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
                        Intent nextActivity = new Intent(getBaseContext(), JogoDasPasActivity2.class);
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
                        if (getFase() <= TOTAL_FASE - 1) {
                            Intent nextActivity = new Intent(getBaseContext(), JogoDasPasActivity2.class);
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

}
