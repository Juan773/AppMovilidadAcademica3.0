package com.example.aplicacionmovilidadacademica3.Presentador.Adapter.Interfaces;

import com.example.aplicacionmovilidadacademica3.Models.Solicitud_Docente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Solicitudes_DocenteService {
    @GET ("/apisis/sol_doc")
    Call<List<Solicitud_Docente>> getSolicitud_Docente(@Header("Authorization") String authToken);
}
