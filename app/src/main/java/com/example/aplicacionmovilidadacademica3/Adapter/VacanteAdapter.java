package com.example.aplicacionmovilidadacademica3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplicacionmovilidadacademica3.Models.Vacante;
import com.example.aplicacionmovilidadacademica3.R;

import java.util.List;

public class VacanteAdapter extends RecyclerView.Adapter<VacanteAdapter.VacanteViewHolder> {
    
       private Context vContext;
       private List<Vacante> vacanteList;


    public VacanteAdapter(Context vContext, List<Vacante> vacanteList) {
        this.vContext = vContext;
        this.vacanteList = vacanteList;
    }

    @NonNull
    @Override
    public VacanteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(vContext);
        v = layoutInflater.inflate(R.layout.vacante_item, parent, false);
        return  new VacanteViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull VacanteViewHolder holder, int position) {
            final Vacante vac_item =vacanteList.get(position);
        holder.uni.setText(vacanteList.get(position).getUni_anfi());
        holder.inicio.setText(vacanteList.get(position).getFecha_inicio());
        holder.fin.setText(vacanteList.get(position).getFecha_fin());

        Glide.with(vContext).asBitmap().load(vacanteList.get(position).getImagen()).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return vacanteList.size();
    }


    public   class VacanteViewHolder extends RecyclerView.ViewHolder{

        TextView uni;
        TextView inicio;
        TextView fin;
        TextView contacto;
         ImageView img;

        public VacanteViewHolder(@NonNull View itemView) {
            super(itemView);

            uni = itemView.findViewById(R.id.tv_uni);
            inicio = itemView.findViewById(R.id.tv_fecha_inicio);
            fin = itemView.findViewById(R.id.tv_fecha_fin);
            img = itemView.findViewById(R.id.imagen);

            contacto = itemView.findViewById(R.id.contacto);




        }
    }
}
