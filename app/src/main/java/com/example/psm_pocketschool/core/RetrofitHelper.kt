package com.example.psm_pocketschool.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    //puro consumo de apis
    //configuración de retrofift
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")//ruta fija de nuestros endpoints
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}