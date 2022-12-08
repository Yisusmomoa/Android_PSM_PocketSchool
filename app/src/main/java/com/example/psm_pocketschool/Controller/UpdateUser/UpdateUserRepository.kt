package com.example.psm_pocketschool.Controller.UpdateUser

import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.core.RetrofitHelper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class UpdateUserRepository {
    suspend fun onUpdateImg(idUser:String, img:String): User? {
        val jsonObject = JSONObject()
        jsonObject.put("profilePhoto",img)

        val jsonObjectString = jsonObject.toString()
        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val result=RetrofitHelper.getRetrofit().create(IUpdateUserApiClient::class.java).onUpdateImg(requestBody, idUser)
        if (result.isSuccessful){
            return result.body()
        }
        return null
    }
}