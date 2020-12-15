package com.example.aplicacionmovilidadacademica3.Interfaces;

import com.example.aplicacionmovilidadacademica3.Models.Universidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UniversidadService {

    @GET("/apisis/universidad")
    Call<List<Universidad>> getUniversidad(@Header("Authorization") String authToken);
}
