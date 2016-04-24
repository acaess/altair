package cl.dominis.altair.altair;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alejandro on 23-04-16.
 */
public class TipoAlerta {
    private String nombre;
    private int id;
    private int estado;

    public TipoAlerta() {
        this.estado = 0;
        this.id = 0;
        this.nombre ="nombre";

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean create(Context context)
    {
        try
        {
            DBHelper dba = new DBHelper(context, "Altair", null, 1);
            SQLiteDatabase db = dba.getWritableDatabase();
            ContentValues a = new ContentValues();
            a.put("nombre",this.getNombre());
            a.put("id",this.getId());
            a.put("estado",this.getId());
            db.insert("TipoAlerta",null,a);
            db.close();
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
    public boolean read(Context context)
    {
        try
        {
            DBHelper dba = new DBHelper(context, "Altair", null, 1);
            SQLiteDatabase db = dba.getReadableDatabase();
            String sql = String.format("SELECT * FROM TipoAlerta where id = %d", this.id);
            Cursor c = db.rawQuery(sql, null);
            if (c != null && c.getCount() != 0) {
                c.moveToFirst();
                do {
                    this.nombre = (c.getString(c.getColumnIndex("nombre")));
                    this.estado = (c.getInt(c.getColumnIndex("estado")));
                } while (c.moveToNext());
            }
            db.close();

            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
    public boolean update(Context context)
    {
        try
        {
            DBHelper dba = new DBHelper(context, "Altair", null, 1);
            SQLiteDatabase db = dba.getWritableDatabase();
            ContentValues a = new ContentValues();
            a.put("nombre",this.getNombre());
            a.put("id",this.getId());
            a.put("estado",this.getId());
            db.update("TipoAlerta", a, String.format("id= %d", id), null);
            db.close();
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
    public boolean delete(Context context)
    {
        try
        {
            DBHelper dba = new DBHelper(context, "Altair", null, 1);
            SQLiteDatabase db = dba.getWritableDatabase();
            String where = String.format("id = %d",this.getId());
            db.delete("TipoAlerta", where, null);
            db.close();

            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
}
