package com.example.psm_pocketschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.psm_pocketschool.Controller.UpdateHomework.UpdateHomeworkController
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.View.IUpdateHomeworkView
import com.example.psm_pocketschool.databinding.ActivityMainBinding
import com.example.psm_pocketschool.databinding.ActivityUpdateInfoHomeworkBinding
import com.example.psm_pocketschool.fragments.*
import com.example.psm_pocketschool.fragments.Home

class UpdateInfoHomework : AppCompatActivity(), IUpdateHomeworkView {
    private lateinit var binding: ActivityUpdateInfoHomeworkBinding
    private lateinit var updateHomeworkController:UpdateHomeworkController
    private lateinit var tarea:Tarea
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateInfoHomeworkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_update_info_homework)
        /*Log.d("pass", intent.getStringExtra("uid").toString())
        Log.d("pass", intent.getStringExtra("title").toString())
        Log.d("pass", intent.getStringExtra("descr").toString())
        Log.d("pass", intent.getStringExtra("fechaFin").toString())*/
        tarea= Tarea(intent.getStringExtra("uid").toString(),intent.getStringExtra("title").toString(),
            intent.getStringExtra("descr").toString(), "2022/12/15")
        updateHomeworkController= UpdateHomeworkController(this)
        binding.editTextTitleHomework.setText(tarea.title)
        binding.editTextDescHomework.setText(tarea.description)
        updateHomeworkController= UpdateHomeworkController(this)
        //binding.editTextDate.setText(tarea.dateFin)

        binding.btnActuaizarTarea.setOnClickListener {
            tarea.title= binding.editTextTitleHomework.text.toString()
            tarea.description=binding.editTextDescHomework.text.toString()
            updateHomeworkController.onUpdate(tarea)
        }

    }

    override fun OnUpdateSuccess(message: String?) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, com.example.psm_pocketschool.Home::class.java))
            /*val fragmentMisTareasBinding= MisTareas()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout,fragmentMisTareasBinding)
                .commit()*/
        }
    }

    override fun OnAddPdfSuccess(message: String?) {
        TODO("Not yet implemented")
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}