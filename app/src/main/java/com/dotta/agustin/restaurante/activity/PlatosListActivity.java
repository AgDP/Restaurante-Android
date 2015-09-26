package com.dotta.agustin.restaurante.activity;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.dotta.agustin.restaurante.R;
import com.dotta.agustin.restaurante.fragment.MesasListFragment;
import com.dotta.agustin.restaurante.model.Mesa;
import com.dotta.agustin.restaurante.model.Restaurante;
import com.dotta.agustin.restaurante.util.DefaultThemeDecorator;

import org.json.JSONException;

import java.io.IOException;

public class PlatosListActivity extends AppCompatActivity {

    private Mesa mMesa;
    private Restaurante mRestaurante;
    private DefaultThemeDecorator mThemeDecorator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mThemeDecorator = new DefaultThemeDecorator(this);
        mThemeDecorator.setDefaultTheme();

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

        try {
            mRestaurante = Restaurante.getInstance(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMesa = mRestaurante.getMesas().get(getIntent().getIntExtra(MesaDetalleActivity.MESA_DETALLE_INDEX,0));

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();

        mThemeDecorator.checkThemeChanges();
    }
}
