package com.example.psm_pocketschool.View

import com.example.psm_pocketschool.Model.Carrer.Carrer
import com.example.psm_pocketschool.Model.Grupo.Grupo

interface IGetGroupsByUserView {
    fun onSuccessGroups(grupos:List<Grupo>)
}