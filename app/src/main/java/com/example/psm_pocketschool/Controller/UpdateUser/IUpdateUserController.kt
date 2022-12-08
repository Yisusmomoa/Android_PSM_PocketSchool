package com.example.psm_pocketschool.Controller.UpdateUser

import com.example.psm_pocketschool.Model.User.User

interface IUpdateUserController {
    fun onUpdate(user:User)
    fun onUpdateImg(imgBase64:String)
}