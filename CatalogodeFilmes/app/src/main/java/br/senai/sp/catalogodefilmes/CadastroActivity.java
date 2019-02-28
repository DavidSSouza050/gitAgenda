package br.senai.sp.catalogodefilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.senai.sp.dao.FilmeDAO;
import br.senai.sp.modelo.Filme;

public class CadastroActivity extends AppCompatActivity {
    CadastroFilmeHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        helper = new CadastroFilmeHelper(this);

        Intent intent = getIntent();

        Filme filme = (Filme) intent.getSerializableExtra("filme");

        if(filme != null){
            helper.preencherFormulario(filme);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro_filmes, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_salvar:
                Filme filme = helper.getFilme();

                FilmeDAO dao = new FilmeDAO(this);

                if(filme.getId() == 0){
                    dao.salvar(filme);
                    dao.close();
                    Toast.makeText(this, filme.getTitulo() + " gravado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    dao.atualizar(filme);
                    dao.close();
                    Toast.makeText(this, filme.getTitulo() + " foi atualizado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            case R.id.menu_del:

                break;
            case R.id.menu_configuracoes:

                break;
            default:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}