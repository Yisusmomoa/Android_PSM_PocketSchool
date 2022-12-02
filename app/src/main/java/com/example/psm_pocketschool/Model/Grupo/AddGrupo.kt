package com.example.psm_pocketschool.Model.Grupo

import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Model.User.User
import com.google.gson.annotations.SerializedName

class AddGrupo {
    @SerializedName("nameGroup") var nameGroup:String = ""
    @SerializedName("listStudents") var listStudents:List<User>?=null
    @SerializedName("teacher") var teacher: String=""
    @SerializedName("listHomeworks") var listHomeworks:List<Tarea>?=null

    constructor()
    constructor(
        nameGroup: String,
        listStudents: List<User>?,
        teacher: String?,
        listHomeworks: List<Tarea>?
    ) {
        this.nameGroup = nameGroup
        this.listStudents = listStudents
        this.teacher = teacher!!
        this.listHomeworks = listHomeworks
    }

    constructor(nameGroup: String, teacher: String) {
        this.nameGroup = nameGroup
        this.teacher = teacher
    }

}