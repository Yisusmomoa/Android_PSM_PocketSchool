package com.example.psm_pocketschool.Controller.GetHomeworksTeacher

import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.core.RetrofitHelper

class GetHomeworksTeacherRepository {
    suspend fun getHomeworksTeacher(uid:String):List<Tarea>?{
        val result= RetrofitHelper.getRetrofit().create(IGetHomeworksTeacherApiClient::class.java).getHomeworksTeacher(uid)
        if (result.isSuccessful){
            val bodyList:List<Tarea>?=result.body()
            return bodyList
        }
        return null
    }
}