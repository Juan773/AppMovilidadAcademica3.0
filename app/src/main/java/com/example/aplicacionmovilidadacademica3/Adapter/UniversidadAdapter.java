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
import com.example.aplicacionmovilidadacademica3.Models.Universidad;
import com.example.aplicacionmovilidadacademica3.R;

import java.util.List;

import retrofit2.http.GET;

public class UniversidadAdapter extends RecyclerView.Adapter<UniversidadAdapter.UniversidadViewHolder> {

    private Context uContext;
    private List<Universidad> universidadList;


    public UniversidadAdapter(Context uContext, List<Universidad> universidadList) {
        this.uContext = uContext;
        this.universidadList = universidadList;
    }

    @NonNull
    @Override
    public UniversidadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(uContext);
        v = layoutInflater.inflate(R.layout.convenio_item,parent,false);
        return new UniversidadViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull UniversidadViewHolder holder, int position) {

          holder.direccion.setText(universidadList.get(position).getDireccion());
          holder.nombreuni.setText(universidadList.get(position).getNombre());
          holder.pais.setText(universidadList.get(position).getPais());

        Glide.with(uContext).asBitmap().load(universidadList.get(position).getImagen()).into(holder.imgu);

    }

    @Override
    public int getItemCount() {return universidadList.size();
    }



    public class  UniversidadViewHolder extends RecyclerView.ViewHolder{
        ImageView imgu;
        TextView nombreuni;
        TextView direccion;
        TextView pais;

        public UniversidadViewHolder(@NonNull View itemView) {
            super(itemView);
            imgu = itemView.findViewById(R.id.imagen_conve);
            nombreuni = itemView.findViewById(R.id.tv_nombreuni);
            direccion = itemView.findViewById(R.id.tv_direccion);
            pais = itemView.findViewById(R.id.tv_pais);



        }
    }
}
