package com.example.psm_pocketschool.Model.TareaAlumno

import com.example.psm_pocketschool.Model.User.User
import com.google.gson.annotations.SerializedName

class TareaAlumno {
    @SerializedName("_id") var uid:String = ""
    @SerializedName("dateDelivery") var dateDelivery:String = ""
    @SerializedName("pdfs") var pdfs:String = ""
    @SerializedName("student") var student:User?=null
    @SerializedName("status") var status:Boolean=false

    constructor()
    constructor(uid: String, dateDelivery: String, pdfs: String, student: User?, status: Boolean) {
        this.uid = uid
        this.dateDelivery = dateDelivery
        this.pdfs = pdfs
        this.student = student
        this.status = status
    }

}