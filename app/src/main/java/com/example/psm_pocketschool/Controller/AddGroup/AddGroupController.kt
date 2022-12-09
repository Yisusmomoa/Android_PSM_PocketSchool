package com.example.psm_pocketschool.Controller.AddGroup

import com.example.psm_pocketschool.Controller.GetStudents.GetStudentsRepository
import com.example.psm_pocketschool.Model.Grupo.AddGrupo
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.View.IAddGroupView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddGroupController(
    private val iAddGroupView: IAddGroupView
) :IAddGroupController{
    private val addGroupRepository=AddGroupRepository()

    override fun addGroup(grupo: AddGrupo, students: List<User>) {
        grupo.listStudents= listOf()
        grupo.listHomeworks= listOf()
        /*val addGrupo=AddGrupo(grupo.nameGroup,grupo.listStudents,
            grupo.teacher, grupo.listHomeworks )*/

        GlobalScope.launch {
            val idGrupo=addGroupRepository.addGroup(grupo)
            var error:Boolean=false
            if (idGrupo.isNotBlank()){
                students.forEach {
                    if (!addGroupRepository.addStudents(it, idGrupo)){
                        error=true
                    }
                }
                //error?iAddGroupView.OnAddGroupSuccess("Grupo añadido con exito"):iAdd
                if(error){
                    iAddGroupView.OnAddGroupSuccess("Error, intenta más tarde")
                    //iAddGroupView.OnAddGroupError("Intente, más tarde")
                } else{
                    iAddGroupView.OnAddGroupSuccess("Grupo añadido con exito")
                }
            }
            iAddGroupView.OnAddGroupError("Error, Intente, más tarde")
        }

    }


}



/*
* {
  "_id": "638686cf1930f3fa51a17788",
  "nameGroup": "Grupo 3",
  "listStudents": [],
  "teacher": "635ede1ad3b1fff391e1a186",
  "listHomeworks": [],
  "createdAt": "2022-11-29T22:25:19.251Z",
  "updatedAt": "2022-11-29T22:25:19.251Z"
}
*
* */