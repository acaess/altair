package cl.dominis.altair.altair;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alejandro on 23-04-16.
 */
public class BaseAdapterAlertas extends BaseAdapter {
    private ArrayList<Alertas> ls;
    private Context context;


    public BaseAdapterAlertas(ArrayList<Alertas> ls, Context context) {
        this.ls = ls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public Object getItem(int position) {
        return ls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            row = inflater.inflate(R.layout.lvdesplegable,parent,false);
        }else{
            row = convertView;
            Alertas a = ls.get(position);
            TextView info = (TextView)row.findViewById(R.id.lvinfo);
            TextView tipo = (TextView)row.findViewById(R.id.lvtipo);
            TextView latitud = (TextView)row.findViewById(R.id.lvlatitud);
            TextView longitud = (TextView)row.findViewById(R.id.lvlongitud);
            info.setText(a.getInfo());
            tipo.setText(a.getTipo_alerta());
            latitud.setText(a.getLatitud());
            longitud.setText(a.getLongitud());
            Button button = (Button)row.findViewById(R.id.lvbotton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context,DesgloseActivity.class);
                    i.putExtra("estado",1);
                    context.startActivity(i);
                }
            });


        }
        return row;

    }
}
