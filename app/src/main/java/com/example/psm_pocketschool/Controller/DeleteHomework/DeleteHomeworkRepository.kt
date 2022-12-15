package com.example.psm_pocketschool.Controller.DeleteHomework

import com.example.psm_pocketschool.core.RetrofitHelper

class DeleteHomeworkRepository {
    suspend fun onDeleteHomework(uid:String):Boolean{
        val result= RetrofitHelper.getRetrofit().create(IDeleteHomeworkApiClient::class.java).onDeleteHomework(uid)
        if (result.isSuccessful){
            return true
        }
        return false
    }
}