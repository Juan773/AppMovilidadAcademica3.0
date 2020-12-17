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
import com.example.aplicacionmovilidadacademica3.Models.Universidad;
import com.example.aplicacionmovilidadacademica3.R;

import java.util.List;

public class UniversidadAdapter extends RecyclerView.Adapter<UniversidadAdapter.UniversidadViewHolder> {

    private Context uContext;
    private List<Universidad> universidadList;
    private VacanteAdapter.OnItemClickListener mListener;


    public UniversidadAdapter(Context uContext, List<Universidad> universidadList) {
        this.uContext = uContext;
        this.universidadList = universidadList;
    }
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(VacanteAdapter.OnItemClickListener listener){
        mListener = listener;
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
        Universidad universidad = universidadList.get(position);
        String imagenUrl = universidad.getImagen();
        String dir = universidad.getDireccion();
        String nomuni = universidad.getNombre();
        String pais = universidad.getPais();
        String totvac = universidad.getTotalvacantes();
          holder.direccion.setText(dir);
          holder.nombreuni.setText(nomuni);
          holder.pais.setText(pais);
        Glide.with(uContext).load(imagenUrl).into(holder.imgu);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.convenio_detalle,null);
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
                Glide.with(uContext).load(universidad.getImagen()).into(image_detalle_vac1);
                tv_detalle_uni1.setText(universidad.getDireccion());
                tv_fechaini1.setText(universidad.getNombre());
                tv_fechafin1.setText(universidad.getPais());
                tv_contacto1.setText(universidad.getTotalvacantes());

                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();


            }
        });


    }

    @Override
    public int getItemCount() {return universidadList.size();
    }



    public class  UniversidadViewHolder extends RecyclerView.ViewHolder{
        ImageView imgu;
        TextView nombreuni;
        TextView direccion;
        TextView pais;
        ImageView button;


        public UniversidadViewHolder(@NonNull View itemView) {
            super(itemView);
            imgu = itemView.findViewById(R.id.imagen_conve);
            nombreuni = itemView.findViewById(R.id.tv_nombreuni);
            direccion = itemView.findViewById(R.id.tv_direccion);
            pais = itemView.findViewById(R.id.tv_pais);
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
