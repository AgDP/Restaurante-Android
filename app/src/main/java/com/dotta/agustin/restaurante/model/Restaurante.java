package com.dotta.agustin.restaurante.model;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Restaurante {
    public static final String PREF_RESTAURANTES = "com.dotta.agustin.restaurante.model.PREF_RESTAURANTES";

    private ArrayList<Mesa> mMesas;
    private ArrayList<Plato> mPlatos;

    private static Restaurante ourInstance;

    private static final String platosURL = "http://www.mocky.io/v2/5601a41c5aa981ee01d22003";

    private WeakReference<Context> mContext;

    public static Restaurante getInstance(Context context) throws IOException, JSONException {
        if (ourInstance == null || ourInstance.mContext.get() == null) {
            synchronized (PREF_RESTAURANTES) {
                if (ourInstance == null) {
                    // 2) No tenemos la instancia, vamos a crearla a partir de las preferencias
                    ourInstance = new Restaurante(context);
                } else if (ourInstance.mContext.get() == null) {
                    // 3) He perdido la referencia al contexto, no puedo seguir usando el objeto
                    // sin ella, así que la creo de nuevo
                    ourInstance.mContext = new WeakReference<>(context);
                }
            }
        }

        return ourInstance;
    }

    public Restaurante(Context context) throws IOException, JSONException {

        mContext = new WeakReference<>(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
            StrictMode.setThreadPolicy(policy);
        }
        downloadPlatos();
        downloadMesas();

    }

    private void downloadMesas() {

        mMesas = new ArrayList<Mesa>();
        for (int i = 0; i < 10; i++){
            Mesa mesa = new Mesa(i,0,new ArrayList<Plato>());

            mMesas.add(mesa);
        }

    }

    private void downloadPlatos() throws IOException, JSONException {

        this.mPlatos = new ArrayList<Plato>();

        URLConnection conn = new URL(platosURL).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null){
            response.append(line);
        }
        reader.close();

        JSONArray platos = new JSONArray(response.toString());

        for (int platosIndex = 0; platosIndex < platos.length(); platosIndex++){
            String id = null;
            String nombre = null;
            String imagen = null;
            JSONArray alergenos = null;
            ArrayList<String> alergenosList = new ArrayList<String>();
            int precio = 0;
            String descripcion = null;

            JSONObject jsonPlatos = platos.getJSONObject(platosIndex);
            if (jsonPlatos.has("id")){
                id = jsonPlatos.getString("id");
                nombre = jsonPlatos.getString("nombre");
                imagen = jsonPlatos.getString("imagen");
                alergenos = jsonPlatos.getJSONArray("alergenos");
                for (int alergenosIndex = 0; alergenosIndex < alergenos.length(); alergenosIndex++){
                    JSONObject jsonAlergenos = alergenos.getJSONObject(alergenosIndex);
                    if (jsonAlergenos.has("id")){
                        alergenosList.add(jsonAlergenos.getString("id"));
                    }
                }

                precio = jsonPlatos.getInt("precio");
                descripcion = jsonPlatos.getString("descripcion");

                Plato plato = new Plato(id,nombre,imagen,alergenosList,precio,descripcion);

                mPlatos.add(plato);
            }
        }
    }

    public ArrayList<Mesa> getMesas() {
        return mMesas;
    }

    public ArrayList<Plato> getPlatos() {
        return mPlatos;
    }
}
