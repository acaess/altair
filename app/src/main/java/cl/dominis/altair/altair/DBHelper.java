package cl.dominis.altair.altair;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alejandro on 23-04-16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private String nombre;
    private int id;
    private int estado;


    private final String CREATE_TABLE_TIPO_ALERTA = "create table TipoAlerta(nombre TEXT,id INTERGER,estado INTERGER)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TIPO_ALERTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST TipoAlerta");
        db.execSQL(CREATE_TABLE_TIPO_ALERTA);
    }


}
