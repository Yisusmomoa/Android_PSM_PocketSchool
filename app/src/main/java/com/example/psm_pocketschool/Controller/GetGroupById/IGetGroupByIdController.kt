package com.example.psm_pocketschool.Controller.GetGroupById

import com.example.psm_pocketschool.Model.Grupo.Grupo

interface IGetGroupByIdController {
    suspend fun onGetGroup(uid:String):Grupo?
}