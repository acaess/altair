package cl.dominis.altair.altair;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alejandro on 24-04-16.
 */
public class SuscripcionesAdapter extends BaseAdapter{
    ArrayList<TipoAlerta> ls;
    Context context;

    public SuscripcionesAdapter(ArrayList<TipoAlerta> ls, Context context) {
        this.ls = ls;
        this.context = context;
    }

    public ArrayList<TipoAlerta> getLs() {
        return ls;
    }

    public void setLs(ArrayList<TipoAlerta> ls) {
        this.ls = ls;
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
        return ls.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            row = inflater.inflate(R.layout.lvtipoalerta,parent,false);

        }else{
            row = convertView;
            TipoAlerta ta = ls.get(position);
            Switch info = (Switch) row.findViewById(R.id.lvswitch);
            info.setText(ta.getNombre());
            if (info.isChecked())
            {
                ta.setEstado(0);
            }else{
                ta.setEstado(1);
            }
        }
        return row;
    }
}
