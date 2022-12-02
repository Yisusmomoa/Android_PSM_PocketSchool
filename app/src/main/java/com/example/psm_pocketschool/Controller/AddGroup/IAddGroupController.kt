package com.example.psm_pocketschool.Controller.AddGroup

import com.example.psm_pocketschool.Model.Grupo.AddGrupo
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User

interface IAddGroupController {
    fun addGroup(grupo:AddGrupo, Students: List<User>)
    //fun addStudentsGroup(idGrupo: String, user: User)

}