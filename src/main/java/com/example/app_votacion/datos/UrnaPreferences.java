package com.example.app_votacion.datos;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UrnaPreferences {
    private static final String PREF_NAME = "UrnaPrefs";
    private static final String KEY_CANDIDATOS = "candidatos";

    private static UrnaPreferences instance;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public UrnaPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    public static UrnaPreferences getInstance(Context context) {
        if (instance == null) {
            instance = new UrnaPreferences(context);
        }
        return instance;
    }

    public List<Candidato> getCandidatos() {
        String candidatosJson = sharedPreferences.getString(KEY_CANDIDATOS, null);
        if (candidatosJson == null) {
            return new ArrayList<>();
        }
        Type listType = new TypeToken<List<Candidato>>() {}.getType();
        return gson.fromJson(candidatosJson, listType);
    }

    public void saveCandidatos(List<Candidato> candidatos) {
        String jsonCandidatos = gson.toJson(candidatos);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CANDIDATOS, jsonCandidatos);
        editor.apply();
    }
}