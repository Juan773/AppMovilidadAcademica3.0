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
import com.example.aplicacionmovilidadacademica3.Models.Solicitud_Alumno;
import com.example.aplicacionmovilidadacademica3.R;

import java.util.List;

public class Solicitud_AlumnoAdapter  extends RecyclerView.Adapter<Solicitud_AlumnoAdapter.Solicitud_AlumnoViewHolder> {


    public Solicitud_AlumnoAdapter(Context saContext, List<Solicitud_Alumno> solicitud_alumnoList) {
        this.saContext = saContext;
        this.solicitud_alumnoList = solicitud_alumnoList;
    }

    private Context saContext;
    private List<Solicitud_Alumno> solicitud_alumnoList;

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

        holder.tv_nombresolalu1.setText(solicitud_alumnoList.get(position).getNombre());
        Glide.with(saContext).asBitmap().load(solicitud_alumnoList.get(position).getImagen()).into(holder.imagensol_alu1);

    }

    @Override
    public int getItemCount() {
        return solicitud_alumnoList.size();
    }


    public class Solicitud_AlumnoViewHolder extends RecyclerView.ViewHolder {
        TextView  tv_nombresolalu1;
        ImageView imagensol_alu1;

        public Solicitud_AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombresolalu1 = itemView.findViewById(R.id.tv_nombresolalu);
            imagensol_alu1 = itemView.findViewById(R.id.imagensol_alu);

        }
    }
}
