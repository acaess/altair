package cl.dominis.altair.altair;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;

public class IngresoNoficacion extends AppCompatActivity {
    Context context = this;
    Ubicaciones ub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_enviar_notificacion);
        ub = new Ubicaciones(context);
        limpiar();
    }

    private void limpiar()
    {
        final Spinner rango = (Spinner)findViewById(R.id.sprango);
        String[] arRango = {"0-10","11-16","17-21","22-30","31-40","41-50","60 0 más"};
        ArrayAdapter<String> aaRango = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,arRango);
        rango.setAdapter(aaRango);
        final Spinner alerta = (Spinner)findViewById(R.id.spalerta);
        String[] arAlerta = {"Alergia","Incendio","Otra"};
        ArrayAdapter<String> aaAlerta = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,arAlerta);
        alerta.setAdapter(aaAlerta);
        final Spinner grupo = (Spinner)findViewById(R.id.spgrupo);
        String[] arAgrupo = {"Alergia","Incendio","Otra"};
        ArrayAdapter<String> aagrupo = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,arAgrupo);
        grupo.setAdapter(aagrupo);
        final Spinner area = (Spinner)findViewById(R.id.sparea);
        String[] ararea = {"10","100","1000","10000","100000"};
        ArrayAdapter<String> aaArea = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,ararea);
        area.setAdapter(aaArea);
        Button btnAgregar = (Button)findViewById(R.id.btnnotificar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notificacion nt = new Notificacion();
                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                long imei = Long.parseLong(tm.getDeviceId());
                nt.setImei(imei);
                nt.setTipo_alerta(alerta.getSelectedItemPosition());
                nt.setGrupo(grupo.getSelectedItemPosition());
                nt.setRangoedad(rango.getSelectedItemPosition());
                nt.setArea(Integer.parseInt(area.getSelectedItem().toString()));
                nt.setLatitud(ub.getLatitude() + "");
                nt.setLongitud(ub.getLongitude() + "");


                Gson gson = new Gson();
                Log.e("NOTIFICACION",gson.toJson(nt));
                if (Connection.enviarNotificacion(nt,context))
                {
                    Intent i = new Intent(context,MainActivity.class);
                    makeMessage("ENVIADO CON EXITO").show();
                    startActivity(i);
                }else {
                    makeMessage("USTED NO TIENE CONECCION A INTERNET").show();
                }
            }
        });
    }


    public Toast makeMessage(String texto)
    {
        Toast toast = Toast.makeText(this, texto, Toast.LENGTH_LONG);
        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(30);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 400);
        return toast;
    }


}
