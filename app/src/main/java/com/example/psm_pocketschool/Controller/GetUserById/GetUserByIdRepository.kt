package com.example.psm_pocketschool.Controller.GetUserById

import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.core.RetrofitHelper

class GetUserByIdRepository {
    suspend fun getUserById(uid:String): User? {
        val result=RetrofitHelper.getRetrofit().create(IGetUserByIdApiClient::class.java).getUserById(uid)
        if (result.isSuccessful){
            val us: User? =result.body()
            return us
        }
        return null
    }
}