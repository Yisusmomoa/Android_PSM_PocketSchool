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

    override fun addGroup(grupo: Grupo, students: List<User>) {
        grupo.listStudents= listOf()
        grupo.listHomeworks= listOf()
        val addGrupo=AddGrupo(grupo.nameGroup,grupo.listStudents,
            grupo.teacher!!.uid, grupo.listHomeworks )

        GlobalScope.launch {
            val idGrupo=addGroupRepository.addGroup(grupo)
            var error:Boolean=true
            if (idGrupo.isNotBlank()){
                students.forEach {
                    if (!addGroupRepository.addStudents(it, idGrupo)){
                        error=false
                    }
                }
                //error?iAddGroupView.OnAddGroupSuccess("Grupo añadido con exito"):iAdd
                if(error)iAddGroupView.OnAddGroupSuccess("Grupo añadido con exito") else iAddGroupView.OnAddGroupError("Error, Intente, más tarde")
            }
            iAddGroupView.OnAddGroupError("Error, Intente, más tarde")
        }

    }


}