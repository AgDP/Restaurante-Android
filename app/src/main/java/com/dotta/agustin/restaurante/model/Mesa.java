package com.dotta.agustin.restaurante.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Agustin on 22/09/2015.
 */
public class Mesa implements Serializable{
    private int mNumeroMesa;
    private float mCuenta;
    private ArrayList<Plato> mPlatos;

    public Mesa(int numeroMesa, float cuenta, ArrayList<Plato> platos) {
        mNumeroMesa = numeroMesa;
        mCuenta = cuenta;
        mPlatos = platos;
    }

    public int getNumeroMesa() {
        return mNumeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        mNumeroMesa = numeroMesa;
    }

    public float getCuenta() {
        return mCuenta;
    }

    public void setCuenta(float cuenta) {
        mCuenta = cuenta;
    }

    public ArrayList<Plato> getPlatos() {
        return mPlatos;
    }

    public void setPlatos(ArrayList<Plato> platos) {
        mPlatos = platos;
    }

    @Override
    public String toString() {
        return "Mesa " + mNumeroMesa;
    }
}
