package com.example.psm_pocketschool.Model.User

interface IUser {
    fun getEmail(): String?
    fun getPassword(): String?
    fun isValid(): Int
    fun getUid():String?
    fun getInfo():User?

}