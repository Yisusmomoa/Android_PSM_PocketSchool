package com.example.psm_pocketschool

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.psm_pocketschool.Controller.GetGroupsByUser.GetGroupsByUserController
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.Session.UserApplication
import com.example.psm_pocketschool.View.IGetGroupsByUserView
import com.example.psm_pocketschool.databinding.ActivityMain2Binding
import com.example.psm_pocketschool.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity2 : AppCompatActivity(), IGetGroupsByUserView, AdapterView.OnItemClickListener {
    private lateinit var binding: ActivityMain2Binding

    private var getGroupsByUserController: GetGroupsByUserController?=null
    private var groupsList=arrayListOf<String>()
    private var groupsListStruct:List<Grupo> = listOf()
    private var listOfGrupos:ArrayList<String> = ArrayList()
    private var titleHomework:String=""
    private var descHomework:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        getGroupsByUserController= GetGroupsByUserController(this)
        var user= UserApplication.prefs.getCredentials()
        getGroupsByUserController!!.onGetGroups(user)

        //val btnNextTarea=findViewById<Button>(R.id.btnNextTarea)
        binding.btnNextTarea.setOnClickListener {
            titleHomework=binding.editTextTitleHomework.text.toString()
            descHomework=binding.editTextDescHomework.text.toString()

            val intent=Intent(this, MainActivity3::class.java)
            intent.putExtra("titleHomework", titleHomework)
            intent.putExtra("descHomework", descHomework)
            intent.putExtra("listOfGrupos",  listOfGrupos)
            startActivity(intent)
        }

    }

    override fun onSuccessGroups(grupos: List<Grupo>) {
        groupsListStruct= grupos as ArrayList<Grupo>
        grupos.forEach {
            groupsList.add(it.nameGroup)
        }

        runOnUiThread {
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.simple_list_item_multiple_choice, groupsList)
            //val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.list, groupsList)
            binding.listGrupos.choiceMode= ListView.CHOICE_MODE_MULTIPLE
            binding.listGrupos.adapter=arrayAdapter
            binding.listGrupos.onItemClickListener=this
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val grup: Grupo =groupsListStruct[position]
        listOfGrupos.add(grup.uid)
    }
}