package com.example.psm_pocketschool.Controller.UpdateUser

import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.Session.UserApplication.Companion.prefs
import com.example.psm_pocketschool.View.IUpdateUserView
import com.example.psm_pocketschool.core.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class UpdateUserConntroller(
    private val iUpdateUserView:IUpdateUserView
) :IUpdateUserController{
    private var retrofit= RetrofitHelper.getRetrofit()
    private var updateUserRepository=UpdateUserRepository()
    private val myScope = CoroutineScope(Dispatchers.IO)
    override fun onUpdate(user: User) {
        if (user.password.isEmpty()){
            user.password= prefs.getPassword().toString()
        }

        val jsonObject = JSONObject()
        jsonObject.put("password", user.password)
        jsonObject.put("username", user.username)
        jsonObject.put("name", user.name)
        //jsonObject.put("profilePhoto", user.imgUser)

        val jsonObjectString = jsonObject.toString()
        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        GlobalScope.launch {
            //val response=retrofit.create(ILoginApiClient::class.java).onLogin(requestBody)
            val response=retrofit.create(IUpdateUserApiClient::class.java).onUpdateUser(requestBody,
                prefs.getUid().toString()
            )
            if (response.isSuccessful){
                val userAux = User()
                userAux.uid=response.body()!!.result.uid
                userAux.name=response.body()!!.result.name
                userAux.username=response.body()!!.result.username
                userAux.email=response.body()!!.result.email
                userAux.createdAt=response.body()!!.result.createdAt
                userAux.typeUser=response.body()!!.result.typeUser
                userAux.imgUser=response.body()!!.result.imgUser
                userAux.carrer=response.body()!!.result.carrer
                userAux.password=response.body()!!.result.password
                prefs.saveCredentials(userAux)

                iUpdateUserView.OnUpdateSuccess("Update exitoso")
            }
            else{
                iUpdateUserView.OnUpdateError("Update fallido")
            }

        }
    }

    override fun onUpdateImg(imgBase64: String) {
        val uid=prefs.getUid()
        myScope.launch {
            val user:User?=updateUserRepository.onUpdateImg(uid.toString(), imgBase64)
            if (user!=null){
                prefs.saveCredentials(user)
                iUpdateUserView.onUpdateImgSuccess(true, "Imagen actualizada con exito")
            }
            else{
                iUpdateUserView.onUpdateImgSuccess(false, "Error, intente más tarde")
            }
        }
    }


}


/*
*{
  "result": {
    "_id": "637ff0aa8e86dcf2d41500bb",
    "name": "userkot1",
    "email": "uskot1@gmail.com",
    "password": "12345",
    "username": "uskot1",
    "profilePhoto": "https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg",
    "typeUser": "Teacher",
    "createdAt": "2022-11-24T22:31:07.170Z",
    "updatedAt": "2022-11-25T22:43:25.579Z"
  }
}
* */