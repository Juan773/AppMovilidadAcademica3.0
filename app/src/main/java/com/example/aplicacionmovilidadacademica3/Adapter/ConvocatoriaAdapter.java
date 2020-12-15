package com.example.aplicacionmovilidadacademica3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplicacionmovilidadacademica3.Models.Convocatoria;
import com.example.aplicacionmovilidadacademica3.R;

import java.util.List;

public class ConvocatoriaAdapter extends RecyclerView.Adapter<ConvocatoriaAdapter.ConvocatoriaViewHolder> {
    private Context cContext;
    private List<Convocatoria> convocatoriaList;

    public ConvocatoriaAdapter(Context cContext, List<Convocatoria> convocatoriaList) {
        this.cContext = cContext;
        this.convocatoriaList = convocatoriaList;
    }

    @NonNull
    @Override
    public ConvocatoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(cContext);
        v = layoutInflater.inflate(R.layout.convocatoria_item,parent,false);
        return new ConvocatoriaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConvocatoriaViewHolder holder, int position) {

        holder.tv_nombrecon1.setText(convocatoriaList.get(position).getNombre_con());
        holder.tv_fechainicio1.setText(convocatoriaList.get(position).getFecha_ini());
        holder.tv_fechafin1.setText(convocatoriaList.get(position).getFecha_fin());
        Glide.with(cContext).asBitmap().load(convocatoriaList.get(position).getImagen()).into(holder.imagen_convo1);

    }

    @Override
    public int getItemCount() {
        return convocatoriaList.size();
    }
    public  class ConvocatoriaViewHolder extends RecyclerView.ViewHolder{
        TextView  tv_nombrecon1;
        TextView tv_fechainicio1;
        TextView tv_fechafin1;

        ImageView imagen_convo1;


        public ConvocatoriaViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nombrecon1 = itemView.findViewById(R.id.tv_nombrecon);
            tv_fechainicio1 = itemView.findViewById(R.id.tv_fechainicio);
            tv_fechafin1 = itemView.findViewById(R.id.tv_fechafin);
            imagen_convo1 = itemView.findViewById(R.id.imagen_convo);

        }
    }
}