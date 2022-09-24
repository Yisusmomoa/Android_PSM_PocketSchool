package com.example.psm_pocketschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class SignUp : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val textViewLogin=findViewById<TextView>(R.id.textViewLogin)
        textViewLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val itemId= v?.id
        when(itemId){
            R.id.textViewLogin->{
                Log.d("Click", "login")
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}