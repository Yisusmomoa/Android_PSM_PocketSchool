package com.example.psm_pocketschool.Controller.GetGroupsByUser

import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.View.IGetGroupsByUserView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetGroupsByUserController (
    private val iGetGroupsByUserView: IGetGroupsByUserView
    ):IGetGroupsByUserController{
    val getGroupsByUserRepository=GetGroupsByUserRepository()
    override fun onGetGroups(user: User) {
        GlobalScope.launch {
            val groups:List<Grupo>?=getGroupsByUserRepository.getGroupsByUser(user.uid)
            if (groups!=null){
                //iGetStudentsView.OnSuccessUsers(users)
                iGetGroupsByUserView.onSuccessGroups(groups)
            }
        }
    }
}