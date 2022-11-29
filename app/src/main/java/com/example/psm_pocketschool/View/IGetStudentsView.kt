package com.example.psm_pocketschool.View

import com.example.psm_pocketschool.Model.User.User

interface IGetStudentsView {
    fun OnSuccessUsers(users:List<User>)
}