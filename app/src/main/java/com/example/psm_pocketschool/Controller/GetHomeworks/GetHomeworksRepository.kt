package com.example.psm_pocketschool.Controller.GetHomeworks

import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.core.RetrofitHelper

class GetHomeworksRepository {
    suspend fun getHomeworksByUser(uid:String):List<Tarea>?{
        val result=RetrofitHelper.getRetrofit().create(IGetHomeworksApiClient::class.java).getHomeworksByUser(uid)
        if (result.isSuccessful){
            val bodyList:List<Tarea>?=result.body()
            return bodyList
        }
        return null
    }
}