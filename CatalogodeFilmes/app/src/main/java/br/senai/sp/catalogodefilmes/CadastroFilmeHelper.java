package br.senai.sp.catalogodefilmes;

import android.widget.EditText;
import android.widget.RatingBar;

import br.senai.sp.modelo.Filme;

public class CadastroFilmeHelper {
    private EditText txtTitle, txtDiretor, txtGenero, txtDataDeLancamento, txtDuracao;
    private Filme filme;
    private RatingBar nota;


    public CadastroFilmeHelper(CadastroActivity activity) {
        txtTitle = activity.findViewById(R.id.txt_titulo);
        txtDiretor = activity.findViewById(R.id.txt_diretor);
        txtGenero = activity.findViewById(R.id.txt_genero);
        txtDataDeLancamento = activity.findViewById(R.id.txt_data);
        txtDuracao = activity.findViewById(R.id.txt_duracao);
        nota = activity.findViewById(R.id.nota_filme);
        filme = new Filme();
    }

    public Filme getFilme() {
        filme.setDataLancamento(txtDataDeLancamento.getText().toString());
        filme.setDiretor(txtDiretor.getText().toString());
        filme.setDuracao(txtDuracao.getText().toString());
        filme.setGenero(txtGenero.getText().toString());
        filme.setTitulo(txtTitle.getText().toString());
        filme.setNota(nota.getProgress());

        return filme;
    }

    public void preencherFormulario(Filme filme) {
        txtTitle.setText(filme.getTitulo());
        txtDataDeLancamento.setText(filme.getDataLancamento());
        txtDiretor.setText(filme.getDiretor());
        txtDuracao.setText(filme.getDuracao());
        txtGenero.setText(filme.getGenero());
        nota.setProgress(filme.getNota());
        this.filme = filme;
    }
}