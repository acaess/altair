package cl.dominis.altair.altair;

import java.io.Serializable;

/**
 * Created by alejandro on 23-04-16.
 */
public class Alertas implements Serializable{
    private int tipo_alerta;
    private String latitud;
    private String longitud;
    private String info;

    public Alertas(){
    }

    public int getTipo_alerta() {
        return tipo_alerta;
    }

    public void setTipo_alerta(int tipo_alerta) {
        this.tipo_alerta = tipo_alerta;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
