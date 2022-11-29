package com.example.psm_pocketschool.Controller.GetStudents

import com.example.psm_pocketschool.Model.User.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface IGetStudentsApiClient {
    @GET("users/")
    suspend fun getStudents():Response<List<User>>
}