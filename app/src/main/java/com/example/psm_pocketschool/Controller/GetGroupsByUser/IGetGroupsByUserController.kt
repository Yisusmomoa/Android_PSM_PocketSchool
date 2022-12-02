package com.example.psm_pocketschool.Controller.GetGroupsByUser

import com.example.psm_pocketschool.Model.User.User

interface IGetGroupsByUserController {
    fun onGetGroups(user:User)
}