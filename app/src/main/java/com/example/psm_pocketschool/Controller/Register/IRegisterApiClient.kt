package com.example.psm_pocketschool.Controller.Register

import com.example.psm_pocketschool.Model.User.User
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IRegisterApiClient {
    @POST("users/register")
    suspend fun onRegister(@Body requestBody: RequestBody): Response<ResponseBody>
}