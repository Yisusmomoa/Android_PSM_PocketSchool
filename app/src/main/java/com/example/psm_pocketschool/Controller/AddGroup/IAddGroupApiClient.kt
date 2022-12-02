package com.example.psm_pocketschool.Controller.AddGroup

import com.example.psm_pocketschool.Model.Grupo.AddGrupo
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface IAddGroupApiClient {
    @POST("groups/")
    suspend fun addGroup(@Body groupData:AddGrupo):Response<Grupo>
    @POST("groups/{groupId}/Students")
    suspend fun addStudent(@Body RequestBody: RequestBody, @Path("groupId") groupId:String):Response<ResponseBody>
}