package com.example.psm_pocketschool

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Base64
import android.view.View
import com.example.psm_pocketschool.Controller.UpdateHomework.UpdateHomeworkController
import com.example.psm_pocketschool.Model.Pdf.PdfClass
import com.example.psm_pocketschool.View.IUpdateHomeworkView
import com.example.psm_pocketschool.databinding.ActivityMain2Binding
import com.example.psm_pocketschool.databinding.ActivityUpdatePdfHomeworkBinding
import java.io.IOException

class UpdatePdfHomework : AppCompatActivity(), View.OnClickListener, IUpdateHomeworkView {
    private lateinit var binding:ActivityUpdatePdfHomeworkBinding
    private lateinit var updateHomeworkController: UpdateHomeworkController
    var uri:Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdatePdfHomeworkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateHomeworkController= UpdateHomeworkController(this)
        //setContentView(R.layout.activity_update_pdf_homework)
    }

    override fun onClick(v: View?) {
        val itemId=v!!.id
        when(itemId){
            R.id.txtAdjPdfs->{
                selectPdf()
            }
        }
    }
    private fun selectPdf() {
        val pdfIntent = Intent(Intent.ACTION_GET_CONTENT)
        pdfIntent.type = "application/pdf"
        pdfIntent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(pdfIntent, 0)
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
        if (resultCode== RESULT_OK && requestCode==0){
            var pdfName: String? = null
            uri=data!!.data
            val uriString: String = uri.toString()

            if (uriString.startsWith("content://")) {
                var myCursor: Cursor? = null
                try {
                    myCursor = applicationContext!!.contentResolver.query(uri!!, null, null, null, null)
                    if (myCursor != null && myCursor.moveToFirst()){
                        pdfName = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                        //Log.d("Pdf", pdfName)
                    }
                }
                finally {
                    myCursor?.close()
                }

                val base64pdfString: String? = getBase64ForUriAndPossiblyCrash(uri!!)
                //Log.d("Pdf", base64pdfString.toString())
                val pdfClass= PdfClass(pdfName!!, base64pdfString!!)

                //convertToBase64()
                //addPdfList(pdfClass)

            }
        }

    }

    override fun OnUpdateSuccess(message: String?) {
        TODO("Not yet implemented")
    }

    override fun OnAddPdfSuccess(message: String?) {
        TODO("Not yet implemented")
    }

}