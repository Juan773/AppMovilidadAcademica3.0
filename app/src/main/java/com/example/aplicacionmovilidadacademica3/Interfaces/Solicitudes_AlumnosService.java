package com.example.aplicacionmovilidadacademica3.Interfaces;

import com.example.aplicacionmovilidadacademica3.Models.Solicitud_Alumno;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Solicitudes_AlumnosService {
    @GET("/apisis/solicitud")
    Call<List<Solicitud_Alumno>> getSolicitud_alumno(@Header("Authorization") String authToken);
}
