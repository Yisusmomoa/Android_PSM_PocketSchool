package com.example.psm_pocketschool.Controller.Login

import com.example.psm_pocketschool.Model.User.User

interface ILoginController {
    fun onLogin(user: User)
}