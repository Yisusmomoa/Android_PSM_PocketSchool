package com.example.psm_pocketschool.Controller.AddHomework

import com.example.psm_pocketschool.Model.Tarea.AddTarea
import com.example.psm_pocketschool.Model.Tarea.Tarea

interface IAddHomeworkController {
    fun addHomework(tareas:List<AddTarea>)

}