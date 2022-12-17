package com.example.psm_pocketschool

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.psm_pocketschool.Controller.GetGroupsByUser.GetGroupsByUserController
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.Session.UserApplication
import com.example.psm_pocketschool.Session.UserApplication.Companion.dbHelper
import com.example.psm_pocketschool.View.IGetGroupsByUserView
import com.example.psm_pocketschool.core.isConnected
import com.example.psm_pocketschool.databinding.ActivityMain2Binding
import com.example.psm_pocketschool.databinding.ActivityMainBinding
import java.io.Serializable
import kotlin.math.log

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
        Log.d("ciclovida", "Ingresa a oncreate")
        //setContentView(R.layout.activity_main2)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isConnected.isConnected(this)){
            getGroupsByUserController= GetGroupsByUserController(this)
            //guardr grupos en sqlite
            var user= UserApplication.prefs.getCredentials()
            getGroupsByUserController!!.onGetGroups(user)
        }
        else{
            //initRV(dbHelper.getGroupsCreateTarea())
            groupsListStruct= dbHelper.getGroupsCreateTarea()
            initRV(groupsListStruct)
        }

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
        initRV(groupsListStruct)
        /*grupos.forEach {
            groupsList.add(it.nameGroup)
        }*/
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val grup: Grupo =groupsListStruct[position]
        listOfGrupos.add(grup.uid)
    }

    fun initRV(grupos: List<Grupo>){
        grupos.forEach {
            groupsList.add(it.nameGroup)
        }
        //pasar a una nueva fucnión
        runOnUiThread {
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.simple_list_item_multiple_choice, groupsList)
            //val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.list, groupsList)
            binding.listGrupos.choiceMode= ListView.CHOICE_MODE_MULTIPLE
            binding.listGrupos.adapter=arrayAdapter
            binding.listGrupos.onItemClickListener=this
        }
    }















    override fun onResume() {
        super.onResume()
        Log.d("ciclovida", "Ingresa a onresume")
    }

    //se pierde el foco, presiono el botón de atras o se cambio la pantalla o la minimizo
    override fun onPause() {
        super.onPause()
        Log.d("ciclovida", "Ingresa a onpause")
    }

    //ya no es visible en la pantalla
    override fun onStop() {
        super.onStop()
        Log.d("ciclovida", "Ingresa a onstop")
    }

    //inicia nuevamente la activity se reinicia el estado y pasa poor los estados anteriores
    override fun onRestart() {
        super.onRestart()
        Log.d("ciclovida", "Ingresa a onrestart")
    }
}