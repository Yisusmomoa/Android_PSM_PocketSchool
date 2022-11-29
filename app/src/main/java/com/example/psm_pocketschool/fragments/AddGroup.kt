package com.example.psm_pocketschool.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.psm_pocketschool.Controller.AddGroup.AddGroupController
import com.example.psm_pocketschool.Controller.GetStudents.GetStudentsController
import com.example.psm_pocketschool.Model.Carrer.Carrer
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.R
import com.example.psm_pocketschool.Session.UserApplication
import com.example.psm_pocketschool.View.IAddGroupView
import com.example.psm_pocketschool.View.IGetStudentsView
import com.example.psm_pocketschool.databinding.FragmentAddGroupBinding
import com.example.psm_pocketschool.databinding.FragmentProfileBinding

class AddGroup : Fragment(), AdapterView.OnItemClickListener,IGetStudentsView, View.OnClickListener, IAddGroupView {

    //TODO getStudents,
    private var _binding: FragmentAddGroupBinding?=null
    private val binding get()=_binding!!

    private var getStudentsController:GetStudentsController?=null
    private var addGroupController:AddGroupController?=null
    //private lateinit var users: ArrayAdapter<String>
    private var arrayListUsers:ArrayList<User> = ArrayList()
    private var listOfStudents= arrayListOf<User>()

    var listOfItem=arrayListOf<String>()
    private lateinit var teacher:User

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

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_group, container, false)
        _binding=FragmentAddGroupBinding.inflate(inflater, container, false)
        binding.btnCreateGgroup.setOnClickListener(this)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val listView:ListView=view.findViewById(R.id.listStudentsAdd)
        //listOfItem= setMultipleListView()

        //val arrayAdapter:ArrayAdapter<String> =ArrayAdapter(view.context, android.R.layout.simple_list_item_multiple_choice, listOfItem)

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
        listOfStudents.add(us)
        Log.d("user", us.toString())
        Log.d("listUsers", listOfStudents.toString())

        /*if (view != null) {
            Toast.makeText(view.context, "Clicked by: $options", Toast.LENGTH_SHORT).show()
        }*/
    }

    override fun OnSuccessUsers(users: List<User>) {
        arrayListUsers= users as ArrayList<User>
        arrayListUsers.forEach {
            listOfItem.add(it.name)
        }

        requireActivity().runOnUiThread {
            val arrayAdapter:ArrayAdapter<String> =ArrayAdapter(requireContext(), android.R.layout.simple_list_item_multiple_choice, listOfItem)
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
                    val grupoAux=Grupo(binding.editTextGroupName.text.toString(),teacher )
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
        }
        val fragmentB = Home()
        activity?.getSupportFragmentManager()!!.beginTransaction()
            .replace(R.id.frame_layout, fragmentB, "fragmnetId")
            .commit();
    }

    override fun OnAddGroupError(message: String?) {
        requireActivity().runOnUiThread {
            Toast.makeText(activity,message, Toast.LENGTH_LONG).show()
        }
    }


}