package com.example.fitnes_hanma.Admin;

import android.content.Context;
import android.content.SharedPreferences;
public class AdPreferenceManager {
    private static final String PREFERENCE_NAME = "MyPreferences";

    public static void setSelectedView(Context context, String viewName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selected_view", viewName);
        editor.apply();
    }

    public static String getSelectedView(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("selected_view", "clases"); // El valor por defecto es "clases"
    }
}