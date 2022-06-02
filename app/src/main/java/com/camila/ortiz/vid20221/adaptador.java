package com.camila.ortiz.vid20221;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class adaptador extends RecyclerView.Adapter<adaptador.pokemonHolder> {

    List<libro> libros;
    Context context;

    public adaptador(List<libro> libros, Context context) {
        this.libros = libros;
        this.context = context;
    }

    @NonNull
    @Override
    public pokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        return new pokemonHolder(inflater.inflate(R.layout.lista_pokemon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull pokemonHolder holder, int position) {

        libro libro = libros.get(position);

        holder.nombre.setText(libro.getTitulo());


       Picasso.get()
                .load("https://sm.ign.com/ign_es/tv/j/juego-de-t/juego-de-tronos_d4p4.png")
                .into(holder.imagen);

        holder.imagen.setOnClickListener(v -> {
            Intent intent = new Intent(context, detalleActivity.class);
            intent.putExtra("libro", libro);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    static class pokemonHolder extends RecyclerView.ViewHolder {

        TextView nombre, tipo;
        ImageView imagen;

        public pokemonHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            imagen = itemView.findViewById(R.id.imagen);
        }
    }
}
