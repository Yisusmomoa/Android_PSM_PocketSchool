package com.example.psm_pocketschool.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.psm_pocketschool.Adapters.AdapterGroupsFragment
import com.example.psm_pocketschool.view.News
import com.example.psm_pocketschool.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Groups.newInstance] factory method to
 * create an instance of this fragment.
 */
class Groups : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: AdapterGroupsFragment
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsArrayList:ArrayList<News>

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
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addGroup: View = view.findViewById(R.id.addGroup)
        addGroup.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, AddGroup())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }
        dataInitialize()
        val layoutManager=LinearLayoutManager(context)
        recyclerView=view.findViewById(R.id.rvGrupos)
        recyclerView.layoutManager=layoutManager
        recyclerView.setHasFixedSize(true)
        adapter=AdapterGroupsFragment(newsArrayList)
        recyclerView.adapter=adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Groups.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Groups().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun dataInitialize(){
        newsArrayList= arrayListOf<News>()
        for (i in 1..10){
            val new: News = News("Nombre grupo${i}", "Desc${i+1}", true)
            newsArrayList.add(new)

        }
    }
}