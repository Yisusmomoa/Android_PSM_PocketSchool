package com.example.psm_pocketschool.Controller.AddHomework

import android.util.Log
import com.example.psm_pocketschool.Model.Tarea.AddTarea
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.core.RetrofitHelper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AddHomeworkRepository {
    suspend fun addHomework(tarea:AddTarea):Boolean{
        //val jsonObject = JSONObject()
        //jsonObject.put("idStudent", user.uid)
        // Convert JSONObject to String
        //val jsonObjectString = jsonObject.toString()
        //val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        //Log.d("Tarea", tarea.pdfsTarea.toString())

        val result=RetrofitHelper.getRetrofit().create(IAddHomeworkApiClient::class.java).addHomework(tarea)
        if (result.isSuccessful){
            return true
        }
        return false
    }
}