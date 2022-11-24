package com.example.psm_pocketschool.View

import com.example.psm_pocketschool.Model.Carrer.Carrer

interface IGetCarrersView {
    fun onSuccessCarrers(carrers:ArrayList<Carrer>)
}