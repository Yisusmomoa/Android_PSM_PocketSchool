package com.example.psm_pocketschool

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.psm_pocketschool.Model.Pdf.PdfClass
import com.example.psm_pocketschool.databinding.ActivityMain3Binding
import java.io.IOException


class MainActivity3 : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemClickListener {
    //TODO: Mostrar pdfs en un listview, remove pdf/item de listview
    private lateinit var binding:ActivityMain3Binding
    var resultLauncher: ActivityResultLauncher<Intent>? = null
    var uri:Uri?=null
    private var listPdfs:ArrayList<PdfClass> = ArrayList()
    private var listPdfsName:ArrayList<String> = ArrayList()

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
                val pdfClass=PdfClass(pdfName!!, base64pdfString!!)
                //convertToBase64()
                addPdfList(pdfClass)

            }
        }

    }

    private fun addPdfList(pdfAux: PdfClass){
        listPdfs.add(pdfAux)
        listPdfsName.add(pdfAux.pdfName)
        listPdfsView()
        Log.d("listPdfsName", listPdfsName.toString())
        Log.d("listPdfs", listPdfs.toString())

    }

    private fun listPdfsView(){
        runOnUiThread {
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listPdfsName)
            binding.listPDFSPublic.choiceMode= ListView.CHOICE_MODE_MULTIPLE
            binding.listPDFSPublic.adapter=arrayAdapter
            binding.listPDFSPublic.onItemClickListener=this
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

}