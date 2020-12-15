package com.example.aplicacionmovilidadacademica3.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplicacionmovilidadacademica3.Models.Solicitud_Docente;
import com.example.aplicacionmovilidadacademica3.R;

import java.util.List;

public class Solicitud_DocenteAdapter  extends RecyclerView.Adapter<Solicitud_DocenteAdapter.Solicitud_DocenteViewHolder> {
    private Context sdContext;
    private List<Solicitud_Docente> solicitud_docenteList;

    public Solicitud_DocenteAdapter(Context sdContext, List<Solicitud_Docente> solicitud_docenteList) {
        this.sdContext = sdContext;
        this.solicitud_docenteList = solicitud_docenteList;
    }

    @NonNull
    @Override
    public Solicitud_DocenteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(sdContext);
        v = layoutInflater.inflate(R.layout.sol_doc_item,parent,false);

        return new Solicitud_DocenteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Solicitud_DocenteViewHolder holder, int position) {
        holder.tv_nombresoldoc1.setText(solicitud_docenteList.get(position).getNombre());
        Glide.with(sdContext).asBitmap().load(solicitud_docenteList.get(position).getImagen()).into(holder.imagensol_doc1);

    }

    @Override
    public int getItemCount() {
        return solicitud_docenteList.size();
    }

    public class Solicitud_DocenteViewHolder  extends RecyclerView.ViewHolder{


        TextView tv_nombresoldoc1;
        ImageView imagensol_doc1;
        public Solicitud_DocenteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombresoldoc1 = itemView.findViewById(R.id.tv_nombresoldoc);
            imagensol_doc1 = itemView.findViewById(R.id.imagensol_doc);

        }
    }
}
