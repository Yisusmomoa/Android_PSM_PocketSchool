package com.example.psm_pocketschool.Controller.DeleteHomework

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DeleteHomeworkController :IDeleteHomeworkController{
    private val myScope = CoroutineScope(Dispatchers.IO)
    private val deleteHomeworkRepository=DeleteHomeworkRepository()
    var result:Boolean=false
    override fun onDeleteHomework(uid: String): Boolean {
        myScope.launch {
            result=deleteHomeworkRepository.onDeleteHomework(uid)
        }
        return result
    }
}