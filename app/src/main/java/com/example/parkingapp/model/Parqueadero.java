package com.example.parkingapp.model;

public class Parqueadero {

    private String uid;
    private String Nombre;
    private String Direccion;
    private String Latitud;
    private String Longitud;
    private String Horarios;
    private String Telefono;
    private String Precios;
    private String Comentarios;

    public Parqueadero() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getHorarios() {
        return Horarios;
    }

    public void setHorarios(String horarios) {
        Horarios = horarios;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getPrecios() {
        return Precios;
    }

    public void setPrecios(String precios) {
        Precios = precios;
    }

    public String getComentarios() {
        return Comentarios;
    }

    public void setComentarios(String comentarios) {
        Comentarios = comentarios;
    }

    @Override
    public String toString() {
        return Nombre + " " + Direccion;
    }
}
