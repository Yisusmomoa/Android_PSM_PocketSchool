package com.example.psm_pocketschool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.psm_pocketschool.databinding.ActivityMain2Binding
import com.example.psm_pocketschool.databinding.ActivityUpdatePdfHomeworkBinding

class UpdatePdfHomework : AppCompatActivity() {
    private lateinit var binding:ActivityUpdatePdfHomeworkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdatePdfHomeworkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_update_pdf_homework)
    }
}