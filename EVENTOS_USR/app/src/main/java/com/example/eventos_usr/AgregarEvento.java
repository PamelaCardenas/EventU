package com.example.eventos_usr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eventos_usr.utilidades.Utilidades;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/*
 * Karla Pamela Cárdenas Leyva 18550338
 * Métodos para poder agregar un evento
 * */

public class AgregarEvento extends AppCompatActivity {

    final int INFO_IMAGENES = 100;
    Intent intentImage;
    ImageView imgVwEvAg;
    EditText edTxtTit,edTxtDesc,edTxtFecha,edTxtHora,edTxtOrg;

    public static ConexionSQLHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_evento);
        //Se recibe el id de cada uno de los componentes
        imgVwEvAg = findViewById(R.id.imgVwEvAg);
        edTxtTit=findViewById(R.id.edTxtTit);
        edTxtDesc=findViewById(R.id.edTxtDesc);
        edTxtFecha=findViewById(R.id.edTxtFecha);
        edTxtHora=findViewById(R.id.edTxtHora);
        edTxtOrg=findViewById(R.id.edTxtOrg);


        //Contexto, nombre de base de datos, parametro factory y version de la bd
        sqLiteHelper =new ConexionSQLHelper(this,"bd_eventos.sqlite",null,1);

        //Se llama la consulta para crear la tabla de la clase Utilidades
        sqLiteHelper.datosConsulta(Utilidades.CREAR_TABLA_EVENTOS);

        //Intento para seleccionar imagen de galeria
        intentImage = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

    }


    //Se hace la imagen como mapa de bits para poder guardarse en la base de datos
    private byte[] imgVwEvAgToByte(ImageView imgVw) {
        Bitmap bitmap = ((BitmapDrawable)imgVw.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    //Para seleccionar la imagen de la galeria
    public void onClickImage(View v){
        startActivityForResult(intentImage, INFO_IMAGENES);
    }

    //Generar el onClick para agregar un evento
    public void onClickRegEv(View view) {
        try {
            //Se obtienen los textos de los editText y se insertan en la base de datos
            sqLiteHelper.insertarDatos(
                    edTxtTit.getText().toString().trim(),
                    edTxtDesc.getText().toString().trim(),
                    edTxtFecha.getText().toString().trim(),
                    edTxtHora.getText().toString().trim(),
                    edTxtOrg.getText().toString().trim(),
                    imgVwEvAgToByte(imgVwEvAg)
            );
            //Al terminar se muestra un Toast para indicar que se agregaron los datos
            Toast.makeText(getApplicationContext(),"Se agregó el evento",Toast.LENGTH_SHORT).show();
            //se vacian los campos
            edTxtTit.setText("");
            edTxtDesc.setText("");
            edTxtFecha.setText("");
            edTxtHora.setText("");
            edTxtOrg.setText("");
            imgVwEvAg.setImageResource(R.mipmap.ic_launcher);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onClickCancel (View v){
        //Abre la MainActivity cuando seleccionamos el botón cancelar
        Intent intent = new Intent(AgregarEvento.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == INFO_IMAGENES) {
            Uri uri = data.getData();
            //Volver la imagen en un mapa de bits
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgVwEvAg.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }



    //Selector de fecha
    public void onClickFormatoFecha(View v) {
        switch (v.getId()){
            case R.id.edTxtFecha:
                showDatePickerDialog((EditText) v);
                break;
        }
    }

    private void showDatePickerDialog(EditText v) {
        DialogFragment newFragment = formatoFecha.newInstance(v);
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    //Selector de Hora
    public void onClickFormatoHora(View v) {
        switch (v.getId()){
            case R.id.edTxtHora:
                showTimePickerDialog((EditText) v);
                break;
        }
    }

    private void showTimePickerDialog(EditText v) {
        DialogFragment newFragment = formatoHora.newInstance(v);
        newFragment.show(getSupportFragmentManager(),"timePicker");
    }
}