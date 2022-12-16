package com.example.psm_pocketschool.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.psm_pocketschool.Adapters.AdapterMisTareasFragment
import com.example.psm_pocketschool.Controller.GetHomeworksTeacher.GetHomeworksTeacherController
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Session.UserApplication.Companion.prefs
import com.example.psm_pocketschool.View.IGetHomeworksTeacherView
import com.example.psm_pocketschool.databinding.FragmentMisTareasBinding

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
        initRV(homeworks)
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