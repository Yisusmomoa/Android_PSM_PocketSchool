package com.example.psm_pocketschool.Controller.GetGroupsByUser

import android.util.Log
import com.example.psm_pocketschool.Controller.GetUserById.GetUserByIdRepository
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.core.RetrofitHelper
import retrofit2.create

class GetGroupsByUserRepository {
    suspend fun getGroupsByUser(uid:String):List<Grupo>?{
        val result=RetrofitHelper.getRetrofit().create(IGetGroupsApiClient::class.java).getGroupsById(uid)
        val getUserByIdRepository=GetUserByIdRepository()
        //TODO mapear de un grupo a otro, grupo donde teacher, liststudents, homework son strings a objetos
        if (result.isSuccessful){
            val bodyList:List<Grupo>?=result.body()
            var listGroups:Grupo
            if (bodyList != null) {
                bodyList.forEach {
                    var teacher=getUserByIdRepository.getUserById(it.teacher)
                    it.teacherStruct=teacher
                    it.listStudents?.forEach { idus->
                        var studentAux=getUserByIdRepository.getUserById(idus)
                        it.listStudentsStruct?.add(studentAux!!)
                    }
                    //primero traer el teacher
                }
                Log.d("Grupo: ",bodyList.toString())
                return bodyList
            }

        }
            return null

    }
}

/*
* [
  {
    "_id": "635eed8b7d96d9989c15d1f5",
    "nameGroup": "Grupo 1",
    "listStudents": [
      "635cce4b100b21eda01a8a3d"
    ],
    "teacher": "635ede20d3b1fff391e1a188",
    "listHomeworks": [],
    "createdAt": "2022-10-30T21:32:59.378Z",
    "updatedAt": "2022-10-31T01:06:28.614Z"
  }
]
*
* */