package com.example.psm_pocketschool.Controller.GetCarrers

import android.util.Log
import com.example.psm_pocketschool.Model.Carrer.Carrer
import com.example.psm_pocketschool.Model.Carrer.CarrersResult
import com.example.psm_pocketschool.Model.User.UserResult
import com.example.psm_pocketschool.View.IGetCarrersView
import com.example.psm_pocketschool.core.RetrofitHelper
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import okhttp3.RequestBody
import retrofit2.Response
import kotlin.math.log

class GetCarrersController(private val getCarrersView: IGetCarrersView) {
    private var retrofit= RetrofitHelper.getRetrofit()
    var listCarrers= ArrayList<Carrer>()
    fun getCarrers(){
        CoroutineScope(Dispatchers.IO).launch {
            val response=retrofit.create(IGetCarrersApiClient::class.java).getCarrers()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    //getCarrersView.onSuccessCarrers()
                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()

                    val prettyJson = gson.toJson(
                            response.body()
                    )
                    response.body()!!.result!!.forEach{
                        listCarrers.add(Carrer(it.uid, it.name))
                    }
                    getCarrersView.onSuccessCarrers(listCarrers)
                    Log.d("grupo lmad", response.body()!!.result!![0].toString())
                    Log.d("prettyJson", prettyJson)
                    Log.d("response", response.body().toString())

                }
            }
        }
    }

}


/*
* {
  "result": [
    {
      "_id": "635daa4500be52975afa7fe1",
      "name": "LMAD",
      "listStudents": [ ],
      "listGroups": [],
      "createdAt": "2022-10-29T22:33:41.222Z",
      "updatedAt": "2022-10-30T21:01:30.628Z"
    },
    {
      "_id": "635dab5900be52975afa7fe3",
      "name": "LCC",
      "listStudents": [],
      "listGroups": [],
      "createdAt": "2022-10-29T22:38:17.469Z",
      "updatedAt": "2022-10-30T21:03:37.258Z"
    }
  ]
}
*
* */