package com.example.psm_pocketschool.Controller.AddHomework

import com.example.psm_pocketschool.Model.Tarea.AddTarea
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.View.IAddHomeworkView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddHomeworkController(private val iAddHomeworkView: IAddHomeworkView) :IAddHomeworkController{

    private val addHomeworkRepository=AddHomeworkRepository()

    override fun addHomework(tareas: List<AddTarea>) {
        var valid:Boolean=true
        GlobalScope.launch {
            for (it in tareas){
                it.tareasAlumnos=listOf()
                if (!addHomeworkRepository.addHomework(it)){
                    iAddHomeworkView.OnAddHomeworkError("Error, intenta más tarde")
                    valid=false
                }
            }
            if (valid){
                iAddHomeworkView.OnAddHomeworkSuccess("Tarea subida con exito")
            }
        }

    }

}


/*
{
  "title":"tarea 1",
  "description":"desc tarea 1",
  "pdfsTarea":[],
  "tareasAlumnos":[],
  "grupo":"635eed8b7d96d9989c15d1f5"
}

{
  "title": "tarea 1",
  "description": "desc tarea 1",
  "pdfsTarea": [],
  "tareasAlumnos": [],
  "grupo": "635eed8b7d96d9989c15d1f5",
  "_id": "639029fff96def4cb30594df",
  "createdAt": "2022-12-07T05:51:59.996Z",
  "updatedAt": "2022-12-07T05:51:59.996Z"
}
*
* */