package com.example.psm_pocketschool.Controller.UpdateHomework

import com.example.psm_pocketschool.Controller.UpdateUser.UpdateUserRepository
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.View.IUpdateHomeworkView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateHomeworkController(
    private val iUpdateHomeworkView: IUpdateHomeworkView
) :IUpdateHomeworkController{
    private val updateHomeworkRepository=UpdateHomeworkRepository()
    private val myScope = CoroutineScope(Dispatchers.IO)

    override fun onUpdate(tarea: Tarea) {
        myScope.launch {
            if (updateHomeworkRepository.onUpdateHomework(tarea)){
                iUpdateHomeworkView.OnUpdateSuccess("Tarea editada con exito")
            }
            else{
                iUpdateHomeworkView.OnUpdateSuccess("Error, al querer editar su tarea")
            }
        }
    }

    override fun onAddPdf(imgBase64: String) {
        TODO("Not yet implemented")
    }

    override fun onRemovePdf() {
        TODO("Not yet implemented")
    }
}