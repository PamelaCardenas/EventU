package com.example.eventos_usr;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * Karla Pamela Cárdenas Leyva 18550338
 * Establecer el formato y el DatePicker para obtener la fecha
 * */

public class formatoFecha extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private EditText edTxtFecha;

    public static formatoFecha newInstance(EditText edTxtFecha){
        formatoFecha formato = new formatoFecha();
        formato.setEditTextFecha(edTxtFecha);

        return formato;
    }

    private void setEditTextFecha(EditText edTxtFecha) {
        this.edTxtFecha = edTxtFecha;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //Se define el formato al obtener la fecha con el DatePicker y obtener los valores correspondientes
        //Dia - Mes - Año
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        if(this.edTxtFecha.getText().toString().length()>0){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate = null;
            try{
                parsedDate = format.parse(this.edTxtFecha.getText().toString());
                c.setTime(parsedDate);
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new DatePickerDialog(getActivity(),this,year,month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        //Se muestra en el editText
        String selectedDate = String.format("%02d", day) + "/" + String.format("%02d", (month+1)) + "/" + String.format("%04d", year);
        this.edTxtFecha.setText(selectedDate);
    }

}
