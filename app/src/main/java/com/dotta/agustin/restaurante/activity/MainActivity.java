package com.dotta.agustin.restaurante.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dotta.agustin.restaurante.R;
import com.dotta.agustin.restaurante.fragment.MesasListFragment;
import com.dotta.agustin.restaurante.model.Mesa;
import com.dotta.agustin.restaurante.model.Restaurante;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MesasListFragment.MesasListListener{

    private Restaurante mRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mRestaurante = Restaurante.getInstance(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
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
    public void onMesaSelected(Mesa mesa, int index) {
        Intent mesaDealleIntent = new Intent(this, MesaDetalleActivity.class);
        mesaDealleIntent.putExtra(MesaDetalleActivity.MESA_DETALLE_INDEX, index);
        // Animación de transición
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //noinspection unchecked
            startActivity(mesaDealleIntent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
        }
        else {
            startActivity(mesaDealleIntent);
        }
    }
}
