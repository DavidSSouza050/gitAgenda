package br.senai.sp.agenda;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.ImageView;

import br.senai.sp.conversores.Imagem;
import br.senai.sp.modelo.Contato;
import br.senai.sp.utils.ValidacaoNumeroLetra;

public class CadastroContatoHelper {
    private EditText txtNome, txtEndereco, txtTelefone, txtEmail, txtLinkedin;
    private TextInputLayout layoutTxtNome, layoutTxtEndereco, layoutTxtEmail, layoutTxtTelefone, layoutTxtLinkedin;
    private Contato contato;
    private ImageView imgFoto;

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
        imgFoto = activity.findViewById(R.id.contato_imagemView);
        contato = new Contato();
    }

    public Contato getContato() {
        contato.setNome(txtNome.getText().toString());
        contato.setEmail(txtEmail.getText().toString());
        contato.setEndereco(txtEndereco.getText().toString());
        contato.setTelefone(txtTelefone.getText().toString());
        contato.setLinkedin(txtLinkedin.getText().toString());

        contato.setFoto(Imagem.imageViewToArray(imgFoto));

        return contato;
    }

    public void preencherFormulario (Contato contato){
        txtNome.setText(contato.getNome());
        txtEndereco.setText(contato.getEndereco());
        txtTelefone.setText(contato.getTelefone());
        txtLinkedin.setText(contato.getLinkedin());
        txtEmail.setText(contato.getEmail());

        if(contato.getFoto() != null){
            imgFoto.setImageBitmap(Imagem.arrayToBitmap(contato.getFoto()));
        }

        this.contato = contato;
    }

    public boolean validarVazio(){
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



    public boolean validarPalavra(Context activity){
        ValidacaoNumeroLetra validaPalavra = new ValidacaoNumeroLetra();
        boolean palavraValidada = true;

        String nome = txtNome.getText().toString();
        String email = txtEmail.getText().toString();
        String telefone = txtTelefone.getText().toString();
        String linkedin = txtLinkedin.getText().toString();

        if (validaPalavra.verificarLetra(activity, nome)){
            layoutTxtNome.setErrorEnabled(true);
            layoutTxtNome.setError("Preencha essa caixa Corretamente!!!");
            palavraValidada = false;
        }else{
            layoutTxtNome.setErrorEnabled(false);
        }

        if(!telefone.matches(("[0-9]{2}[ ]?9?[0-9]{4}-?[0-9]{4}"))){
            layoutTxtTelefone.setErrorEnabled(true);
            layoutTxtTelefone.setError("Preencha essa caixa Corretamente!!!");
            palavraValidada = false;
        }else{
            layoutTxtTelefone.setErrorEnabled(false);
        }

        if(!email.matches(("[0-9a-zA-Z._-]+@[a-z]+.[a-z]+"))){
            layoutTxtEmail.setErrorEnabled(true);
            layoutTxtEmail.setError("Faça o E-mail de maneira correta!!");
            palavraValidada = false;
        }else{
            layoutTxtEmail.setErrorEnabled(false);
        }


        return palavraValidada;
    }


}
