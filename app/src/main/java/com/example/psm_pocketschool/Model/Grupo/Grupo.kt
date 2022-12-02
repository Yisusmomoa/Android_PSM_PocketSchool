package com.example.psm_pocketschool.Model.Grupo

import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Model.User.User
import com.google.gson.annotations.SerializedName

class Grupo {
    @SerializedName("_id") var uid:String = ""
    @SerializedName("nameGroup") var nameGroup:String = ""
    @SerializedName("listStudents") var listStudents:List<String>?=null
    var listStudentsStruct:List<User>?=null
    @SerializedName("teacher") var teacher:String=""
    var teacherStruct:User ?= null
    @SerializedName("listHomeworks") var listHomeworks:List<String>?=null
    var listHomeworksStruct:List<Tarea>?=null
    @SerializedName("createdAt") var createdAt:String = ""
    constructor()

    constructor(
        uid: String,
        nameGroup: String,
        listStudentsStruct: List<User>?,
        teacher: String,
        teacherStruct: User?,
        listHomeworksStruct: List<Tarea>?,
        createdAt: String
    ) {
        this.uid = uid
        this.nameGroup = nameGroup
        this.listStudentsStruct = listStudentsStruct
        this.teacher = teacher
        this.teacherStruct = teacherStruct
        this.listHomeworksStruct = listHomeworksStruct
        this.createdAt = createdAt
    }



    constructor(
        nameGroup: String,
        listStudentsStruct: List<User>?,
        teacher: String,
        listHomeworksStruct: List<Tarea>?
    ) {
        this.nameGroup = nameGroup
        this.listStudentsStruct = listStudentsStruct
        this.teacher = teacher
        this.listHomeworksStruct = listHomeworksStruct
    }

    constructor(nameGroup: String, teacher: String) {
        this.nameGroup = nameGroup
        this.teacher = teacher
    }

    constructor(
        uid: String,
        nameGroup: String,
        listStudents: List<String>?,
        teacher: String,
        listHomeworks: List<String>?,
        createdAt: String
    ) {
        this.uid = uid
        this.nameGroup = nameGroup
        this.listStudents = listStudents
        this.teacher = teacher
        this.listHomeworks = listHomeworks
        this.createdAt = createdAt
    }


}