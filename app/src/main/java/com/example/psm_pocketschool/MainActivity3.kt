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
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.psm_pocketschool.Adapters.AdapterPdf
import com.example.psm_pocketschool.Adapters.OnItemClickListener
import com.example.psm_pocketschool.Controller.AddHomework.AddHomeworkController
import com.example.psm_pocketschool.Model.Pdf.PdfClass
import com.example.psm_pocketschool.Model.Tarea.AddTarea
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.View.IAddHomeworkView
import com.example.psm_pocketschool.databinding.ActivityMain3Binding
import com.example.psm_pocketschool.fragments.Home
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.Serializable


class MainActivity3 : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemClickListener, IAddHomeworkView {
    //TODO: Mostrar pdfs en un listview, remove pdf/item de listview
    private lateinit var binding:ActivityMain3Binding
    var resultLauncher: ActivityResultLauncher<Intent>? = null
    var uri:Uri?=null
    private var listPdfs:ArrayList<PdfClass> = ArrayList()
    private var listPdfsName:ArrayList<String> = ArrayList()
    private var listPdfs64:ArrayList<String> = ArrayList()
    private var listAuxTareas:ArrayList<AddTarea> = ArrayList()
    lateinit var pdfAdapterPdf: AdapterPdf
    var titleHomework:String=""
    var descHomework:String=""
    private var addHomeworkController:AddHomeworkController?=null

    lateinit var listOfGrupos:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        titleHomework= intent.getStringExtra("titleHomework")!!
        descHomework= intent.getStringExtra("descHomework")!!
        listOfGrupos= intent.getSerializableExtra("listOfGrupos") as ArrayList<String>

        addHomeworkController=AddHomeworkController(this)

        binding.txtAdjPdfs.setOnClickListener(this)
        binding.btnPublicHomework.setOnClickListener(this)

        //setContentView(R.layout.activity_main3)
    }

    override fun onClick(v: View?) {
        val itemId=v!!.id
        when(itemId){
            R.id.txtAdjPdfs->{
                selectPdf()
            }
            R.id.btnPublicHomework->{
                listOfGrupos.forEach { gpo->
                    val auxTarea=AddTarea(titleHomework, descHomework, listPdfs64, gpo)
                    listAuxTareas.add(auxTarea)
                }
                //Log.d("Tarea", listAuxTareas.toString())
                addHomeworkController?.addHomework(listAuxTareas)

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
        listPdfs64.add(pdfAux.pdfBase64String)
        listPdfsView()
        //Log.d("listPdfsName", listPdfsName.toString())
        //Log.d("listPdfs", listPdfs.toString())
    }

    private fun listPdfsView(){
        runOnUiThread {
            //val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listPdfsName)
            pdfAdapterPdf= AdapterPdf(this, listPdfs)
            binding.listPDFSPublic.choiceMode= ListView.CHOICE_MODE_MULTIPLE
            //binding.listPDFSPublic.adapter=arrayAdapter
            binding.listPDFSPublic.adapter=pdfAdapterPdf
            pdfAdapterPdf.notifyDataSetChanged()

            pdfAdapterPdf.mOnItemClickListener= object : OnItemClickListener {
                override fun onItemClick(index: Int) {
                    listPdfs.removeAt(index)
                    listPdfsName.removeAt(index)
                    listPdfs64.removeAt(index)
                }
            }

            binding.listPDFSPublic.onItemClickListener=this

        }
    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun OnAddHomeworkSuccess(message: String?) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
        Thread.sleep(2000)
            val intent=Intent(this, com.example.psm_pocketschool.Home::class.java)
            startActivity(intent)

    }

    override fun OnAddHomeworkError(message: String?) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
        val intent=Intent(this, com.example.psm_pocketschool.Home::class.java)
        startActivity(intent)
    }

}