package com.example.psm_pocketschool.Controller.UpdateHomework

import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Model.User.UserResult
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface IUpdateHomeworkApiClient {
    @PUT("homework/{homeworkId}")
    suspend fun onUpdateHomework(@Body RequestBody: RequestBody, @Path("homeworkId") homeworkId:String) : Response<Tarea>

}