package br.senai.sp.utils;

import android.content.Context;
import android.widget.Toast;

public class ValidacaoNumeroLetra {

    public boolean verificarLetra(Context activity, String palavra){

        int eNumero = 0;
        for(int cont =0; cont < palavra.length(); cont++) {
            if (Character.isLetter(palavra.charAt(cont)) || Character.isSpaceChar(palavra.charAt(cont))) {
            } else {
                eNumero++;
            }
        }
        if(eNumero != 0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean verificarNumero(Context activity, String numero){
        int eLetra = 0;
        for(int cont = 0; cont < numero.length() ; cont++){
            if(Character.isDigit(numero.charAt(cont)) || Character.isSpaceChar(numero.charAt(cont))){
            }else {
                eLetra++;
            }
        }
        if(eLetra != 0){
            return true;
        }else{
            return false;
        }
    }



}
