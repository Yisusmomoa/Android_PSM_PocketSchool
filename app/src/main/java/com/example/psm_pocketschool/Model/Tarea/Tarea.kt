package com.example.psm_pocketschool.Model.Tarea

import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.google.gson.annotations.SerializedName

class Tarea {
    @SerializedName("_id") var uid:String = ""
    @SerializedName("title") var title:String = ""
    @SerializedName("description") var description:String = ""
    @SerializedName("pdfsTarea") var pdfsTarea:List<Tarea>?=null
    @SerializedName("dateIni") var dateIni:String = ""
    @SerializedName("dateFin") var dateFin:String = ""
    @SerializedName("tareasAlumnos") var tareasAlumnos:List<User>?=null
    @SerializedName("grupo") var grupo:Grupo ?= null

    constructor()
    constructor(
        uid: String,
        title: String,
        description: String,
        pdfsTarea: List<Tarea>?,
        dateIni: String,
        dateFin: String,
        tareasAlumnos: List<User>?,
        grupo: Grupo?
    ) {
        this.uid = uid
        this.title = title
        this.description = description
        this.pdfsTarea = pdfsTarea
        this.dateIni = dateIni
        this.dateFin = dateFin
        this.tareasAlumnos = tareasAlumnos
        this.grupo = grupo
    }


}