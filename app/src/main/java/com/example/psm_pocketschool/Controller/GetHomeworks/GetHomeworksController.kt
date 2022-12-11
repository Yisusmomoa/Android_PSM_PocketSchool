package com.example.psm_pocketschool.Controller.GetHomeworks

import android.util.Log
import com.example.psm_pocketschool.Controller.GetGroupById.GetGroupByIdController
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.View.IGetHomeworksView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class GetHomeworksController(
    private val iGetHomeworksView: IGetHomeworksView
) :IGetHomeworksController{
    private val myScope = CoroutineScope(Dispatchers.IO)
    val getHomeworksRepository=GetHomeworksRepository()
    private val getGroupByIdController=GetGroupByIdController()
    override fun onGetHomeworks(uid: String) {
        myScope.launch {
            val tareas=getHomeworksRepository.getHomeworksByUser(uid)
            if (tareas!=null){
                tareas.forEach {
                    it.grupoStruct=getGroupByIdController.onGetGroup(it.grupo)
                    //guardar en sqlite
                }



                iGetHomeworksView.onSuccessHomeworks(tareas as ArrayList<Tarea>)
            }
        }
    }

}