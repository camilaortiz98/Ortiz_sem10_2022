package com.camila.ortiz.vid20221;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class crearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        EditText editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        EditText editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        EditText editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        EditText editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5);


        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("https://628f68e4dc478523653ffd8a.mockapi.io/api/pokemon")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicio serv = retrofit.create(servicio.class);

        Button button4 = findViewById(R.id.button4);

        button4.setOnClickListener(v -> {

            String nombre = editTextTextPersonName.getText().toString();
            String tipo = editTextTextPersonName2.getText().toString();
            String region = editTextTextPersonName3.getText().toString();
            String imagen = editTextTextPersonName5.getText().toString();

            pokemon pokemon = new pokemon();
            pokemon.setNombre(nombre);
            pokemon.setTipo(tipo);


            pokemon.setUrl_imagen(imagen);

            Call<Void> capturar = serv.crear(pokemon);

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

    }
}