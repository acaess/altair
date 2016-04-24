package cl.dominis.altair.altair;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandro on 23-04-16.
 */
public class Connection {
    private static final  String PROXY = "http://altair.dominis.cl/v1/";

    public static boolean enviarNotificacion(Notificacion notificacion,Context context)
    {

        if(checkConnectivity(context))
        {
        try{
            Gson sj = new Gson();
            String json= sj.toJson(notificacion);
            String api = String.format("%s/hw.php",PROXY);
            postRequest(api,json);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        }else {
            return false;
        }
    }
    public static boolean enviarSuscripcion(Suscripcion suscripcion,Context context)
    {
        if(checkConnectivity(context))
        {
        try{
            Gson sj = new Gson();
            String json= sj.toJson(suscripcion);
            String api = String.format("%s/subscripciones.php ",PROXY);
            postRequest(api,json);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        }else {
            return false;
        }
    }

    public static boolean consultarSuscripcion(long imei,Context context)
    {
        if(checkConnectivity(context))
        {
        try{
            Gson sj = new Gson();
            String json= sj.toJson(imei);
            String api = String.format("%s/", PROXY);
            postRequest(api,json);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        }else {
            return false;
        }
    }
    public static ArrayList<Alertas> obtenerUltimas10(Context context)
    {
        if(checkConnectivity(context))
        {
        try{
            Gson sj = new Gson();
            String api = String.format("%s/",PROXY);
            String json = postRequest(api, "");
            ArrayList<Alertas> list = sj.fromJson(json, new TypeToken<List<Alertas>>() {
            }.getType());
            postRequest(api, json);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        }else {
            return null;
        }
    }

    public static ArrayList<Alertas> obtenerNotificaciones(long imei,Context context)
    {
        if(checkConnectivity(context)) {
            try {
                Gson sj = new Gson();
                String json = String.format("{\"imei\":{\"%d\"}", imei);
                String api = String.format("%s/", PROXY);
                String json2 = postRequest(api, "");
                ArrayList<Alertas> list = sj.fromJson(json2, new TypeToken<List<Alertas>>() {
                }.getType());
                postRequest(api, json);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }
    private static String postRequest(String url, String param)
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            URL link = new URL(url);
            Log.w("MiRequestPost", "NEW URL");
            HttpURLConnection urlConnection = (HttpURLConnection) link.openConnection();
            Log.w("MiRequestPost", "OPEN C");
            Log.w("MiRequestPost", "PARAMETROS " + param);
            urlConnection.setDoOutput(true);
            try {
                urlConnection.setRequestMethod("POST");
            } catch (ProtocolException e) {
                Log.w("MiRequestPost", e);
            }
            urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStream os = urlConnection.getOutputStream();
            os.write(param.getBytes());
            os.flush();
            os.close();

            int statusCode = urlConnection.getResponseCode();
            Log.w("MiRequestPost", "STATUS " + statusCode);

            if (statusCode == 200) {
                BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
                Log.e("REQUEST",total.toString());
                return total.toString();
            } else {
                Log.w("MiRequestPost", "ERROR");
                Log.w("MiRequestPost", "STATUS " + statusCode);
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void ingresarTipoActividad(Context context){

        ArrayList<TipoAlerta> l = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TipoAlerta t = new TipoAlerta();
            t.setId(i);
            t.setNombre(String.format("Alerta%d", 1));
            t.create(context);
        }
    }

    public static ArrayList<TipoAlerta> readAll(Context context)
    {
        ArrayList<TipoAlerta> ls = new ArrayList<>();
        try {

        DBHelper dba = new DBHelper(context, "Altair", null, 1);
        SQLiteDatabase db = dba.getWritableDatabase();
        String sql = "SELECT * FROM TipoAlerta";
        Cursor c = db.rawQuery(sql, null);
        if (c != null && c.getCount() != 0) {
            c.moveToFirst();
            do {
                TipoAlerta a = new TipoAlerta();
                a.setId(c.getInt(c.getColumnIndex("id")));
                a.setNombre(c.getString(c.getColumnIndex("nombre")));
                a.setEstado(c.getInt(c.getColumnIndex("estado")));
                ls.add(a);
            } while (c.moveToNext());
        }
        db.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return ls;
    }

    private static boolean checkConnectivity(Context ctx)
    {
        ConnectivityManager conMgr = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        return i.isAvailable();
    }
}
