package br.senai.sp.agenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
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
        final Contato contato = helper.getContato();
        final ContatoDAO dao = new ContatoDAO(this);

        switch (item.getItemId()){
            case R.id.menu_salvar:
                    if(helper.validarVazio() && helper.validarPalavra(this)){
                        if (contato.getId() == 0) {
                            dao.salvar(contato);
                            Toast.makeText(this, contato.getNome() + " foi Gravado", Toast.LENGTH_LONG).show();
                        } else {
                            dao.atualizar(contato);
                            Toast.makeText(this, contato.getNome() + " foi Atualizado", Toast.LENGTH_LONG).show();
                        }
                        dao.close();
                        finish();
                    }
                break;

            case R.id.menu_del:

                if(contato.getId() == 0){
                    Toast.makeText(this, "Contato não existente", Toast.LENGTH_LONG).show();
                }else{
                    AlertDialog.Builder caixaDialogo = new AlertDialog.Builder(this);
                    caixaDialogo.setTitle("Exluindo um Contato");
                    caixaDialogo.setMessage("você tem certeza que quer excluir "+ contato.getNome() +" ?");
                    caixaDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dao.excluir(contato);
                            Toast.makeText(CadastroContatoActivity.this, "contato foi Excluído", Toast.LENGTH_LONG).show();
                            dao.close();
                            finish();
                        }
                    });
                    caixaDialogo.setNegativeButton("Não", null);
                    caixaDialogo.create().show();
                }


                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
