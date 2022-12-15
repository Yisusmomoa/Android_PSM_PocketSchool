package com.example.psm_pocketschool.Controller.DeleteHomework

import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Model.User.UserResult
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface IDeleteHomeworkApiClient {
    @PUT("homework/status/{homeworkId}")
    suspend fun onDeleteHomework( @Path("homeworkId") homeworkId:String) : Response<Tarea>
}