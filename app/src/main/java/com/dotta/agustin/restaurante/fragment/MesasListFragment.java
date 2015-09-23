package com.dotta.agustin.restaurante.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dotta.agustin.restaurante.R;
import com.dotta.agustin.restaurante.model.Mesa;
import com.dotta.agustin.restaurante.model.Restaurante;

import org.json.JSONException;

import java.io.IOException;

public class MesasListFragment extends Fragment {

    private MesasListListener mListener;


    public static MesasListFragment newInstance() {
        return new MesasListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_mesas, container, false);

        Restaurante restaurante = null;
        try {
            restaurante = Restaurante.getInstance(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListView list = (ListView) root.findViewById(android.R.id.list);
        final ArrayAdapter<Mesa> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1, // Recuerda que esto es el layout de cada fila
                restaurante.getMesas());
        list.setAdapter(adapter);
       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    mListener.onMesaSelected(adapter.getItem(position), position);
                }
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (MesasListListener) getActivity();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mListener = (MesasListListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    public interface MesasListListener {
        void onMesaSelected(Mesa mesa, int index);
    }
}
