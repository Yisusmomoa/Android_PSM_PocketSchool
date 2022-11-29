package com.example.psm_pocketschool.Controller.GetStudents

import android.util.Log
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.View.IGetStudentsView
import com.example.psm_pocketschool.core.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetStudentsController(
    private val iGetStudentsView: IGetStudentsView
) :IGetStudentsController{
    val getStudentsRepository=GetStudentsRepository()
    override fun onGetStudents() {
        /*return withContext(Dispatchers.IO){
            val users:List<User>?=getStudentsRepository.getStudents()
            if (users!=null){
                iGetStudentsView.OnSuccessUsers(users)
            }
        }*/

        GlobalScope.launch {
            val users:List<User>?=getStudentsRepository.getStudents()
            if (users!=null){
                iGetStudentsView.OnSuccessUsers(users)
            }
        }
    }

}