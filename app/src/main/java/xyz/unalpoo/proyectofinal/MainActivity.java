package xyz.unalpoo.proyectofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    private Button login;
    private Button registrar;
    private Button CerrarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.InicairSesionPantalla);
        registrar = findViewById(R.id.registrarpantalla);
        CerrarSesion = findViewById(R.id.CerrarSesion);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            login.setVisibility(View.GONE);
            registrar.setVisibility(View.GONE);
            CerrarSesion.setVisibility(View.VISIBLE);
        }

    }

    public void cerrarSesion(View view){
        auth.signOut();
        if(auth.getCurrentUser()==null){
            login.setVisibility(View.VISIBLE);
            registrar.setVisibility(View.VISIBLE);
            CerrarSesion.setVisibility(View.GONE);
        }
    }


    public void iriniciar(View view){
        Intent i = new Intent(this, InicioSesion.class);
        startActivity(i);

    }



    public void irregistrarse(View view){
        Intent i = new Intent(this,Registrarse.class);
        startActivity(i);

    }

    public void irmenu(View view){
        if(auth.getCurrentUser()==null){ //Aqui se pregunta si hay un usuario activo, para dejarlo pasar o dale un aviso.
            Intent i = new Intent(this, MenuP.class);
            startActivity(i);
        }else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("Debes registrarte o iniciar sesion para acceder");
            builder1.setCancelable(true);

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }


    }
}