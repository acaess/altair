package cl.dominis.altair.altair;

import java.util.ArrayList;

/**
 * Created by alejandro on 23-04-16.
 */
public class Suscripcion {
    private long imei;
    private String method;
    private ArrayList<TipoAlerta> alert;

    public Suscripcion(long imei, ArrayList<TipoAlerta> alert) {
        this.imei = imei;
        this.alert = alert;
        this.method = "setSubcripciones";
    }


    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    public ArrayList<TipoAlerta> getalert() {
        return alert;
    }

    public void setalert(ArrayList<TipoAlerta> lista) {
        this.alert = lista;
    }
}
