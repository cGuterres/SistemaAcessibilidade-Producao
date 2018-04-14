package com.dev2.sa.sistemaacessibilidade.activities;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
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

public class JodoDoCorpo extends AppCompatActivity {

    private int fase = 0, acerto = 0;
    private final int PONTO_ACERTO = 10;
    private final int PONTO_ERRO = 5;
    private final int TOTAL_ACERTO = 8;
    private final int TOTAL_FASE = 1;
    private int pontuacao = 0;

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    private ImageView img = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_do_corpo);
        setTags();
        createElements();
    }

    private void createElements(){
        findViewById(R.id.palavra_cabeca).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavra_braco).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavra_joelho).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavra_pes).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavra_coxa).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavra_perna).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavra_ombro).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.palavra_mao).setOnLongClickListener(new MyOnLongClickListener());

        findViewById(R.id.linearLayout4).setOnDragListener(new MyOnDragListener(1));
        findViewById(R.id.linearLayout5).setOnDragListener(new MyOnDragListener(2));
        findViewById(R.id.linearLayout6).setOnDragListener(new MyOnDragListener(3));
        findViewById(R.id.linearLayout7).setOnDragListener(new MyOnDragListener(4));
        findViewById(R.id.linearLayout8).setOnDragListener(new MyOnDragListener(5));
        findViewById(R.id.linearLayout9).setOnDragListener(new MyOnDragListener(6));
        findViewById(R.id.box_pes).setOnDragListener(new MyOnDragListener(7));
        findViewById(R.id.box_perna).setOnDragListener(new MyOnDragListener(8));
    }

    class MyOnLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            //DragShadowBuilder sb = new View.DragShadowBuilder(findViewById(R.id.shadow));
            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);
            // seta a visibilidade de quem está sendo movido para invisível
            v.setVisibility(View.INVISIBLE);
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
            View view = (View) event.getLocalState();//aqui entra quem está sendo movido
            switch (action) {
                // Quando tem ação de drop deixa a sombra sempre visível
                case DragEvent.ACTION_DRAG_ENDED:
                    //qualquer evento de drag que acabar vai setar a figura para visível, independente do lugar que a figura cair
                    view.setVisibility(View.VISIBLE);
                    break;

                case DragEvent.ACTION_DROP:
                    String box = "";
                    if (v.getId() == R.id.linearLayout6) { // Clicou na primeira imagem e largou na view da esquerda
                        box = "braco";
                    } else if (v.getId() == R.id.linearLayout4) { // Clicou na segunda imagem e largou na view da esquerda
                        box = "cabeca";
                    } else if (v.getId() == R.id.linearLayout5) { // Clicou na terceira imagem e largou na view da esquerda
                        box = "ombro";
                    }else if (v.getId() == R.id.linearLayout7) { // Clicou na segunda imagem e largou na view da esquerda
                        box = "mao";
                    } else if (v.getId() == R.id.linearLayout9) { // Clicou na terceira imagem e largou na view da esquerda
                        box = "coxa";
                    }else if (v.getId() == R.id.linearLayout8) { // Clicou na segunda imagem e largou na view da esquerda
                        box = "joelho";
                    } else if (v.getId() == R.id.box_pes) { // Clicou na terceira imagem e largou na view da esquerda
                        box = "pes";
                    }else if (v.getId() == R.id.box_perna) { // Clicou na terceira imagem e largou na view da esquerda
                        box = "perna";
                    }
                    String movido = Metodos.getPalavraCorpo(Integer.parseInt(view.getTag().toString()));

                    if(movido.toLowerCase().equals(box.toLowerCase())){
                        int rawId = Metodos.getSomCorpo(movido);
                        Metodos.defaultSound(rawId, JodoDoCorpo.this);
                        Toast.makeText(JodoDoCorpo.this, "VOCÊ ACERTOU!", Toast.LENGTH_SHORT).show();
                        int total = Metodos.somaTotal(pontuacao, PONTO_ACERTO, true);
                        setPontuacao(total);
                        acerto++;
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        view.setEnabled(false);
                        v.setEnabled(false);

                    }
                    else{
                        // mensagem de erro para o usuário
                        Toast.makeText(JodoDoCorpo.this, "VOCÊ ERROU!", Toast.LENGTH_SHORT).show();
                        int total = Metodos.somaTotal(pontuacao,PONTO_ERRO, false);
                        setPontuacao(total);
                    }

                    if (acerto == TOTAL_ACERTO) {
                        Metodos.ShowDialog(JodoDoCorpo.this, R.drawable.icotrofeu, "PARABÉNS", "VOCÊ GANHOU!");
                    }
                    TextView mostra = (TextView)findViewById(R.id.txtPonto);
                    int pontuacaoAtual = getPontuacao();
                    mostra.setText(Integer.toString(pontuacaoAtual));
                    break;

            }
            return true;
        }
    }

    private void setTags() {
        int[] vetor = new int[8];
        // troca o valor para que nao seja validado de forma errada quando o vetor for iniciado com 0
        int max = 7, min = 0;
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

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.palavra_cabeca);
        img.setImageResource(Metodos.setPalavraCorpoId(vetor[0]));
        img.setTag(Metodos.setPalavraCorpoId(vetor[0]));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.palavra_pes);
        img.setImageResource(Metodos.setPalavraCorpoId(vetor[1]));
        img.setTag(Metodos.setPalavraCorpoId(vetor[1]));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.palavra_mao);
        img.setImageResource(Metodos.setPalavraCorpoId(vetor[2]));
        img.setTag(Metodos.setPalavraCorpoId(vetor[2]));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.palavra_perna);
        img.setImageResource(Metodos.setPalavraCorpoId(vetor[3]));
        img.setTag(Metodos.setPalavraCorpoId(vetor[3]));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.palavra_ombro);
        img.setImageResource(Metodos.setPalavraCorpoId(vetor[4]));
        img.setTag(Metodos.setPalavraCorpoId(vetor[4]));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.palavra_joelho);
        img.setImageResource(Metodos.setPalavraCorpoId(vetor[5]));
        img.setTag(Metodos.setPalavraCorpoId(vetor[5]));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.palavra_braco);
        img.setImageResource(Metodos.setPalavraCorpoId(vetor[6]));
        img.setTag(Metodos.setPalavraCorpoId(vetor[6]));

        img = new ImageView(this);
        img = (ImageView) findViewById(R.id.palavra_coxa);
        img.setImageResource(Metodos.setPalavraCorpoId(vetor[7]));
        img.setTag(Metodos.setPalavraCorpoId(vetor[7]));
    }


}
