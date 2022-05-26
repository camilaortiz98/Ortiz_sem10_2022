package com.camila.ortiz.vid20221;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class detalleActivity extends AppCompatActivity {

    ImageView imageView;
    TextView nombre, tipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        imageView = findViewById(R.id.imageView);
        nombre = findViewById(R.id.nombre_detalle);
        tipo = findViewById(R.id.tipo_detalle);

        pokemon pokemon = (pokemon) getIntent().getSerializableExtra("pokemon");

        nombre.setText(pokemon.getNombre());
        tipo.setText(pokemon.getTipo());

        Picasso.get()
                .load(pokemon.getUrl_imagen())
                .into(imageView);

    }
}