package xyz.unalpoo.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InicioSesion extends AppCompatActivity {

    private EditText correo;
    private EditText contrasena;
    private FirebaseAuth rAuth;
    //Se definen las variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);

        rAuth = FirebaseAuth.getInstance();
        //Se obtinen los datos

    }

    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = rAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void iniciarSesion (View view){

        rAuth.signInWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                //Aqui se toma el correo y la contraseña para autenticarlos.
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Si existe, se inicia sesion y se manda un mensaje para confirmar


                            FirebaseUser user = rAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Authentication success",
                                    Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                            //Se regresa a la pagina principal

                            //UpdateUI(user);
                        }else{

                            //Log.w(TAG, "signInWithEmail:failure",task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
}