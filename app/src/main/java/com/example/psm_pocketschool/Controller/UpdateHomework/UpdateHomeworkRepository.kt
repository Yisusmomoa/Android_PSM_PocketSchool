package com.example.psm_pocketschool.Controller.UpdateHomework

import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.core.RetrofitHelper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class UpdateHomeworkRepository {
    suspend fun onUpdateHomework(tarea: Tarea): Boolean {
        val jsonObject = JSONObject()
        jsonObject.put("title",tarea.title)
        jsonObject.put("description",tarea.description)
        //jsonObject.put("dateFin",tarea.dateFin)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val result=RetrofitHelper.getRetrofit().create(IUpdateHomeworkApiClient::class.java).onUpdateHomework(requestBody, tarea.uid)
        return result.isSuccessful
    }
}