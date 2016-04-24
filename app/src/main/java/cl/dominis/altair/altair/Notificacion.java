package cl.dominis.altair.altair;

/**
 * Created by alejandro on 23-04-16.
 */
public class Notificacion {
    private long imei;
    private String latitud;
    private String longitud;
    private int rangoedad;
    private int tipo_alerta;
    private int grupo;
    private int area;
    private String method;
    private int dispositivo;
    private int value;


    public Notificacion() {
        init();
    }

    private void init()
    {
        this.imei = 0;
        this.latitud = "";
        this.longitud = "";
        this.rangoedad = 0;
        this.tipo_alerta = 0;
        this.grupo = 0;
        this.area = 0;
        this.method = "setDatos";
        this.dispositivo = 2;
        this.value = 0;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
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

    public int getRangoedad() {
        return rangoedad;
    }

    public void setRangoedad(int rangoedad) {
        this.rangoedad = rangoedad;
    }

    public int getId_alerta() {
        return tipo_alerta;
    }

    public void setTipo_alerta(int tipo_alerta) {
        this.tipo_alerta = tipo_alerta;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int superficie) {
        this.area = superficie;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "imei=" + imei +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", rangoedad=" + rangoedad +
                ", tipo_alerta=" + tipo_alerta +
                ", grupo=" + grupo +
                ", area=" + area +
                ", method='" + method + '\'' +
                '}';
    }
}
