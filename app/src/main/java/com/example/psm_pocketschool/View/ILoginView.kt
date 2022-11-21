package com.example.psm_pocketschool.View

interface ILoginView {
    fun OnLoginSuccess(message: String?)
    fun OnLoginError(message: String?)
}