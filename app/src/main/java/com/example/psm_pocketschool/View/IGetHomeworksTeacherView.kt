package com.example.psm_pocketschool.View

import com.example.psm_pocketschool.Model.Tarea.Tarea

interface IGetHomeworksTeacherView {
    fun onSuccessHomeworks(homeworks:ArrayList<Tarea>)
}