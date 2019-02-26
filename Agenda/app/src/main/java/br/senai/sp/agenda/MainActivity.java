package br.senai.sp.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;

public class MainActivity extends AppCompatActivity {
    private ListView listaContatos;
    private Button btNovoContato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContatos = findViewById(R.id.list_contatos);

        btNovoContato = findViewById(R.id.bt_novo_contato);

        btNovoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "ae boyyy", Toast.LENGTH_LONG).show;
                Intent criarContato = new Intent(MainActivity.this, CadastroContatoActivity.class);
                startActivity(criarContato);
            }
        });

//        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });


//        EXEPLO DE ADAPTER PARA ARRAY DE Strings
//        String[] contatos = {"David Silva", "ralapago marguinhos", "O Miranha", "Jubileo",
//                                "David Silva", "ralapago marguinhos", "O Miranha", "Jubileo"};
//
//        ArrayAdapter<String> contatoAdapiter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contatos);
//        listaContatos.setAdapter(contatoAdapiter);
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
        ArrayAdapter<Contato> adapterContato = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);
        listaContatos.setAdapter(adapterContato);
    }



}
