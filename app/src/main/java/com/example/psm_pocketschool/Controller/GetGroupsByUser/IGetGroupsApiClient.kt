package com.example.psm_pocketschool.Controller.GetGroupsByUser

import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IGetGroupsApiClient {
    @GET("groups/user/{idUser}")
    suspend fun getGroupsById(@Path("idUser") idUser:String): Response<List<Grupo>>
}