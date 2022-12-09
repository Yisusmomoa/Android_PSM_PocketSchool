package com.example.psm_pocketschool.Controller.AddHomework

import com.example.psm_pocketschool.Model.Grupo.AddGrupo
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.Tarea.AddTarea
import com.example.psm_pocketschool.Model.Tarea.Tarea
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IAddHomeworkApiClient {
    @POST("homework/")
    suspend fun addHomework(@Body tareaData: AddTarea): Response<Grupo>
}