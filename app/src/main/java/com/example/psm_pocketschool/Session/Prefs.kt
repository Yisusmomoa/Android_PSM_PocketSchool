package com.example.psm_pocketschool.Session

import android.content.Context
import android.net.wifi.hotspot2.pps.Credential
import com.example.psm_pocketschool.Model.User.User

class Prefs(val context: Context) {
    val SHARED_NAMEDB="Mydtb"
    val storage=context.getSharedPreferences(SHARED_NAMEDB, Context.MODE_PRIVATE)
    //FUNCION QUE NOS PERMITE GUARDAR LAS CREDENCIALES
    fun saveCredentials(user:User){
        var editor =  storage.edit()
        editor.putString("uid",user.uid)
        editor.putString("username",user.username)
        editor.putString("password",user.password)
        editor.putString("imgUser",user.imgUser)
        editor.putString("email",user.email)
        editor.putString("typeUser",user.typeUser)
        editor.putString("name",user.name)
        editor.putString("createdAt",user.createdAt)
        editor.putString("carrer", user.carrer)
        editor.commit()
    }

    /*val SHARED_UID="uid"
    val SHARED_NAME="name"
    val SHARED_USERNAME="username"
    val SHARED_PASSWORD="password"
    val SHARED_EMAIL="email"
    val SHARED_CREATEDAT="createdAt"
    val SHARED_TYPEUSER="typeUser"
    val SHARED_IMGUSER="imgUser"

    fun saveUid(uid:String){
        storage.edit().putString(SHARED_UID, uid)
    }
    fun getUid():String{
        return storage.getString(SHARED_UID, "")!!
    }

    fun saveName(name:String){
        storage.edit().putString(SHARED_NAME, name)
    }
    fun getName():String{
        return storage.getString(SHARED_NAME, "")!!
    }

    fun saveUsername(username:String){
        storage.edit().putString(SHARED_USERNAME, username)
    }
    fun getUsername():String{
        return storage.getString(SHARED_USERNAME, "")!!
    }

    fun savePassword(password:String){
        storage.edit().putString(SHARED_PASSWORD, password)
    }
    fun getPassword():String{
        return storage.getString(SHARED_PASSWORD, "")!!
    }

    fun saveEmail(email:String){
        storage.edit().putString(SHARED_EMAIL, email)
    }
    fun getEmail():String{
        return storage.getString(SHARED_EMAIL, "")!!
    }

    fun saveCreatedAt(createdAt:String){
        storage.edit().putString(SHARED_CREATEDAT, createdAt)
    }
    fun getCreatedAt():String{
        return storage.getString(SHARED_CREATEDAT, "")!!
    }

    fun saveTypeUser(typeUser:String){
        storage.edit().putString(SHARED_TYPEUSER, typeUser)
    }
    fun getTypeUser():String{
        return storage.getString(SHARED_TYPEUSER, "")!!
    }

    fun saveImgUser(imgUser:String){
        storage.edit().putString(SHARED_IMGUSER, imgUser)
    }
    fun getImgUser():String{
        return storage.getString(SHARED_IMGUSER, "")!!
    }

    fun wipe(){
        storage.edit().clear().apply()
    }

     */

    //FUNCION QUE PERMITE RECUPERAR LAS CREDENCIALES
    fun getCredentials():User{

        val user:User =  User()
        //ENCASO DE QUE NO HAYA DATOS REGRESA UN VALOR POR DEFAULT
        val username: String? =  storage.getString("username","")
        val password:String? =  storage.getString("password", "")
        val uid:String? =  storage.getString("uid", "")
        val email:String? =  storage.getString("email", "")
        val typeUser:String? =  storage.getString("typeUser", "")
        val imgUser:String? =  storage.getString("imgUser", "")
        val name:String? =  storage.getString("name", "")
        val createdAt:String? =  storage.getString("createdAt", "")
        val carrer:String? =  storage.getString("carrer", "")

        user.uid=uid!!
        user.username=username!!
        user.password=password!!
        user.email=email!!
        user.typeUser=typeUser!!
        user.imgUser=imgUser!!
        user.name=name!!
        user.createdAt=createdAt!!
        user.carrer=carrer!!

        //credential.strUser =  strUser!!
        //credential.strPassword =  strPassword!!

        return user
    }

    fun wipe(){
        storage.edit().clear().apply()
    }

    fun getUid(): String? {
        val uid:String? =  storage.getString("uid", "")
        return uid
    }

    fun getPassword():String?{
        val password:String?=storage.getString("password", "")
        return password
    }

}