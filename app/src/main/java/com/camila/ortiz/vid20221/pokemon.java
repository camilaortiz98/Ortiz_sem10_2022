package com.camila.ortiz.vid20221;

import java.io.Serializable;

public class pokemon implements Serializable {

    String nombre;
    String tipo;
    String url_imagen;
    float latitude;
    float longitude;

    public pokemon() {
    }

    public pokemon(String nombre, String tipo, String url_imagen, float latitude, float longitude) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.url_imagen = url_imagen;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
