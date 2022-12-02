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
    suspend fun addGroup(grupo: AddGrupo):String{
        Log.d("Grupo", grupo.toString())

        val result= RetrofitHelper.getRetrofit().create(IAddGroupApiClient::class.java).addGroup(grupo)
        if (result.isSuccessful){
            return result.body()!!.uid
        }
        return ""
    }
    suspend fun addStudents(user: User, groupId: String):Boolean{
        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("idStudent", user.uid)

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val result= RetrofitHelper.getRetrofit().create(IAddGroupApiClient::class.java).addStudent(requestBody,groupId)
        Log.d("Error", result.isSuccessful.toString())
        return result.isSuccessful
    }
}