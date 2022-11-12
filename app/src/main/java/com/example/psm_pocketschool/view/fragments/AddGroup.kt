package com.example.psm_pocketschool.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.psm_pocketschool.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddGroup.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddGroup : Fragment(), AdapterView.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView:ListView=view.findViewById(R.id.listStudentsAdd)
        val listOfItem:ArrayList<String> = setMultipleListView()
        //val arrayAdapter:ArrayAdapter<String> =ArrayAdapter(view.context, android.R.layout.simple_list_item_multiple_choice, listOfItem)
        val arrayAdapter:ArrayAdapter<String> =ArrayAdapter(view.context, android.R.layout.simple_list_item_multiple_choice, listOfItem)
        listView.choiceMode=ListView.CHOICE_MODE_MULTIPLE
        listView.adapter=arrayAdapter
        listView.onItemClickListener=this
    }

    private fun setMultipleListView():ArrayList<String>{
        val arrayList:ArrayList<String> = ArrayList()
        for(i in 1..30){
            arrayList.add("Application List $i")
        }

        return arrayList
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddGroup.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddGroup().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val options:String = parent?.getItemAtPosition(position) as String
        if (view != null) {
            Toast.makeText(view.context, "Clicked by: $options", Toast.LENGTH_SHORT).show()
        }
    }
}