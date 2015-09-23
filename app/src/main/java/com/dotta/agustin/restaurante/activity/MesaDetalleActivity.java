package com.dotta.agustin.restaurante.activity;


import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Window;

import com.dotta.agustin.restaurante.R;
import com.dotta.agustin.restaurante.fragment.MesasListFragment;

public class MesaDetalleActivity extends AppCompatActivity {

    public static final String MESA_DETALLE_INDEX = "com.dotta.agustin.restaurante.activity.MESA_DETALLE_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Indicamos la animación de entrada, y la de regreso a la anterior
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            Explode explode = new Explode();
            explode.setDuration(1000);
            getWindow().setEnterTransition(explode);

            Slide slide = new Slide(Gravity.RIGHT);
            slide.setDuration(1000);
            getWindow().setReturnTransition(slide);
        }

        super.onCreate(savedInstanceState);

        // Le damos la interfaz
        setContentView(R.layout.activity_mesa_detalle);

        // Decimos que nuestra toolbar funciona como un action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Ponemos la flecha de volver porque esta actividad siempre
        // viene de otra
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FragmentManager fm = getFragmentManager();

        if (findViewById(R.id.mesaslist) != null) {

            if (fm.findFragmentById(R.id.mesaslist) == null) {
                fm.beginTransaction()
                        .add(R.id.mesaslist, MesasListFragment.newInstance())
                        .commit();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Comprobamos si la opción es la de la flecha "back"
        if (item.getItemId() == android.R.id.home) {
            endActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        endActivity();
    }

    // Zona común para volver a la actividad anterior
    protected void endActivity() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        else {
            // Esto me asegura que finaliza la actividad actual y regresa a la que
            // hayamos definido en el AndroidManifest.xml
            // Sirve si pudiera acceder a CityPagerActivity desde varios sitios
            // y no quiero marear al usuario con la navegación
            // Ejemplo: correo nuevo de Gmail
            NavUtils.navigateUpFromSameTask(this);
        }
    }

}
