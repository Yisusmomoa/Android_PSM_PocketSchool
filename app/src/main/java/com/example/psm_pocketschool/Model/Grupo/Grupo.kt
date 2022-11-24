package com.example.psm_pocketschool.Model.Grupo

import com.example.psm_pocketschool.Model.User.User
import com.google.gson.annotations.SerializedName

class Grupo {
    @SerializedName("_id") var uid:String = ""
    @SerializedName("nameGroup") var nameGroup:String = ""
    @SerializedName("listStudents") var listStudents:ArrayList<User>?=null
    @SerializedName("teacher") var teacher:User ?= null
    @SerializedName("listHomeworks") var listHomeworks:String = ""
    @SerializedName("createdAt") var createdAt:String = ""
}