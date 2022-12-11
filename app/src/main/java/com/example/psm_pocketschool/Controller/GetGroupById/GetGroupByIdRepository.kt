package com.example.psm_pocketschool.Controller.GetGroupById

import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.core.RetrofitHelper

class GetGroupByIdRepository {
    suspend fun getGroupById(uid:String): Grupo? {
        val result=RetrofitHelper.getRetrofit().create(IGetGroupByIdApiClient::class.java).getGroupById(uid)
        if(result.isSuccessful){
            val body:Grupo= result.body()!!
            return body
        }
        return null
    }
}