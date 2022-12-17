package com.example.psm_pocketschool.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    //puro consumo de apis
    //configuración de retrofift
    //192.168.1.2
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.2:5000/api/")//ruta fija de nuestros endpoints
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // .baseUrl("https://express-psm5.onrender.com")//ruta fija de nuestros endpoints
}