package com.example.psm_pocketschool.Model.Grupo

import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Model.User.User
import com.google.gson.annotations.SerializedName

class Grupo {
    @SerializedName("_id") var uid:String = ""
    @SerializedName("nameGroup") var nameGroup:String = ""
    @SerializedName("listStudents") var listStudents:ArrayList <String>?=null
    var listStudentsStruct:ArrayList <User>?= ArrayList()
    @SerializedName("teacher") var teacher:String=""
    var teacherStruct:User ?= null
    @SerializedName("listHomeworks") var listHomeworks:ArrayList <String>?=null
    var listHomeworksStruct:ArrayList <Tarea>?=ArrayList()
    @SerializedName("createdAt") var createdAt:String = ""
    constructor()

    constructor(
        uid: String,
        nameGroup: String,
        listStudentsStruct: ArrayList <User>?,
        teacher: String,
        teacherStruct: User?,
        listHomeworksStruct: ArrayList <Tarea>?,
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
        listStudentsStruct: ArrayList <User>?,
        teacher: String,
        listHomeworksStruct: ArrayList <Tarea>?
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
        listStudents: ArrayList <String>?,
        teacher: String,
        listHomeworks: ArrayList <String>?,
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