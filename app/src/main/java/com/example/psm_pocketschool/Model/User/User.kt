package com.example.psm_pocketschool.Model.User
import com.example.psm_pocketschool.Model.Carrer.Carrer
import com.google.gson.annotations.SerializedName
    /*private var  uid:String = "",
    private var name:String="",
    private var usuario:String="",
    private var password:String="",
    private var email:String="",
    private var fechaRegistro:String="",
    private var typeUser:String="",
    private var imgUser:String=""*/

class User {

     @SerializedName("_id") var uid:String = ""
     @SerializedName("name") var name:String=""
     @SerializedName("username") var username:String=""
     @SerializedName("password") var password:String=""
     @SerializedName("email") var email:String=""
     @SerializedName("createdAt") var createdAt:String=""
     @SerializedName("typeUser") var typeUser:String=""
     @SerializedName("profilePhoto") var imgUser:String=""
     @SerializedName("carrer") var carrer:String=""

    constructor()
    //registro
    constructor(name: String, username: String, password: String, email: String, typeUser: String, carrer:String) {
        this.name = name
        this.username = username
        this.password = password
        this.email = email
        this.typeUser = typeUser
        this.carrer=carrer
    }

    constructor(
        uid: String,
        name: String,
        username: String,
        password: String,
        email: String,
        createdAt: String,
        typeUser: String,
        imgUser: String,
        carrer: String
    ) {
        this.uid = uid
        this.name = name
        this.username = username
        this.password = password
        this.email = email
        this.createdAt = createdAt
        this.typeUser = typeUser
        this.imgUser = imgUser
        this.carrer=carrer
    }

    //login
    constructor(password: String, email: String) {
        this.password = password
        this.email = email
    }


    override fun toString(): String {
        return "User(uid='$uid', name='$name', username='$username', password='$password', email='$email', createdAt='$createdAt', typeUser='$typeUser', imgUser='$imgUser')"
    }



}