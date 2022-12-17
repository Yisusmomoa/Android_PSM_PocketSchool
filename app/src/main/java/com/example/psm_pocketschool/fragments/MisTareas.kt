package com.example.psm_pocketschool.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.psm_pocketschool.Adapters.AdapterMisTareasFragment
import com.example.psm_pocketschool.Controller.GetHomeworksTeacher.GetHomeworksTeacherController
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Session.UserApplication.Companion.prefs
import com.example.psm_pocketschool.View.IGetHomeworksTeacherView
import com.example.psm_pocketschool.databinding.FragmentMisTareasBinding
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MisTareas.newInstance] factory method to
 * create an instance of this fragment.
 */
class MisTareas : Fragment() , IGetHomeworksTeacherView, View.OnLongClickListener{
    private var _binding: FragmentMisTareasBinding?=null
    private val binding get()=_binding!!


    private lateinit var adapter: AdapterMisTareasFragment
    private lateinit var recyclerView: RecyclerView
    private lateinit var tempArrayList:ArrayList<Tarea>
    private lateinit var listHomeowrks:ArrayList<Tarea>
    //private lateinit var newsArrayList:ArrayList<News>
    //private var getHomeworksController:GetHomeworksController?=null
    private var getHomeworksTeacherController:GetHomeworksTeacherController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getHomeworksTeacherController= GetHomeworksTeacherController(this)
        getHomeworksTeacherController!!.onGetHomeworks(prefs.getUid().toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentMisTareasBinding.inflate(inflater, container, false)
        //recyclerView=binding.rvMisTareas
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                tempArrayList.clear()
                val searchText= newText?.toLowerCase(Locale.getDefault())
                if (searchText!!.isNotEmpty()){
                    listHomeowrks.forEach {
                        if (it.title.toLowerCase(Locale.getDefault()).contains(searchText!!)){
                            tempArrayList.add(it)
                        }
                    }
                    binding.rvMisTareas.adapter!!.notifyDataSetChanged()
                }
                else{
                    tempArrayList.clear()
                    tempArrayList.addAll(listHomeowrks)
                    binding.rvMisTareas.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_mis_tareas, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //dataInitialize()

    }


    override fun onSuccessHomeworks(homeworks: ArrayList<Tarea>) {
        tempArrayList=homeworks
        listHomeowrks=homeworks
        initRV(tempArrayList)
    }

    fun initRV(arrayListHomeworks:ArrayList<Tarea>){
        requireActivity().runOnUiThread {
            run {
                val layoutManager= LinearLayoutManager(context)
                //recyclerView=view.findViewById(R.id.rvMisTareas)
                recyclerView=binding.rvMisTareas
                recyclerView.layoutManager=layoutManager
                recyclerView.setHasFixedSize(true)
                adapter= AdapterMisTareasFragment(requireContext(), arrayListHomeworks)
                recyclerView.adapter=adapter
            }
        }
    }

    override fun onLongClick(v: View?): Boolean {
        TODO("Not yet implemented")
    }
}