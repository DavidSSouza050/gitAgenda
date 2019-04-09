package br.senai.sp.conversores;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class Imagem {


    public static Bitmap arrayToBitmap(byte[] imagemArray){

        Bitmap bitmap = BitmapFactory.decodeByteArray(imagemArray, 0, imagemArray.length);

        return bitmap;
    }

    public static byte[] imageViewToArray(ImageView fotobitmap){

        // TRANSFORMANDO IMAGEM DE IMAGEVIEW PARA BITMAP
        Bitmap bitmap = ((BitmapDrawable) fotobitmap.getDrawable()).getBitmap();
        Bitmap bitmapReduzido = bitmap.createScaledBitmap(bitmap, 300, 300, true);

        // CONVERTER O BITMAP EM UM BYTEARRAY
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapReduzido.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte[] fotoArray = byteArrayOutputStream.toByteArray();


        return fotoArray;
    }


}
