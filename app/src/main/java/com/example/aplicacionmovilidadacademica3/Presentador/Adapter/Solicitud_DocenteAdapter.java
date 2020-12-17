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
import com.example.aplicacionmovilidadacademica3.Models.Solicitud_Docente;
import com.example.aplicacionmovilidadacademica3.R;

import java.util.List;

public class Solicitud_DocenteAdapter  extends RecyclerView.Adapter<Solicitud_DocenteAdapter.Solicitud_DocenteViewHolder> {
    private Context sdContext;
    private List<Solicitud_Docente> solicitud_docenteList;
    private VacanteAdapter.OnItemClickListener mListener;
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(VacanteAdapter.OnItemClickListener listener){
        mListener = listener;
    }


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

        Solicitud_Docente solicitud_docente = solicitud_docenteList.get(position);
        String imageUrl = solicitud_docente.getImagen();
        String unianf = solicitud_docente.getNombre();


        holder.tv_nombresoldoc1.setText(unianf);
        Glide.with(sdContext).load(imageUrl).into(holder.imagensol_doc1);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.soldoc_detalle,null);
                ImageView image_detalle;
                TextView tv_detall1;
                image_detalle=dialogView.findViewById(R.id.image_detalle_doc);
                tv_detall1=dialogView.findViewById(R.id.tv_detalle_uni_doc);
                Glide.with(sdContext).load(solicitud_docente.getImagen()).into(image_detalle);
                tv_detall1.setText(solicitud_docente.getNombre());
                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return solicitud_docenteList.size();
    }

    public class Solicitud_DocenteViewHolder  extends RecyclerView.ViewHolder{
        ImageView button;

        TextView tv_nombresoldoc1;
        ImageView imagensol_doc1;
        public Solicitud_DocenteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombresoldoc1 = itemView.findViewById(R.id.tv_nombresoldoc);
            imagensol_doc1 = itemView.findViewById(R.id.imagensol_doc);
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
