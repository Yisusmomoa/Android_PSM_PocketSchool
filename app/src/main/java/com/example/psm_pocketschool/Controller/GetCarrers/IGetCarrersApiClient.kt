package com.example.psm_pocketschool.Controller.GetCarrers

import com.example.psm_pocketschool.Model.Carrer.CarrersResult
import com.example.psm_pocketschool.Model.User.UserResult
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface IGetCarrersApiClient {
    @GET("carrers/")
    suspend fun getCarrers() : Response<CarrersResult>
}