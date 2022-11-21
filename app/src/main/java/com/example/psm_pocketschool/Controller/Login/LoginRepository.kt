package com.example.psm_pocketschool.Controller.Login

import android.util.Log
import com.example.psm_pocketschool.Controller.Register.IRegisterApiClient
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.core.RetrofitHelper
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class LoginRepository {
    private var retrofit= RetrofitHelper.getRetrofit()
    suspend fun onLogin(user: User){
        val jsonObject = JSONObject()
        jsonObject.put("password", user.password)
        jsonObject.put("email", user.email)
        val jsonObjectString = jsonObject.toString()
        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        return withContext(Dispatchers.IO){
            val response=retrofit.create(ILoginApiClient::class.java).onLogin(requestBody)
            if (response.isSuccessful){
                Log.d("code", response.code().toString())
                val gson = GsonBuilder().setPrettyPrinting().create()

                /*val prettyJson = gson.toJson(
                    JsonParser.parseString(
                        response.body()
                            ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                    )
                )*/

                //Log.d("Pretty Printed JSON :", prettyJson)
            }
            else{
                Log.e("RETROFIT_ERROR", response.code().toString())
            }
        }
    }
}