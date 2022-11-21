package com.example.psm_pocketschool.Controller.Register

import android.util.Log
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.View.IRegisterView
import com.example.psm_pocketschool.core.RetrofitHelper
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.create

class RegisterController(
    private val registerView:IRegisterView):IRegisterController {
    private val repository=RegisterRepository()
    override fun onRegister(user: User) {
        /*GlobalScope.launch {
        }*/
        CoroutineScope(Dispatchers.IO).launch {
            val response=repository.onRegister(user)
            if(response!=null){
                Log.d("Response", response.toString())
                registerView.OnRegisterSuccess("Registro exitoso")
            }
            else{
                Log.d("Response", response.toString())
                registerView.OnRegisterError("Error, intennte más tarde")
            }
        }
        //return response
    }
}
/*
* {
  "name":"Usuario 6",
  "username":"User6 ",
  "password":"123456",
  "email":"user6@gmail.com",
  "typeUser":"Teacher"
}
* */