package com.example.psm_pocketschool.Controller.GetUserById

import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IGetUserByIdApiClient {
    @GET("users/{idUser}")
    suspend fun getUserById(@Path("idUser") idUser:String): Response<User>
}

/*
* {
  "_id": "637d66749e020c1d64dc5ad0",
  "name": "Usuario 7 edit",
  "email": "user5@gmail.com",
  "password": "54321",
  "username": "User7",
  "profilePhoto": "https://icon-library.com/images/anonymous-avatar-icon/anonymous-avatar-icon-25.jpg",
  "typeUser": "Student",
  "carrer": "LMAD",
  "createdAt": "2022-11-23T00:16:52.457Z",
  "updatedAt": "2022-11-26T08:59:05.177Z"
}
* */