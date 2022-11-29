package com.example.psm_pocketschool.Controller.AddGroup

import com.example.psm_pocketschool.Model.Grupo.AddGrupo
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface IAddGroupApiClient {
    @POST("groups/")
    suspend fun addGroup(@Body groupData:AddGrupo):Response<Grupo>
    @POST("groups/{id}/")
    suspend fun addStudent(@Body userData:User, @Path("groupId") groupId:String):Response<User>
}