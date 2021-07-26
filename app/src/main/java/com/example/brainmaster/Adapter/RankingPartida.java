package com.example.brainmaster.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.brainmaster.Entidades.Tabla;
import com.example.brainmaster.R;

import java.util.ArrayList;
import java.util.List;

public class RankingPartida extends ArrayAdapter<Tabla> {
    private List<Tabla> Usuario = new ArrayList<>();

    public RankingPartida (Context context, List<Tabla> datos){
        super(context, R.layout.items, datos);
        Usuario = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.items, null);

        TextView lblNombre = (TextView)item.findViewById(R.id.txtNombreUser);
        lblNombre.setText(Usuario.get(position).getJugador());

        TextView lblPosicion = (TextView)item.findViewById(R.id.lblposicion);
        lblPosicion.setText(Usuario.get(position).getPartida());//POSIBLE CAMBIO

        TextView lblPuntos = (TextView)item.findViewById(R.id.lblpuntos);
        lblPuntos.setText(Usuario.get(position).getPuntaje());

        return item;
    }
}