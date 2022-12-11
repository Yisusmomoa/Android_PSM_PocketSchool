package com.example.psm_pocketschool.Controller.GetHomeworks

import com.example.psm_pocketschool.Model.Tarea.Tarea
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IGetHomeworksApiClient {
    @GET("homework/listStudents/{idUser}")
    suspend fun getHomeworksByUser(@Path("idUser") idUser:String): Response<List<Tarea>>
}