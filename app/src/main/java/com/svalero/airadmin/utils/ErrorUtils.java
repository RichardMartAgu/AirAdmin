package com.svalero.airadmin.utils;

import android.content.Context;

import com.svalero.airadmin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;

public class ErrorUtils {


        public static String parseError(Response<?> response, Context context) {
            try {
                JSONObject errorJson = new JSONObject(response.errorBody().string());
                return errorJson.optString("message", context.getString(R.string.error_unknown));
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return context.getString(R.string.error_response);

        }
    }
}
