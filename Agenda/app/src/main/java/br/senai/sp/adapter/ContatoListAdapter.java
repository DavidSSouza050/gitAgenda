package br.senai.sp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.senai.sp.agenda.R;
import br.senai.sp.conversores.Imagem;
import br.senai.sp.modelo.Contato;

public class ContatoListAdapter extends BaseAdapter {
    private Context context;
    private List<Contato> contatos;

//    construtor para trazer as contantes para a class
    public ContatoListAdapter(Context context, List<Contato> contatos){
        this.context = context;
        this.contatos = contatos;
    }
    //retornando a linha que o contato foi clickado
    @Override
    public int getCount() {
        return contatos.size();
    }
    //retornando a posição do cadastro que foi clickado
    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }
    //pegando a posição e o id
    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }
    //atribuindo os atributos nos lugares determinados pegando a posição
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Criando varivel para facilitar digitção
        Contato contato = contatos.get(position);
        //criando um inflate para inflar a lista criada
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_contato, null);

        //Preenchento atributos para a lista contatos
        TextView txtNome = view.findViewById(R.id.txt_nome_contato);
        txtNome.setText(contato.getNome());

        TextView txtEmail = view.findViewById(R.id.txt_email_contato);
        txtEmail.setText(contato.getEmail());

        TextView txtTelefone = view.findViewById(R.id.txt_telefone_contato);
        txtTelefone.setText(contato.getTelefone());

        ImageView imgFoto = view.findViewById(R.id.imagem_contato);
        imgFoto.setImageBitmap(Imagem.arrayToBitmap(contato.getFoto()));

        return view;
    }
}
