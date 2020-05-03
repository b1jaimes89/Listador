package com.example.listador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    ArrayList<Peliculafue> pelis;

    public Adaptador(Context contexto, ArrayList<Peliculafue> pelis) {
        this.contexto = contexto;
        this.pelis = pelis;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pelis.size();
    }

    @Override
    public Object getItem(int position) {
        return pelis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(pelis.get(position).id);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        final View vista = inflater.inflate(R.layout.elemento_lista,null);
        TextView cuadrolistitulo = vista.findViewById(R.id.cuadrolistitulo);
        TextView cuadrolistfecha = vista.findViewById(R.id.cuadrolistfecha);
        TextView cuadrolistcali = vista.findViewById(R.id.cuadrolistcali);
        ImageView imagenGenero = vista.findViewById(R.id.imagenGenero);


        String aux = pelis.get(position).Genero;

        cuadrolistitulo.setText(pelis.get(position).Titulo);
        cuadrolistfecha.setText(pelis.get(position).Fecha);
        cuadrolistcali.setText(pelis.get(position).Calificacion);
        if(aux.equals("1")) imagenGenero.setImageResource(R.drawable.accion);
        if(aux.equals("2")) imagenGenero.setImageResource(R.drawable.aventura);
        if(aux.equals("3")) imagenGenero.setImageResource(R.drawable.cienciaficcion);
        if(aux.equals("4")) imagenGenero.setImageResource(R.drawable.comedia);
        if(aux.equals("5")) imagenGenero.setImageResource(R.drawable.drama);
        if(aux.equals("6")) imagenGenero.setImageResource(R.drawable.infantiles);
        if(aux.equals("7")) imagenGenero.setImageResource(R.drawable.musicales);
        if(aux.equals("8")) imagenGenero.setImageResource(R.drawable.suspens);
        if(aux.equals("9")) imagenGenero.setImageResource(R.drawable.terror2);



        return vista;
    }
}
