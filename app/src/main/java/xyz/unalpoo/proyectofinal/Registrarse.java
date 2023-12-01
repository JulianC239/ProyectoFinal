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

public class Registrarse extends AppCompatActivity {

    private FirebaseAuth rAuth;

    private EditText correo;
    private EditText contrasena;
    private EditText contrasenaConfirmacion;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        rAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        contrasenaConfirmacion = findViewById(R.id.contrasenaconfirmacion);
    }

    public void onStart(){

        super.onStart();
        FirebaseUser currentUser = rAuth.getCurrentUser();
        //updateUl(currentUser);
    }

    public void registrarUsuario(View view){

        if(contrasena.getText().toString().equals(contrasenaConfirmacion.getText().toString())){
                // Aqui se comparan las dos contraseñás para ver si coinciden
            rAuth.createUserWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                    //Si si coinciden se guarda la contraseñá junto al correo.
                    .addOnCompleteListener(this, new  OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                            //Se comprueba que se creo el usuario.
                                FirebaseUser user = rAuth.getCurrentUser();

                                Toast.makeText(getApplicationContext(),"Usuario Creado",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);

                            }else{

                                Toast.makeText(getApplicationContext(),"Authentication failed",Toast.LENGTH_SHORT).show();
                                //upsdateUI(null);

                            }
                        }
                    });

        }else{
            Toast.makeText(this,"las constrasenas no coinciden",Toast.LENGTH_SHORT).show();
        }



    }
}