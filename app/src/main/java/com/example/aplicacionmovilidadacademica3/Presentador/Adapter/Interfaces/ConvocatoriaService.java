package com.example.aplicacionmovilidadacademica3.Presentador.Adapter.Interfaces;

import com.example.aplicacionmovilidadacademica3.Models.Convocatoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ConvocatoriaService {

    @GET("/apisis/convo")
    Call<List<Convocatoria>> getConvocatorias(@Header("Authorization") String authToken);
}
