package com.example.psm_pocketschool

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.text.Html
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.psm_pocketschool.databinding.ActivityMain3Binding
import java.io.File
import java.io.IOException


class MainActivity3 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityMain3Binding
    var resultLauncher: ActivityResultLauncher<Intent>? = null
    var uri:Uri?=null
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

        binding.txtAdjPdfs.setOnClickListener(this)


        //setContentView(R.layout.activity_main3)
    }

    override fun onClick(v: View?) {
        val itemId=v!!.id
        when(itemId){
            R.id.txtAdjPdfs->{
                selectPdf()
            }
        }
    }

    // Intent for navigating to the files
    private fun selectPdf() {
        val pdfIntent = Intent(Intent.ACTION_GET_CONTENT)
        pdfIntent.type = "application/pdf"
        pdfIntent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(pdfIntent, 0)

        //val intent=Intent()
        //intent.setType("pdf/*")
        //intent.setAction(Intent.ACTION_GET_CONTENT)
        //startActivityForResult(Intent.createChooser(intent, "Select PDF"), 0)

        /*val pdfIntent = Intent(Intent.ACTION_GET_CONTENT)
        pdfIntent.type = "application/pdf"
        pdfIntent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(pdfIntent, 0)*/
    }

    private fun getBase64ForUriAndPossiblyCrash(uri: Uri): String? {

        try {
            val bytes = contentResolver.openInputStream(uri)?.readBytes()
            return Base64.encodeToString(bytes, Base64.DEFAULT)
        } catch (error: IOException) {
            error.printStackTrace() // This exception always occurs
        }
        return null
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== RESULT_OK){
            var pdfName: String? = null
            val uriString: String = uri.toString()
            uri=data!!.data
            Log.d("Pdf", uri.toString())
            if (uriString.startsWith("content://")) {
                var myCursor: Cursor? = null
                try {
                    myCursor = applicationContext!!.contentResolver.query(uri!!, null, null, null, null)
                    if (myCursor != null && myCursor.moveToFirst()){
                        pdfName = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                        Log.d("Pdf", pdfName)
                    }
                }
                finally {
                    myCursor?.close()
                }

                val base64pdf: String? = getBase64ForUriAndPossiblyCrash(uri!!)
                Log.d("Pdf", base64pdf.toString())
                //convertToBase64()

            }
        }

        // For loading PDF
        /*
        when (requestCode) {
            0 -> if (resultCode == RESULT_OK) {

                var pdfUri = data?.data!!
                val uri: Uri = data?.data!!
                val uriString: String = uri.toString()
                var pdfName: String? = null
                if (uriString.startsWith("content://")) {
                    var myCursor: Cursor? = null
                    try {
                        // Setting the PDF to the TextView
                        myCursor = applicationContext!!.contentResolver.query(uri, null, null, null, null)
                        if (myCursor != null && myCursor.moveToFirst()) {
                            pdfName = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                            //pdfTextView.text = pdfName

                            Log.d("Pdf", pdfName)
                            Log.d("Pdf", pdfUri.toString())
                            var filePdf=File(pdfUri.toString())
                            val base64pdf=convertToBase64(filePdf)
                            Log.d("Pdf", base64pdf)
                            //convertToBase64()
                        }
                    } finally {
                        myCursor?.close()
                    }
                }
            }
        }

         */
    }
}