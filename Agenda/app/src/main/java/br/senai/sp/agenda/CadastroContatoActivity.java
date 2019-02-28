package br.senai.sp.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;

public class CadastroContatoActivity extends AppCompatActivity {
    CadastroContatoHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        helper = new CadastroContatoHelper(this);

        Intent intent = getIntent();

        Contato contato = (Contato) intent.getSerializableExtra("contato");

        if(contato != null){
            helper.preencherFormulario(contato);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuCadastro = getMenuInflater();
        menuCadastro.inflate(R.menu.menu_cadastro_contato, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_salvar:
                    Contato contato = helper.getContato();
                    ContatoDAO dao = new ContatoDAO(this);

                    dao.salvar(contato);

                    Toast.makeText(this, contato.getNome() + " foi gravado salvo", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_del:
                    Toast.makeText(this, "Excluirdo", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
