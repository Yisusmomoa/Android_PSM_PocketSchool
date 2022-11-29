package com.example.psm_pocketschool.Controller.AddGroup

import android.util.Log
import com.example.psm_pocketschool.Controller.GetStudents.IGetStudentsApiClient
import com.example.psm_pocketschool.Model.Grupo.AddGrupo
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.core.RetrofitHelper

class AddGroupRepository {
    suspend fun addGroup(grupo: AddGrupo):String{
        Log.d("Grupo", grupo.toString())

        val result= RetrofitHelper.getRetrofit().create(IAddGroupApiClient::class.java).addGroup(grupo)
        if (result.isSuccessful){
            return result.body()!!.uid
        }
        return ""
    }
    suspend fun addStudents(user: User, groupId: String):Boolean{
        val result= RetrofitHelper.getRetrofit().create(IAddGroupApiClient::class.java).addStudent(user,groupId)
        return result.isSuccessful
    }
}