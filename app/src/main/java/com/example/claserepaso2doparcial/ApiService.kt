package com.example.claserepaso2doparcial

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getQuakesSigMonth(@Url url: String): Response<Quakes>

    @GET
    suspend fun getAllQuakesLastDay(@Url url: String): Response<Quakes>
}