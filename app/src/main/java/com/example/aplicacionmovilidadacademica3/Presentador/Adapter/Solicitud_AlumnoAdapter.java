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
import com.example.aplicacionmovilidadacademica3.Models.Solicitud_Alumno;
import com.example.aplicacionmovilidadacademica3.R;

import java.util.List;

public class Solicitud_AlumnoAdapter  extends RecyclerView.Adapter<Solicitud_AlumnoAdapter.Solicitud_AlumnoViewHolder> {

    private Context saContext;
    private List<Solicitud_Alumno> solicitud_alumnoList;
    private VacanteAdapter.OnItemClickListener mListener;
    public Solicitud_AlumnoAdapter(Context saContext, List<Solicitud_Alumno> solicitud_alumnoList) {
        this.saContext = saContext;
        this.solicitud_alumnoList = solicitud_alumnoList;
    }
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(VacanteAdapter.OnItemClickListener listener){
        mListener = listener;
    }



    @NonNull
    @Override
    public Solicitud_AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(saContext);
        v = layoutInflater.inflate(R.layout.sol_alu_item,parent,false);

        return new Solicitud_AlumnoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Solicitud_AlumnoViewHolder holder, int position) {
        Solicitud_Alumno solicitud_alumno = solicitud_alumnoList.get(position);


        String imageUrl = solicitud_alumno.getImagen();
        String unianf = solicitud_alumno.getNombre();

        holder.tv_nombresolalu1.setText(unianf);
        Glide.with(saContext).load(imageUrl).into(holder.imagensol_alu1);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.solalu_detalle,null);
                ImageView image_detalle_vac1;
                TextView tv_detalle_uni1;
                image_detalle_vac1=dialogView.findViewById(R.id.image_detalle_vac);
                tv_detalle_uni1=dialogView.findViewById(R.id.tv_detalle_uni);
                Glide.with(saContext).load(solicitud_alumno.getImagen()).into(image_detalle_vac1);
                tv_detalle_uni1.setText(solicitud_alumno.getNombre());
                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return solicitud_alumnoList.size();
    }


    public class Solicitud_AlumnoViewHolder extends RecyclerView.ViewHolder {
        TextView  tv_nombresolalu1;
        ImageView imagensol_alu1;
        ImageView button;

        public Solicitud_AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombresolalu1 = itemView.findViewById(R.id.tv_nombresolalu);
            imagensol_alu1 = itemView.findViewById(R.id.imagensol_alu);
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
