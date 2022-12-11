package com.example.psm_pocketschool.Controller.GetGroupById

import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.Tarea.Tarea
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IGetGroupByIdApiClient {
    ///groups/:groupId
    @GET("groups/{groupId}")
    suspend fun getGroupById(@Path("groupId") groupId:String): Response<Grupo>
}