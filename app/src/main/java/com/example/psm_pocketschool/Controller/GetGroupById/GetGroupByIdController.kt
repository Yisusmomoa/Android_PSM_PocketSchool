package com.example.psm_pocketschool.Controller.GetGroupById

import com.example.psm_pocketschool.Model.Grupo.Grupo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetGroupByIdController :IGetGroupByIdController{
    //private val myScope = CoroutineScope(Dispatchers.IO)
    private val getGroupByIdRepository=GetGroupByIdRepository()

    override suspend fun onGetGroup(uid: String): Grupo? {
            val grupo=getGroupByIdRepository.getGroupById(uid)
            if (grupo!=null){
                return grupo
            }
            return null
    }

}