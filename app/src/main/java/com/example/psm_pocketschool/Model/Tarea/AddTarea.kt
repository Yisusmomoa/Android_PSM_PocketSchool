package com.example.psm_pocketschool.Model.Tarea

import com.google.gson.annotations.SerializedName

class AddTarea {
    @SerializedName("title") var title:String = ""
    @SerializedName("description") var description:String = ""
    @SerializedName("pdfsTarea") var pdfsTarea:List<String>?= listOf()
    @SerializedName("tareasAlumnos") var tareasAlumnos:List<String>?=listOf()
    @SerializedName("grupo") var grupo:String =""

    constructor()
    constructor(title: String, description: String, pdfsTarea: List<String>?, grupo: String) {
        this.title = title
        this.description = description
        this.pdfsTarea = pdfsTarea
        this.grupo = grupo
    }

}