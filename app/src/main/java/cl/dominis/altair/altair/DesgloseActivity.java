package cl.dominis.altair.altair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DesgloseActivity extends AppCompatActivity {
    public int estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desglose);
        setContentView(R.layout.activity_lista_alertas);
        Intent i = getIntent();
        Alertas a = (Alertas) i.getSerializableExtra("alerta");


    }
}
