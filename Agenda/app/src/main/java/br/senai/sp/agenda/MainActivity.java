package br.senai.sp.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        String[] contatos = {"David Silva", "ralapago marguinhos", "O Miranha", "Jubileo",
                                "David Silva", "ralapago marguinhos", "O Miranha", "Jubileo"};

        ArrayAdapter<String> contatoAdapiter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contatos);
        listaContatos.setAdapter(contatoAdapiter);
    }
}
