package com.example.psm_pocketschool.Controller.AddGroup

import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User

interface IAddGroupController {
    fun addGroup(grupo:Grupo, Students: List<User>)
    //fun addStudentsGroup(idGrupo: String, user: User)

}