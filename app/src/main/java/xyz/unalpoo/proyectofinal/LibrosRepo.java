package xyz.unalpoo.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage. ListResult;
import com.google.firebase.storage. StorageReference;

public class LibrosRepo extends AppCompatActivity {

    private ListView listView;
    //Se crean el listView y un ArrayList para contener a los libros.
    private ArrayList<String> libros;

    private StorageReference mstorageref;
    // Se crea la referencia para acceder al storage.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros_repo);

        listView = findViewById(R.id.ListViewLibros);
        //Se define cual es la lista donde estaran los libros.
        libros= new ArrayList<>();



        mstorageref = FirebaseStorage.getInstance().getReference();
        StorageReference ref = mstorageref.child("libros");
        //Se usa la referencia para buscar los archivos en el Storage
        ref.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                //Si se encuentatra, se iteran todos los elementos y se ponen en la lista.
                for(StorageReference item : listResult.getItems()){
                    libros.add(item.getName()+"");

                }
                ArrayAdapter <String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, libros);

                listView.setAdapter(adapter);


            }
        });




}}