package com.example.psm_pocketschool.Controller.GetHomeworksTeacher

import com.example.psm_pocketschool.Controller.GetGroupById.GetGroupByIdController
import com.example.psm_pocketschool.Controller.GetHomeworks.GetHomeworksRepository
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Session.UserApplication
import com.example.psm_pocketschool.View.IGetHomeworksTeacherView
import com.example.psm_pocketschool.View.IGetHomeworksView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetHomeworksTeacherController(
    private val iGetHomeworksTeacherView: IGetHomeworksTeacherView
) :IGetHomeworksTeacher{
    private val myScope = CoroutineScope(Dispatchers.IO)
    val getHomeworksTeacherRepository=GetHomeworksTeacherRepository()
    private val getGroupByIdController= GetGroupByIdController()

    override fun onGetHomeworks(uid: String) {
        myScope.launch {
            val tareas=getHomeworksTeacherRepository.getHomeworksTeacher(uid)
            if (tareas!!.size>0){
                tareas.forEach {
                    it.grupoStruct=getGroupByIdController.onGetGroup(it.grupo)
                }
                iGetHomeworksTeacherView.onSuccessHomeworks(tareas as ArrayList<Tarea>)
            }
        }
    }
}