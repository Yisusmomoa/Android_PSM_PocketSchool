package com.example.psm_pocketschool.Model.Tarea

import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.google.gson.annotations.SerializedName

class Tarea {
    @SerializedName("_id") var uid:String = ""
    @SerializedName("title") var title:String = ""
    @SerializedName("description") var description:String = ""
    @SerializedName("pdfsTarea") var pdfsTarea:List<String>?=null
    @SerializedName("dateIni") var dateIni:String = ""
    @SerializedName("dateFin") var dateFin:String = ""
    @SerializedName("tareasAlumnos") var tareasAlumnos:List<String>?=null
    var tareasAlumnosStruct:List<User>?=null
    @SerializedName("grupo") var grupo:String =""
    var grupoStruct:Grupo ?= null

    constructor()

    constructor(
        uid: String,
        title: String,
        description: String,
        pdfsTarea: List<String>?,
        dateIni: String,
        dateFin: String,
        tareasAlumnos: List<String>?,
        tareasAlumnosStruct: List<User>?,
        grupo: String,
        grupoStruct: Grupo?
    ) {
        this.uid = uid
        this.title = title
        this.description = description
        this.pdfsTarea = pdfsTarea
        this.dateIni = dateIni
        this.dateFin = dateFin
        this.tareasAlumnos = tareasAlumnos
        this.tareasAlumnosStruct = tareasAlumnosStruct
        this.grupo = grupo
        this.grupoStruct = grupoStruct
    }

    constructor(title: String, description: String, pdfsTarea: List<String>?, grupo: String) {
        this.title = title
        this.description = description
        this.pdfsTarea = pdfsTarea
        this.grupo = grupo
    }

    constructor(title: String, description: String, pdfsTarea: List<String>?) {
        this.title = title
        this.description = description
        this.pdfsTarea = pdfsTarea
    }

    constructor(title: String, description: String, grupo: String) {
        this.title = title
        this.description = description
        this.grupo = grupo
    }


}