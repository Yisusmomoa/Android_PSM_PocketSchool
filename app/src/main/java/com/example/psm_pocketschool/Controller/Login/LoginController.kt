package com.example.psm_pocketschool.Controller.Login

import android.util.Log
import com.example.psm_pocketschool.Controller.Register.RegisterRepository
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.View.ILoginView
import com.example.psm_pocketschool.View.IRegisterView
import com.example.psm_pocketschool.core.RetrofitHelper
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class LoginController(
    private val registerView: ILoginView ):ILoginController {
    private val repository=LoginRepository()
    private var retrofit= RetrofitHelper.getRetrofit()
    override fun onLogin(user: User) {
        val jsonObject = JSONObject()
        jsonObject.put("password", user.password)
        jsonObject.put("email", user.email)
        val jsonObjectString = jsonObject.toString()
        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        GlobalScope.launch {
            val response=retrofit.create(ILoginApiClient::class.java).onLogin(requestBody)
            if (response.isSuccessful) {
                //val gson = GsonBuilder().setPrettyPrinting().create()
                val userAux = User()
                userAux.uid=response.body()!!.result.uid
                userAux.name=response.body()!!.result.name
                userAux.username=response.body()!!.result.username
                userAux.email=response.body()!!.result.email
                userAux.createdAt=response.body()!!.result.createdAt
                userAux.typeUser=response.body()!!.result.typeUser
                userAux.imgUser=response.body()!!.result.imgUser

            } else {
                Log.e("RETROFIT_ERROR", response.code().toString())
            }
        }
        /*CoroutineScope(Dispatchers.IO).launch {
            val response=repository.onLogin(user)
            if(response!=null){
                Log.d("User", response.toString())
            }
            else{

            }
        }*/
    }

}

/*
* {
  "result": {
    "_id": "635cce4b100b21eda01a8a3d",
    "name": "Usuario 1",
    "email": "user5@gmail.com",
    "password": "54321",
    "username": "user5",
    "profilePhoto": "qwertyuio",
    "typeUser": "Student",
    "createdAt": "2022-10-29T06:55:07.316Z",
    "updatedAt": "2022-10-30T20:35:53.535Z",
    "__v": 0
  }
}
*
* */