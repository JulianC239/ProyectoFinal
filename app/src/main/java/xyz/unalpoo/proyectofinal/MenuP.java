package xyz.unalpoo.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_p);
    }
    public void irilibros(View view){
        Intent i = new Intent(this, LibrosRepo.class);
        startActivity(i);

    }
    public void irpractica(View view){
        Intent i = new Intent(this, Sensores.class);
        startActivity(i);

    }
}