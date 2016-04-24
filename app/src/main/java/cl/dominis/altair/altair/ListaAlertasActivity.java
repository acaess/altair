package cl.dominis.altair.altair;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaAlertasActivity extends AppCompatActivity {
    public int estado;
    ArrayList<Alertas> tl;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alertas);
        Intent i = getIntent();
        estado = i.getIntExtra("estado",3);
        if (estado == 1)
        {
            try {
            TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            long imei = Long.parseLong(tm.getDeviceId());
            tl = Connection.obtenerNotificaciones(imei, context);
            ListView lv =(ListView) findViewById(R.id.lvAlertas);
                if (tl == null)
                {
                    tl= new ArrayList<>();
                }
                lv.setAdapter(new BaseAdapterAlertas(tl, context));
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }else
        {
            try {

                tl = Connection.obtenerUltimas10(context);
                ListView lv =(ListView) findViewById(R.id.lvAlertas);
                if (tl == null)
                {
                    tl= new ArrayList<>();
                }
                lv.setAdapter(new BaseAdapterAlertas(tl, context));
            }catch (Exception e)
            {

                e.printStackTrace();
            }
        }
    }
}
