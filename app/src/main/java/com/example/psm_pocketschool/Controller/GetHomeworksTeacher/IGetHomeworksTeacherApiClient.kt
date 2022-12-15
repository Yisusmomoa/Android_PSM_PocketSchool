package com.example.psm_pocketschool.Controller.GetHomeworksTeacher

import com.example.psm_pocketschool.Model.Tarea.Tarea
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IGetHomeworksTeacherApiClient {
    @GET("homework/listHomeworksTeacher/{idUser}")
    suspend fun getHomeworksTeacher(@Path("idUser") idUser:String): Response<List<Tarea>>
}