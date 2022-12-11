package com.example.psm_pocketschool.Adapters

import android.app.Activity
import android.util.Base64.decode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.psm_pocketschool.Model.Pdf.PdfClass
import com.example.psm_pocketschool.R
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Byte.decode
import java.util.*

class AdapterPdfDownload(private val context: Activity, private val arrayList:ArrayList<PdfClass>):
    BaseAdapter() {
    val pdfList= mutableListOf<PdfClass>()
    var mOnItemClickListener: OnItemClickListener? = null
    init {
        pdfList.addAll(arrayList)
    }
    override fun getCount(): Int {
        return pdfList.size
    }

    override fun getItem(position: Int): Any {
        return pdfList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val validBase64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"  // All valid base64 characters
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view:View=inflater.inflate(R.layout.list_download_pdf,parent, false)
        val txtNamePdf: TextView =view.findViewById(R.id.txtNamePdf)
        val btnDownloadPDF: ImageButton =view.findViewById(R.id.btnDownloadPDF)
        txtNamePdf.text=pdfList[position].pdfName
        btnDownloadPDF.setOnClickListener {

            val cleanString = pdfList[position].pdfBase64String.filter { validBase64Chars.contains(it) }
            val decoded = Base64.getDecoder().decode(cleanString)

            /*var bos: BufferedOutputStream? = null
            var fos: FileOutputStream? = null

            val file = File("/path/to/${pdfList[position].pdfName}.pdf")  // The path where you want to save the file
            // Check if the file exists and create it if it does not

            fos = FileOutputStream(file)
            bos = BufferedOutputStream(fos)
            //val bfile: ByteArray = Base64.decode(cleanString, Base64.DEFAULT)
            val bfile: ByteArray = android.util.Base64.decode(cleanString, android.util.Base64.DEFAULT)
            bos.write(bfile)

            /*BufferedOutputStream(FileOutputStream(file)).use {
                it.write(decoded)
            }

             */
             */

        }
        return view
    }



}