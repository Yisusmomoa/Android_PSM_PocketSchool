package com.example.psm_pocketschool.Controller.Login

import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.Model.User.UserResult
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ILoginApiClient {
    @POST("users/login")
    suspend fun onLogin(@Body requestBody: RequestBody) : Response<UserResult>
}