package cl.dominis.altair.altair.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by alejandro on 23-04-16.
 */
public class Connection {

    public static String postRequest(String url, String param)
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
            //String param = "array_json={\"usuario\":\"18861638\",\"pass\":\"1234\"}";
            Log.w("MiRequestPost", "PARAMETROS " + param);
            urlConnection.setDoOutput(true);
            //Log.w("MiRequestPost", "SETOUTPUT");
            try {
                urlConnection.setRequestMethod("POST");
            } catch (ProtocolException e) {
                Log.w("MiRequestPost", e);
            }
            //Log.w("MiRequestPost", "METHOD POST");
            Log.e("OPARAMS", param);
            urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
            Log.w("MiRequestPost", "LARGO PARAMETRO");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            Log.w("MiRequestPost", "ContentTYPE");

            OutputStream os = urlConnection.getOutputStream();
            os.write(param.getBytes());
            os.flush();
            os.close();

            int statusCode = urlConnection.getResponseCode();
            Log.w("MiRequestPost", "STATUS " + statusCode);

            if (statusCode == 200) {
                BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                Log.w("MiRequestPost", "LEO STREAM");
                StringBuilder total = new StringBuilder();
                Log.w("MiRequestPost", "BUILDED TOTAL");
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line);
                    Log.w("MiRequestPost", "NUEVA LINEA");
                }
                Log.e("Resultado", total.toString());
                return total.toString();
            } else {
                Log.w("MiRequestPost", "ERROR");
                return "";
            }
        } catch (MalformedURLException e) {
            Log.w("MiRequestPost", "STACKTRACE");
            e.printStackTrace();
        } catch (IOException e) {
            Log.w("MiRequestPost", "STACKTRACE");
            e.printStackTrace();
        }
        return "";
    }

    public boolean checkConnectivity(Context ctx)
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
