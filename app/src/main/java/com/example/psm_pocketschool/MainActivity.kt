package com.example.psm_pocketschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textViewSignUp=findViewById<TextView>(R.id.textViewSignUp)
        textViewSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val itemId= v?.id
        when(itemId){
            R.id.textViewSignUp->{
                Log.d("Click", "sign up")
                val intent=Intent(this, SignUp::class.java)
                startActivity(intent)
            }
        }
    }
}