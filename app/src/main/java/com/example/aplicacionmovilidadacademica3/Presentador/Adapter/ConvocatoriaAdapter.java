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
import com.example.aplicacionmovilidadacademica3.Models.Convocatoria;
import com.example.aplicacionmovilidadacademica3.R;

import java.util.List;

public class ConvocatoriaAdapter extends RecyclerView.Adapter<ConvocatoriaAdapter.ConvocatoriaViewHolder> {
    private Context cContext;
    private List<Convocatoria> convocatoriaList;

    private VacanteAdapter.OnItemClickListener mListener;


    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(VacanteAdapter.OnItemClickListener listener){
        mListener = listener;
    }
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
            Convocatoria convocatoria = convocatoriaList.get(position);
            String no = convocatoria.getNombre_con();
            String fei = convocatoria.getFecha_ini();
            String fef = convocatoria.getFecha_fin();
            String imageUrl = convocatoria.getImagen();
        holder.tv_nombrecon1.setText(no);
        holder.tv_fechainicio1.setText(fei);
        holder.tv_fechafin1.setText(fef);
        Glide.with(cContext).asBitmap().load(imageUrl).into(holder.imagen_convo1);
holder.button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder= new AlertDialog.Builder(v.getRootView().getContext());
        View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.convocatoria_detalle,null);
        ImageView img;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        img=dialogView.findViewById(R.id.image_detalle_vac);
        tv1=dialogView.findViewById(R.id.tv_detalle_uni);
        tv2= dialogView.findViewById(R.id.tv_fechaini);
        tv3=dialogView.findViewById(R.id.tv_fechafin);
        tv4=dialogView.findViewById(R.id.tv_contacto);
        Glide.with(cContext).load(convocatoria.getImagen()).into(img);
        tv1.setText(convocatoria.getNombre_con());
        tv2.setText(convocatoria.getFecha_ini());
        tv3.setText(convocatoria.getFecha_fin());
        tv4.setText(convocatoria.getDescripcion());
        builder.setView(dialogView);
        builder.setCancelable(true);
        builder.show();
    }
});
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

        ImageView button;


        public ConvocatoriaViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nombrecon1 = itemView.findViewById(R.id.tv_nombrecon);
            tv_fechainicio1 = itemView.findViewById(R.id.tv_fechainicio);
            tv_fechafin1 = itemView.findViewById(R.id.tv_fechafin);
            imagen_convo1 = itemView.findViewById(R.id.imagen_convo);
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