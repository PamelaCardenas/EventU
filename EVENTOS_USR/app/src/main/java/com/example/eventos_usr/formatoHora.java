package com.example.eventos_usr;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

/*
 * Karla Pamela Cárdenas Leyva 18550338
 * Establecer el formato y el TimePicker para obtener la hora
 * */

public class formatoHora extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private EditText edTxtHora;

    public static formatoHora newInstance(EditText edTxtHora){
        formatoHora formato = new formatoHora();
        formato.setEditTextHora(edTxtHora);

        return formato;
    }

    private void setEditTextHora(EditText edTxtHora) {
        this.edTxtHora = edTxtHora;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Establecer el formato del TimePicker y obtener los valores correspondientes
       final Calendar c = Calendar.getInstance();
       int hour = c.get(Calendar.HOUR_OF_DAY);
       int minute = c.get(Calendar.MINUTE);

       return new TimePickerDialog((getActivity()),this,hour,minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Se muestra en el editText
        String selectTime = String.format("%02d", hourOfDay) + ":" +String.format("%02d", minute);
        this.edTxtHora.setText(selectTime);
    }
}
