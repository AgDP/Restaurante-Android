package com.dotta.agustin.restaurante.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Agustin on 22/09/2015.
 */
public class Plato implements Serializable{
    private String mId;
    private String mNombre;
    private String mImagen;
    private ArrayList<String> mAlergenos;
    private int mPrecio;
    private String mDescripcion;


    public Plato(String id, String nombre, String imagen, ArrayList<String> alergenos, int precio, String descripcion) {
        mId = id;
        mNombre = nombre;
        mImagen = imagen;
        mAlergenos = alergenos;
        mPrecio = precio;
        mDescripcion = descripcion;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public String getImagen() {
        return mImagen;
    }

    public void setImagen(String imagen) {
        mImagen = imagen;
    }

    public ArrayList<String> getAlergenos() {
        return mAlergenos;
    }

    public void setAlergenos(ArrayList<String> alergenos) {
        mAlergenos = alergenos;
    }

    public int getPrecio() {
        return mPrecio;
    }

    public void setPrecio(int precio) {
        mPrecio = precio;
    }

    public String getDescripcion() {
        return mDescripcion;
    }

    public void setDescripcion(String descripcion) {
        mDescripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Plato{" +
                "mDescripcion='" + mDescripcion + '\'' +
                '}';
    }
}
