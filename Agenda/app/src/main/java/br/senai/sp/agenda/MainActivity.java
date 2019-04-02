package br.senai.sp.agenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.adapter.ContatoListAdapter;
import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;

public class MainActivity extends AppCompatActivity {
    private ListView listaContatos;
    private Button btNovoContato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**/
        listaContatos = findViewById(R.id.list_contatos);
        /*botao que leva para o cadastro*/
        btNovoContato = findViewById(R.id.bt_novo_contato);

        btNovoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent criarContato = new Intent(MainActivity.this, CadastroContatoActivity.class);
                startActivity(criarContato);
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        /**/
        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contato = (Contato) listaContatos.getItemAtPosition(position);
                Intent abrirCadas = new Intent(MainActivity.this, CadastroContatoActivity.class);
                abrirCadas.putExtra("contato", contato);
                startActivity(abrirCadas);
            }
        });

            registerForContextMenu(listaContatos);
//        EXEMPLO DE ADAPTER PARA ARRAY DE Strings
//        String[] contatos = {"David Silva", "ralapago marguinhos", "O Miranha", "Jubileo",
//                                "David Silva", "ralapago marguinhos", "O Miranha", "Jubileo"};
//
//        ArrayAdapter<String> contatoAdapiter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contatos);
//        listaContatos.setAdapter(contatoAdapiter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuContexto = getMenuInflater();
        menuContexto.inflate(R.menu.manu_context_lista_contatos, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final ContatoDAO dao = new ContatoDAO(this);
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Contato contato = (Contato) listaContatos.getItemAtPosition(info.position);

        AlertDialog.Builder caixaDialogo = new AlertDialog.Builder(this);
        caixaDialogo.setTitle("Exluindo um Contato");
        caixaDialogo.setMessage("você tem certeza que quer excluir "+ contato.getNome() +" ?");
        caixaDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.excluir(contato);
                Toast.makeText(MainActivity.this, contato.getNome() + " foi Excluído", Toast.LENGTH_LONG).show();
                dao.close();
                mostrarContato();
            }
        });
        caixaDialogo.setNegativeButton("Não", null);
        caixaDialogo.create().show();
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        mostrarContato();
        super.onResume();
    }

    private void mostrarContato(){
        //***Pegando os contatos
        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> contatos = dao.getContato();
        dao.close();

        //** Adaptando para a list view
        ContatoListAdapter adapterContato = new ContatoListAdapter(this, contatos);

        listaContatos.setAdapter(adapterContato);
    }


}
