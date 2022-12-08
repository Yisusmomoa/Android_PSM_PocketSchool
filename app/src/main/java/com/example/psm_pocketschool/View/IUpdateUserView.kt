package com.example.psm_pocketschool.View

interface IUpdateUserView {
    fun OnUpdateSuccess(message: String?)
    fun OnUpdateError(message: String?)

    fun onUpdateImgSuccess(result:Boolean, message: String?)
}