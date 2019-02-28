package br.senai.sp.calculadoradeimc;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout layoutTxtPeso;
    private TextInputLayout layoutTxtAltura;

    private EditText txtPeso;
    private EditText txtAltura;
    private ImageButton btCalcular;
    private ImageButton btLimpar;

    private TextView viewImc;
    private TextView viewResultado;
    private TextView viewResumo;

    private RelativeLayout cardResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //texto
        layoutTxtAltura = findViewById(R.id.layout_txt_altura);
        layoutTxtPeso = findViewById(R.id.layout_txt_peso);
        txtPeso = findViewById(R.id.txt_peso);
        txtAltura = findViewById(R.id.txt_altura);
        //botão
        btCalcular = findViewById(R.id.btn_calcular);
        btLimpar = findViewById(R.id.btn_limpar);
        //view
        viewImc = findViewById(R.id.view_imc);
        viewResultado = findViewById(R.id.view_resultado);
        viewResumo = findViewById(R.id.view_resumo);
        //card
        cardResultado = findViewById(R.id.card_resultado);

        cardResultado.setVisibility(View.INVISIBLE);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validar()){
                    calcularImc();
                }
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparActivity();
            }
        });
    }

    private void calcularImc(){
        DecimalFormat df = new DecimalFormat(".##");
        double peso = Double.parseDouble(txtPeso.getText().toString());
        double altura = Double.parseDouble(txtAltura.getText().toString());
        double imc = peso / Math.pow(altura, 2);

        if(imc < 15){
            viewResultado.setText(getResources().getText(R.string.abaixo_do_peso_1_titulo));
            viewResumo.setText(getResources().getText(R.string.abaixo_do_peso_1));
        }else if(imc <= 18.5){
            viewResultado.setText(getResources().getText(R.string.abaixo_do_peso_titulo));
            viewResumo.setText(getResources().getText(R.string.abaixo_do_peso_desc));
        }else if(imc <= 24.9){
            viewResultado.setText(getResources().getText(R.string.peso_normal_titulo));
            viewResumo.setText(getResources().getText(R.string.peso_norma_desc));
        }else if(imc <= 29.9){
            viewResultado.setText(getResources().getText(R.string.acima_do_peso_titulo));
            viewResumo.setText(getResources().getText(R.string.acima_do_peso_desc));
        }else if(imc <= 39.9){
            viewResultado.setText(getResources().getText(R.string.Obesidade_Grau_II_Titulo));
            viewResumo.setText(getResources().getText(R.string.obesidade_Grau_II_desc));
        }else if(imc > 40){
            viewResultado.setText(getResources().getText(R.string.Obesidade_Grau_III_Titulo));
            viewResumo.setText(getResources().getText(R.string.obesidade_Grau_III_desc));
        }


        viewImc.setText(df.format(imc));
        cardResultado.setVisibility(View.VISIBLE);
    }

    private void limparActivity(){
        txtAltura.setText("");
        txtPeso.setText("");
        cardResultado.setVisibility(View.INVISIBLE);
        layoutTxtPeso.setErrorEnabled(false);
        layoutTxtAltura.setErrorEnabled(false);
        txtPeso.requestFocus();
    }

    private boolean validar(){

        boolean validado = true;

        if(txtPeso.getText().toString().isEmpty()){
            layoutTxtPeso.setErrorEnabled(true);
            layoutTxtPeso.setError("Por Favor digite seu peso!");
            validado = false;
        }else{
            layoutTxtPeso.setErrorEnabled(false);
        }

        if (txtAltura.getText().toString().isEmpty()) {
            layoutTxtAltura.setErrorEnabled(true);
            layoutTxtAltura.setError("Por Favor digite sua altura!");
            validado = false;
        }else{
            layoutTxtAltura.setErrorEnabled(false);
        }

        return validado;
    }


}
