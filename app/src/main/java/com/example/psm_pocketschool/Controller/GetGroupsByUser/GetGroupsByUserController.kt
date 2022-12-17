package com.example.psm_pocketschool.Controller.GetGroupsByUser

import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.Session.UserApplication.Companion.dbHelper
import com.example.psm_pocketschool.Session.UserApplication.Companion.prefs
import com.example.psm_pocketschool.View.IGetGroupsByUserView
import kotlinx.coroutines.*

class GetGroupsByUserController (
    private val iGetGroupsByUserView: IGetGroupsByUserView
    ):IGetGroupsByUserController{
    private val myScope = CoroutineScope(Dispatchers.IO)
    val getGroupsByUserRepository=GetGroupsByUserRepository()
    override fun onGetGroups(user: User) {

        myScope.launch {
            val groups:List<Grupo>?=getGroupsByUserRepository.getGroupsByUser(user.uid)
            if (groups!=null){
                //iGetStudentsView.OnSuccessUsers(users)
                //if (prefs.getGroupsOffline()){
                    dbHelper.deleteGroups()
                    dbHelper.insertGroupsCreateTarea(groups)
                    //prefs.saveGroupsOffline(false)
                //}
                iGetGroupsByUserView.onSuccessGroups(groups)
            }
        }

        /*GlobalScope.launch {
            val groups:List<Grupo>?=getGroupsByUserRepository.getGroupsByUser(user.uid)
            if (groups!=null){
                //iGetStudentsView.OnSuccessUsers(users)
                iGetGroupsByUserView.onSuccessGroups(groups)
            }
        }*/
    }
}