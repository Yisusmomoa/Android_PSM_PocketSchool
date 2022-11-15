package com.example.psm_pocketschool.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.psm_pocketschool.R
import com.example.psm_pocketschool.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        //val textViewSignUp=findViewById<TextView>(R.id.textViewSignUp)
        binding.textViewSignUp.setOnClickListener(this)
        //val btnLogin=findViewById<Button>(R.id.btnLogin)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val itemId= v?.id
        when(itemId){
            R.id.textViewSignUp ->{
                Log.d("Click", "sign up")
                val intent=Intent(this, SignUp::class.java)
                startActivity(intent)
            }
            R.id.btnLogin ->{
                Log.d("Click", "Loginbtn")
                val intent=Intent(this, Home::class.java)
                startActivity(intent)
            }
        }
    }
}