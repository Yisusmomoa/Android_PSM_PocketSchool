package com.example.psm_pocketschool.Controller.GetHomeworks

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getSystemService
import com.example.psm_pocketschool.Controller.GetGroupById.GetGroupByIdController
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Session.UserApplication.Companion.dbHelper
import com.example.psm_pocketschool.View.IGetHomeworksView
import com.example.psm_pocketschool.core.isConnected
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GetHomeworksController(
    private val iGetHomeworksView: IGetHomeworksView
) :IGetHomeworksController{
    private val myScope = CoroutineScope(Dispatchers.IO)
    val getHomeworksRepository=GetHomeworksRepository()
    private val getGroupByIdController=GetGroupByIdController()
    override fun onGetHomeworks(uid: String) {

        myScope.launch {
            val tareas=getHomeworksRepository.getHomeworksByUser(uid)
            if (tareas!!.size>0){
                dbHelper.deleteTareas()
                tareas.forEach {
                    it.grupoStruct=getGroupByIdController.onGetGroup(it.grupo)
                    //borra y guarda en sqlite las tareas que se muestran en la pantalla home en offline

                    dbHelper.insertTarea(it)
                }
                iGetHomeworksView.onSuccessHomeworks(tareas as ArrayList<Tarea>)
            }
            else{
                //iGetHomeworksView.onSuccessHomeworks(dbHelper.getTareas() as ArrayList<Tarea>)
            }
        }
    }

}