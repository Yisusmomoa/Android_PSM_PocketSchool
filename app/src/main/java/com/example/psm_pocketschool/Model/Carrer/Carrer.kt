package com.example.psm_pocketschool.Model.Carrer

import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.google.gson.annotations.SerializedName

class Carrer {
    @SerializedName("_id") var uid:String = ""
    @SerializedName("name") var name:String = ""
    @SerializedName("listStudents") var listStudents:ArrayList<User>?=null
    @SerializedName("listGroups") var listGroups:ArrayList<Grupo>?=null
    @SerializedName("createdAt") var createdAt:String=""

    constructor()
    constructor(uid: String, name: String) {
        this.uid = uid
        this.name = name
    }

    constructor(
        uid: String,
        name: String,
        listStudents: ArrayList<User>?,
        listGroups: ArrayList<Grupo>?,
        createdAt: String
    ) {
        this.uid = uid
        this.name = name
        this.listStudents = listStudents
        this.listGroups = listGroups
        this.createdAt = createdAt
    }

}