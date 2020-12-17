package com.example.aplicacionmovilidadacademica3.Presentador.Adapter.Interfaces;

import com.example.aplicacionmovilidadacademica3.Models.Vacante;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface VacanteService {

    @GET("/apisis/cloud/vacante")
     Call<List<Vacante>> getVacantes(@Header("Authorization") String authToken);
}
