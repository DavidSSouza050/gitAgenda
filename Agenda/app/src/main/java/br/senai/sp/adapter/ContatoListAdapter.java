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

    public ContatoListAdapter(Context context, List<Contato> contatos){
        this.context = context;
        this.contatos = contatos;
    }


    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Criando varivel para facilitar digitção
        Contato contato = contatos.get(position);
        //criando um inflate para inflar a lista crianda
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_contato, null);

        //Preenchento atributos para a lista contatos
        TextView txtNome = view.findViewById(R.id.txt_nome_contato);
        txtNome.setText(contato.getNome());

        TextView txtEmail = view.findViewById(R.id.txt_email_contato);
        txtEmail.setText(contato.getEmail());

        TextView txtTelefone = view.findViewById(R.id.txt_telefone_contato);
        txtTelefone.setText(contato.getTelefone());

        ImageView imgFoto = view.findViewById(R.id.contato_imagemView);
        imgFoto.setImageBitmap(Imagem.arrayToBimap(contato.getFoto()));


        return view;
    }
}
