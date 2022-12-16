package com.example.psm_pocketschool.Controller.UpdateHomework

import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Model.User.User

interface IUpdateHomeworkController {
    fun onUpdate(tarea: Tarea)
    fun onAddPdf(imgBase64:String)
    fun onRemovePdf()
}