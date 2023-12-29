package com.svalero.airadmin.utils;

import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ValidatorUtil {


    public static boolean isEditTextNotEmpty(EditText editText) {
        String text = editText.getText().toString().trim();
        return !text.isEmpty();
    }

    // Método para validar varios EditText a la vez
    public static boolean areEditTextsValid(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (!isEditTextNotEmpty(editText)) {
                return false;  // Al menos uno es nulo o está vacío
            }
        }
        return true;
    }


    public static boolean isEditTextDouble(EditText editText) {
        try {
            String text = editText.getText().toString().trim();
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;  // No se puede convertir a double
        }
    }

    public static boolean isEditTextValidDate(EditText editText) {
        try {
            String text = editText.getText().toString().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // Hace que la validación sea estricta
            Date date = sdf.parse(text);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}


