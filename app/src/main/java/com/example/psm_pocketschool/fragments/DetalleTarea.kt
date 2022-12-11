package com.example.psm_pocketschool.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.psm_pocketschool.Adapters.AdapterPdf
import com.example.psm_pocketschool.Adapters.AdapterPdfDownload
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.Pdf.PdfClass
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.R
import com.example.psm_pocketschool.databinding.FragmentAddGroupBinding
import com.example.psm_pocketschool.databinding.FragmentDetalleTareaBinding
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetalleTarea.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetalleTarea : Fragment() {

    private var _binding:FragmentDetalleTareaBinding?=null
    private val binding get()=_binding!!
    private var tarea:Tarea?=null
    lateinit var pdfAdapterPdf: AdapterPdfDownload
    private var listPdfs:ArrayList<PdfClass> = ArrayList()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Recuperamos el argumento del fragmento y lo deserializamos a un objeto
        // usando Gson
        val gson = Gson()
        val myObjectString = arguments?.getString("tarea")
        //val myObject = gson.fromJson(myObjectString, Tarea::class.java)
        tarea = gson.fromJson(myObjectString, Tarea::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detalle_tarea, container, false)
        _binding=FragmentDetalleTareaBinding.inflate(inflater, container, false)
        binding.txtTituloTarea.text=tarea?.title
        binding.txtDescrTareaEntrega.text=tarea?.description

        tarea?.pdfsTarea?.forEach {
            var i=0
            val pdfAux=PdfClass("pdf${i}", it)
            listPdfs.add(pdfAux)
            i++
        }

        pdfAdapterPdf= AdapterPdfDownload(requireContext() as Activity, listPdfs)
        binding.listPDFS.choiceMode= ListView.CHOICE_MODE_MULTIPLE
        binding.listPDFS.adapter=pdfAdapterPdf

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetalleTarea.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetalleTarea().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}