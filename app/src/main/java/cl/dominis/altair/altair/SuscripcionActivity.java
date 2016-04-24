package cl.dominis.altair.altair;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SuscripcionActivity extends AppCompatActivity {
    ArrayList<TipoAlerta> ls;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscripcion);
        ls = Connection.readAll(context);
        if (ls.size() == 0)
        {
            Connection.ingresarTipoActividad(context);
            ls = Connection.readAll(context);
        }

        cargarDatos();
    }
    private void cargarDatos() {
        final ListView lv = (ListView) findViewById(R.id.listviewsuscripcion);
        lv.setAdapter(new SuscripcionesAdapter(ls, context));
        Button btn = (Button)findViewById(R.id.btnsuscribir);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuscripcionesAdapter as = (SuscripcionesAdapter) lv.getAdapter();
                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                long imei = Long.parseLong(tm.getDeviceId());
                Suscripcion s = new Suscripcion(imei,as.getLs());
                Connection.enviarSuscripcion(s,context);

            }
        });
    }
}
