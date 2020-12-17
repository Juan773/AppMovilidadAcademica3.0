package com.example.aplicacionmovilidadacademica3.Presentador.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
       private OnItemClickListener mListener;



       public interface OnItemClickListener{
           void OnItemClick(int position);
       }
       public void setOnItemClickListener(OnItemClickListener listener){
           mListener = listener;
       }


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
             Vacante vacante = vacanteList.get(position);

             String imageUrl = vacante.getImagen();
             String unianf = vacante.getUni_anfi();
             String feini = vacante.getFecha_inicio();
             String fefin = vacante.getFecha_fin();
             String conta = vacante.getContacto();
        holder.uni.setText(unianf);
        holder.inicio.setText(feini);
        holder.fin.setText(fefin);
        Glide.with(vContext).load(imageUrl).into(holder.img);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.vacante_detalle,null);
                ImageView image_detalle_vac1;
                TextView tv_detalle_uni1;
                TextView tv_fechaini1;
                TextView tv_fechafin1;
                TextView tv_contacto1;
                image_detalle_vac1=dialogView.findViewById(R.id.image_detalle_vac);
                tv_detalle_uni1=dialogView.findViewById(R.id.tv_detalle_uni);
                tv_fechaini1=dialogView.findViewById(R.id.tv_fechaini);
                tv_fechafin1=dialogView.findViewById(R.id.tv_fechafin);
                tv_contacto1=dialogView.findViewById(R.id.tv_contacto);
                Glide.with(vContext).load(vacante.getImagen()).into(image_detalle_vac1);
                tv_detalle_uni1.setText(vacante.getUni_anfi());
                tv_fechaini1.setText(vacante.getFecha_inicio());
                tv_fechafin1.setText(vacante.getFecha_fin());
                tv_contacto1.setText(vacante.getContacto());

                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();



            }
        });

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
        ImageView button;

        public VacanteViewHolder(@NonNull View itemView) {
            super(itemView);

            uni = itemView.findViewById(R.id.tv_uni);
            inicio = itemView.findViewById(R.id.tv_fecha_inicio);
            fin = itemView.findViewById(R.id.tv_fecha_fin);
            img = itemView.findViewById(R.id.imagen);

            contacto = itemView.findViewById(R.id.tv_contacto);
            button = itemView.findViewById(R.id.detalleboton);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION);
                        mListener.OnItemClick(position);
                    }
                }
            });
        }
    }
}
