package com.example.psm_pocketschool.Adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.psm_pocketschool.Model.Pdf.PdfClass
import com.example.psm_pocketschool.R

class AdapterPdf (private val context: Activity, private val arrayList:ArrayList<PdfClass>)
    :BaseAdapter() {
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater:LayoutInflater=LayoutInflater.from(context)
        val view:View=inflater.inflate(R.layout.list_pdf,parent, false)

        val txtNamePdf:TextView=view.findViewById(R.id.txtNamePdf)
        val btnRemovePDF:ImageButton=view.findViewById(R.id.btnRemovePDF)

        btnRemovePDF.setOnClickListener {
            Log.d("listPdfs", "remove: $pdfList")
            removeItem(position)
            Log.d("listPdfs", "remove: $pdfList")
            notifyDataSetChanged()
            mOnItemClickListener?.onItemClick(position)
        }

        txtNamePdf.text=pdfList[position].pdfName

        return view
    }

    fun removeItem(position: Int){
        val selectedPdf= pdfList[position]
        pdfList.remove(selectedPdf)
    }

    //:ArrayAdapter<PdfClass>(context,R.layout.list_pdf,arrayList)
}