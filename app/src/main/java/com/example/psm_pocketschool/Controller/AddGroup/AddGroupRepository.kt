package com.example.psm_pocketschool.Controller.AddGroup

import android.util.Log
import com.example.psm_pocketschool.Controller.GetStudents.IGetStudentsApiClient
import com.example.psm_pocketschool.Model.Grupo.AddGrupo
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.core.RetrofitHelper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AddGroupRepository {
    suspend fun addGroup(grupo: Grupo):String{
        Log.d("Grupo", grupo.toString())
        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("nameGroup", grupo.nameGroup)
        jsonObject.put("listStudents", grupo.listStudents)
        jsonObject.put("teacher", grupo.teacher!!.uid)
        jsonObject.put("listHomeworks", grupo.listHomeworks)

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val result= RetrofitHelper.getRetrofit().create(IAddGroupApiClient::class.java).addGroup(requestBody)
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