package com.example.psm_pocketschool.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.psm_pocketschool.R

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val btnNextTarea=findViewById<Button>(R.id.btnNextTarea)
        btnNextTarea.setOnClickListener {
            val intent=Intent(this, MainActivity3::class.java)
            startActivity(intent)

        }
    }
}