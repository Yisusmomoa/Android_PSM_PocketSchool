package com.example.psm_pocketschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.databinding.ActivityMain2Binding
import com.example.psm_pocketschool.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var titleHomework:String= intent.getStringExtra("titleHomework")!!
        var descHomework:String= intent.getStringExtra("descHomework")!!
        var listOfGrupos=intent.getSerializableExtra("listOfGrupos")

        Log.d("Tarea", titleHomework)
        Log.d("Tarea", descHomework)
        Log.d("Tarea", listOfGrupos.toString())

        //setContentView(R.layout.activity_main3)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}