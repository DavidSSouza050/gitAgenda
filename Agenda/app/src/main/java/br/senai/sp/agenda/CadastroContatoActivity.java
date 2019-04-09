package br.senai.sp.agenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;

public class CadastroContatoActivity extends AppCompatActivity {
    public static final int REQUEST_GALERIA = 100;
    public static final int REQUEST_CAMERA = 999;
    CadastroContatoHelper helper;
    private ImageView img_foto_contato;
    private Button btn_camera;
    private Button btn_galeria;
    private String caminhpFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        helper = new CadastroContatoHelper(this);

        btn_camera = findViewById(R.id.bt_camera);
        btn_galeria = findViewById(R.id.bt_galeria);
        img_foto_contato = findViewById(R.id.contato_imagemView);

        //* pegando a intent
        Intent intent = getIntent();

        Contato contato = (Contato) intent.getSerializableExtra("contato");

        //verificando se o helper não é nulo para pegar um contato
        if(contato != null){
            helper.preencherFormulario(contato);
        }


        //*******CRIANDO INTENÇÕES PARA A CAMERA E A GALERIA*********//


        btn_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //INTENT QUE ESTÁ ABRINDO A GALERIA PADRÃO DO CELULAR
                Intent intentGaleria = new Intent(Intent.ACTION_GET_CONTENT);

                //SETANDO A IMAGEM EM QUALQUER FORMATO
                intentGaleria.setType("image/*");

                //STARTANDO A GALERIA E COLOCANDO UMA CONSTANT
                startActivityForResult(intentGaleria, REQUEST_GALERIA);
            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //criando um nome para a imagem que vai ser tirada
                String nomeArquivo = "/IMG_"+System.currentTimeMillis()+".jpg";

                //Abrir Camera
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Dando o nome criado para a foto
                caminhpFoto = getExternalFilesDir(null) + nomeArquivo;
                File arquivoFoto = new File(caminhpFoto);

                //criando a caminho para guadar a imagem

                Uri fotoUri = FileProvider.getUriForFile(
                        CadastroContatoActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        arquivoFoto);

                //Pendurando Na intent uma saida, que é o aquivo gerado pela camera
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);

                //STARTANDO A CAMERA E COLOCANDO UMA CONSTANT
                startActivityForResult(intentCamera, REQUEST_CAMERA);
            }
        });


        //*****************************************************************//
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK){
            try {
                switch (requestCode){
                    case REQUEST_GALERIA:
                        InputStream inputStream = getContentResolver().openInputStream(data.getData());

                        Bitmap bitmapFactory = BitmapFactory.decodeStream(inputStream);

                        img_foto_contato.setImageBitmap(bitmapFactory);

                        break;
                    case REQUEST_CAMERA:
                        Bitmap bitmap = BitmapFactory.decodeFile(caminhpFoto);
                        Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                        img_foto_contato.setImageBitmap(bitmapReduzido);

                        break;

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
                    Toast.makeText(this, "Contato não existe", Toast.LENGTH_LONG).show();
                }else{
                    AlertDialog.Builder caixaDialogo = new AlertDialog.Builder(this);
                    caixaDialogo.setTitle("Exluindo um Contato");
                    caixaDialogo.setMessage("você tem certeza que quer excluir "+ contato.getNome() +" ?");
                    caixaDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dao.excluir(contato);
                            Toast.makeText(CadastroContatoActivity.this, contato.getNome() + "contato foi Excluído", Toast.LENGTH_LONG).show();
                            dao.close();
                            finish();
                        }
                    });
                    caixaDialogo.setNegativeButton("Não", null);
                    caixaDialogo.create().show();
                }


                break;

            case R.id.menu_ligar:

                if(contato.getId() == 0){
                    Toast.makeText(CadastroContatoActivity.this, "Contato não existe", Toast.LENGTH_LONG).show();
                }else{
                    Uri uri = Uri.parse("tel:" + contato.getTelefone());
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
