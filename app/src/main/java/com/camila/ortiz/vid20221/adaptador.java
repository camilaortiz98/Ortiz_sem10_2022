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

    List<pokemon> pokemons;
    Context context;

    public adaptador(List<pokemon> pokemons, Context context) {
        this.pokemons = pokemons;
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

        pokemon pokemon = pokemons.get(position);

        holder.nombre.setText(pokemon.getNombre());
        holder.tipo.setText(pokemon.getTipo());

        Picasso.get()
                .load(pokemon.getUrl_imagen())
                .into(holder.imagen);

        holder.button.setOnClickListener(v -> {
            Intent intent = new Intent(context, detalleActivity.class);
            intent.putExtra("pokemon", pokemon);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    class pokemonHolder extends RecyclerView.ViewHolder {

        TextView nombre, tipo;
        Button button;
        ImageView imagen;

        public pokemonHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            tipo = itemView.findViewById(R.id.tipo);
            button = itemView.findViewById(R.id.detalle);
            imagen = itemView.findViewById(R.id.imagen);
        }
    }
}
