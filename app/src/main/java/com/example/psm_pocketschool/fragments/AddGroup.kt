package com.example.psm_pocketschool.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.psm_pocketschool.Controller.AddGroup.AddGroupController
import com.example.psm_pocketschool.Controller.GetStudents.GetStudentsController
import com.example.psm_pocketschool.Model.Carrer.Carrer
import com.example.psm_pocketschool.Model.Grupo.AddGrupo
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.R
import com.example.psm_pocketschool.Session.UserApplication
import com.example.psm_pocketschool.View.IAddGroupView
import com.example.psm_pocketschool.View.IGetStudentsView
import com.example.psm_pocketschool.databinding.FragmentAddGroupBinding
import com.example.psm_pocketschool.databinding.FragmentProfileBinding

class AddGroup : Fragment(), AdapterView.OnItemClickListener, IGetStudentsView, View.OnClickListener, IAddGroupView {

    //TODO
    private var _binding: FragmentAddGroupBinding?=null
    private val binding get()=_binding!!

    private var getStudentsController:GetStudentsController?=null
    private var addGroupController:AddGroupController?=null

    //private lateinit var users: ArrayAdapter<String>
    private var arrayListUsers:ArrayList<User> = ArrayList()
    private var listOfStudents= arrayListOf<User>()

    var listOfItem=arrayListOf<String>()
    private lateinit var teacher:User
    lateinit var arrayAdapter:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getStudentsController=GetStudentsController(this)
        getStudentsController!!.onGetStudents()

        addGroupController= AddGroupController(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=FragmentAddGroupBinding.inflate(inflater, container, false)
        binding.btnCreateGgroup.setOnClickListener(this)
        /*
        binding.idSV.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.idSV.clearFocus()
                if(listOfItem.contains(query)){
                    arrayAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                arrayAdapter.filter.filter(newText)
                return false
            }

        })*/


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setMultipleListView():ArrayList<String>{
        val arrayList:ArrayList<String> = ArrayList()
        for(i in 1..30){
            arrayList.add("Application List $i")
        }

        return arrayList
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //val options:String = parent?.getItemAtPosition(position) as String
        val us: User =arrayListUsers[position]
        if(listOfStudents.contains(us)){
            listOfStudents.remove(us)
            Log.d("listUsers", "remove")
            listOfStudents.forEach {
                Log.d("listUsers", it.uid)
            }
        }
        else{
            listOfStudents.add(us)
            Log.d("listUsers", "add")
            listOfStudents.forEach {
                Log.d("listUsers", it.uid)
            }
        }

    }

    override fun OnSuccessUsers(users: List<User>) {
        arrayListUsers= users as ArrayList<User>
        arrayListUsers.forEach {
            listOfItem.add(it.name)
        }

        requireActivity().runOnUiThread {
            arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_list_item_multiple_choice, listOfItem)
            binding.listStudentsAdd.choiceMode=ListView.CHOICE_MODE_MULTIPLE
            binding.listStudentsAdd.adapter=arrayAdapter
            binding.listStudentsAdd.onItemClickListener=this
        }
    }

    override fun onClick(v: View?) {
        val itemId= v?.id
        when(itemId){
            R.id.btnCreateGgroup->{
                if (binding.editTextGroupName.text.isNotEmpty() || binding.editTextGroupName.text.isNotEmpty()){
                    teacher= UserApplication.prefs.getCredentials()
                    //val grupoAux=Grupo(binding.editTextGroupName.text.toString(),teacher.uid )
                    val grupoAux=AddGrupo(binding.editTextGroupName.text.toString(),teacher.uid)
                    addGroupController!!.addGroup(grupoAux, listOfStudents)
                }
                else{
                    Toast.makeText(activity,"Debes capturar un nombre", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    override fun OnAddGroupSuccess(message: String?) {
        requireActivity().runOnUiThread {
            Toast.makeText(activity,message, Toast.LENGTH_LONG).show()
            val fragmentB = Home()
            activity?.getSupportFragmentManager()!!.beginTransaction()
                .replace(R.id.frame_layout, fragmentB, "fragmnetId")
                .commit();
        }

    }

    override fun OnAddGroupError(message: String?) {

    }


}