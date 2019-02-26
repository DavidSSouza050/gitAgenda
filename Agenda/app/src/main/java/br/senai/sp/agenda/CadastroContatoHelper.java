package br.senai.sp.agenda;

import android.widget.EditText;

import br.senai.sp.modelo.Contato;

public class CadastroContatoHelper {
    private EditText txtNome, txtEndereco, txtTelefone, txtEmail, txtLinkedin;
    private Contato contato;

    public CadastroContatoHelper(CadastroContatoActivity activity){
        txtNome = activity.findViewById(R.id.txt_nome);
        txtEmail = activity.findViewById(R.id.txt_email);
        txtEndereco = activity.findViewById(R.id.txt_endereco);
        txtLinkedin = activity.findViewById(R.id.txt_linkedin);
        txtTelefone = activity.findViewById(R.id.txt_telefone);
        contato = new Contato();
    }

    public Contato getContato() {
        contato.setNome(txtNome.getText().toString());
        contato.setEmail(txtEmail.getText().toString());
        contato.setEndereco(txtEndereco.getText().toString());
        contato.setTelefone(txtTelefone.getText().toString());
        contato.setLinkedin(txtLinkedin.getText().toString());
        return contato;
    }

}
