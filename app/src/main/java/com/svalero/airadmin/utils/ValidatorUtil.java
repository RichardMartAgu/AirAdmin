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

    public static boolean areEditTextsValid(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (!isEditTextNotEmpty(editText)) {
                return false;
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
            return false;
        }
    }

}


