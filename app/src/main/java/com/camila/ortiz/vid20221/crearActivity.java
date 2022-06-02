package com.camila.ortiz.vid20221;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class crearActivity extends AppCompatActivity {

    ImageView imagenL;

    Uri imageUri;
    String imagenString;

    private static final int PICK_IMAGE = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        EditText autorL = findViewById(R.id.autor);
        EditText resumenL = findViewById(R.id.resumen);
        EditText tituloL = findViewById(R.id.titulo);
        EditText tienda1L = findViewById(R.id.tienda1);
        EditText tienda2L = findViewById(R.id.tienda2);
        EditText tienda3L = findViewById(R.id.tienda3);
        imagenL = findViewById(R.id.imagen);

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("https://6298b2a7f2decf5bb7496687.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicio serv = retrofit.create(servicio.class);

        Button registrar = findViewById(R.id.registrar);

        registrar.setOnClickListener(v -> {


            String titulo = tituloL.getText().toString();
            String resumen = resumenL.getText().toString();
            String autor = autorL.getText().toString();
            String tienda1 = tienda1L.getText().toString();
            String tienda2 = tienda2L.getText().toString();
            String tienda3 = tienda3L.getText().toString();

            libro libro = new libro();
            libro.setTitulo(titulo);
            libro.setResumen(resumen);
            libro.setAutor(autor);
            libro.setTienda_1(tienda1);
            libro.setTienda_2(tienda2);
            libro.setTienda_3(tienda3);
            libro.setImagen("link de imagen");

            Call<Void> capturar = serv.crear(libro);

            capturar.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                    Log.e("info ", String.valueOf(response.code()));
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            });
        });

        imagenL.setOnClickListener(v -> cargarImagen());

    }
    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imagenL.setImageURI(imageUri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] image = outputStream.toByteArray();
                imagenString = Base64.encodeToString(image, Base64.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}