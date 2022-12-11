package com.example.psm_pocketschool.View

import com.example.psm_pocketschool.Model.Carrer.Carrer
import com.example.psm_pocketschool.Model.Tarea.Tarea

interface IGetHomeworksView {
    fun onSuccessHomeworks(homeworks:ArrayList<Tarea>)
}