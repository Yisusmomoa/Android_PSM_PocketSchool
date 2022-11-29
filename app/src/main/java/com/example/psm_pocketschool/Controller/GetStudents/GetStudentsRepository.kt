package com.example.psm_pocketschool.Controller.GetStudents

import com.example.psm_pocketschool.Controller.GetStudents.IGetStudentsApiClient
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.core.RetrofitHelper
import retrofit2.Response
import retrofit2.create

class GetStudentsRepository {

    suspend fun getStudents(): List<User>? {

        val result=RetrofitHelper.getRetrofit().create(IGetStudentsApiClient::class.java).getStudents()

        if (result.isSuccessful){
            val bodyList:List<User>?=result.body()
            return bodyList
        }
        else{
            return null
        }
    }

}