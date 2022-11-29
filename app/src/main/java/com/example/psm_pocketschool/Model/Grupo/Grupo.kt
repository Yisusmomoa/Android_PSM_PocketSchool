package com.example.psm_pocketschool.Model.Grupo

import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Model.User.User
import com.google.gson.annotations.SerializedName

class Grupo {
    @SerializedName("_id") var uid:String = ""
    @SerializedName("nameGroup") var nameGroup:String = ""
    @SerializedName("listStudents") var listStudents:List<User>?=null
    @SerializedName("teacher") var teacher:User ?= null
    @SerializedName("listHomeworks") var listHomeworks:List<Tarea>?=null
    @SerializedName("createdAt") var createdAt:String = ""
    constructor()

    constructor(
        uid: String,
        nameGroup: String,
        listStudents: List<User>?,
        teacher: User?,
        listHomeworks: List<Tarea>?,
        createdAt: String
    ) {
        this.uid = uid
        this.nameGroup = nameGroup
        this.listStudents = listStudents
        this.teacher = teacher
        this.listHomeworks = listHomeworks
        this.createdAt = createdAt
    }

    constructor(
        nameGroup: String,
        listStudents: List<User>?,
        teacher: User?,
        listHomeworks: List<Tarea>?
    ) {
        this.nameGroup = nameGroup
        this.listStudents = listStudents
        this.teacher = teacher
        this.listHomeworks = listHomeworks
    }

    constructor(nameGroup: String, teacher: User?) {
        this.nameGroup = nameGroup
        this.teacher = teacher
    }


}