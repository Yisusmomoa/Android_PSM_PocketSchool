package com.example.psm_pocketschool.Controller.UpdateUser

import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.Model.User.UserResult
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface IUpdateUserApiClient {
    @PUT("users/{idUser}")
    suspend fun onUpdateUser(@Body RequestBody: RequestBody, @Path("idUser") userId:String) : Response<UserResult>

    @PUT("users/profilePic/{idUser}")
    suspend fun onUpdateImg(@Body RequestBody: RequestBody, @Path("idUser") userId:String):Response<User>

}