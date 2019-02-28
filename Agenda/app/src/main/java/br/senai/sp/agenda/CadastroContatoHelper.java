package br.senai.sp.agenda;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.senai.sp.modelo.Contato;

public class CadastroContatoHelper {
    private EditText txtNome, txtEndereco, txtTelefone, txtEmail, txtLinkedin;
    private TextInputLayout layoutTxtNome, layoutTxtEndereco, layoutTxtEmail, layoutTxtTelefone, layoutTxtLinkedin;
    private Contato contato;

    public CadastroContatoHelper(CadastroContatoActivity activity){

        layoutTxtNome = activity.findViewById(R.id.layout_txt_nome);
        layoutTxtEmail = activity.findViewById(R.id.layout_txt_email);
        layoutTxtEndereco = activity.findViewById(R.id.layout_txt_endereco);
        layoutTxtTelefone = activity.findViewById(R.id.layout_txt_telefone);
        layoutTxtLinkedin = activity.findViewById(R.id.layout_txt_linkedin);


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

    public void preencherFormulario (Contato contato){
        txtNome.setText(contato.getNome());
        txtEndereco.setText(contato.getEndereco());
        txtTelefone.setText(contato.getTelefone());
        txtLinkedin.setText(contato.getLinkedin());
        txtEmail.setText(contato.getEmail());
        this.contato = contato;
    }

    public boolean validar(){
        boolean validado = true;

        if(txtNome.getText().toString().isEmpty()){
            layoutTxtNome.setErrorEnabled(true);
            layoutTxtNome.setError("Preencha essa caixa!!!");
            validado = false;
        }else{
            layoutTxtNome.setErrorEnabled(false);
        }

        if(txtEmail.getText().toString().isEmpty()){
            layoutTxtEmail.setErrorEnabled(true);
            layoutTxtEmail.setError("Preencha essa caixa!!!");
            validado = false;
        }else{
            layoutTxtEmail.setErrorEnabled(false);
        }

        if(txtTelefone.getText().toString().isEmpty()){
            layoutTxtTelefone.setErrorEnabled(true);
            layoutTxtTelefone.setError("Preencha essa caixa!!!");
            validado = false;
        }else{
            layoutTxtTelefone.setErrorEnabled(false);
        }

        if (txtEndereco.getText().toString().isEmpty()){
            layoutTxtEndereco.setErrorEnabled(true);
            layoutTxtEndereco.setError("Preencha essa caixa!!!");
            validado = false;
        }else{
            layoutTxtEndereco.setErrorEnabled(false);
        }

        if (txtLinkedin.getText().toString().isEmpty()){
            layoutTxtLinkedin.setErrorEnabled(true);
            layoutTxtLinkedin.setError("Preencha essa caixa!!!");
            validado = false;
        }else{
            layoutTxtLinkedin.setErrorEnabled(false);
        }
        return validado;
    }

}
