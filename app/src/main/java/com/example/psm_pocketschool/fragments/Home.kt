package com.example.psm_pocketschool.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.psm_pocketschool.Adapters.AdapterHomeFragment
import com.example.psm_pocketschool.Controller.GetHomeworks.GetHomeworksController
import com.example.psm_pocketschool.MainActivity2
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.News
import com.example.psm_pocketschool.R
import com.example.psm_pocketschool.Session.UserApplication.Companion.dbHelper
import com.example.psm_pocketschool.Session.UserApplication.Companion.prefs
import com.example.psm_pocketschool.View.IGetHomeworksView
import com.example.psm_pocketschool.core.isConnected
import com.example.psm_pocketschool.databinding.FragmentAddGroupBinding
import com.example.psm_pocketschool.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() , IGetHomeworksView{

    private var _binding: FragmentHomeBinding?=null
    private val binding get()=_binding!!

    private lateinit var adapter:AdapterHomeFragment
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsArrayList:ArrayList<News>
    private var getHomeworksController:GetHomeworksController?=null
    //private var arrayListHomeworks:ArrayList<Tarea> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //verifica si esta conectado a través del objeto isConnected
        if (isConnected.isConnected(requireContext())) {
            getHomeworksController = GetHomeworksController(this)
            getHomeworksController!!.onGetHomeworks(prefs.getUid()!!)

        }
        else{
            //sino, uso sqlite
            GlobalScope.launch {
                offlineTareas()

            }
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        _binding=FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addHomework.setOnClickListener { view ->
            val intent =Intent(view.context, MainActivity2::class.java)
            startActivity(intent)
            /*val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, PublicarTarea())
            transaction?.disallowAddToBackStack()
            transaction?.commit()*/
            /*Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()*/
        }
    }


    private fun dataInitialize(){
        newsArrayList= arrayListOf<News>()
        for (i in 1..10){
            val new:News=News("titulo${i}", "Desc${i+1}", true)
            newsArrayList.add(new)

        }
    }

    private fun offlineTareas(){
        val listTareas=dbHelper.getTareas() as ArrayList<Tarea>
        initRV(listTareas)
    }

    fun initRV(arrayListHomeworks:ArrayList<Tarea>){
        requireActivity().runOnUiThread {
            val layoutManager=LinearLayoutManager(context)
            //recyclerView= view!!.findViewById(R.id.recyclerView)
            recyclerView= binding.recyclerView
            recyclerView.layoutManager=layoutManager
            recyclerView.setHasFixedSize(true)
            adapter= AdapterHomeFragment(arrayListHomeworks)
            recyclerView.adapter=adapter
        }
    }

    override fun onSuccessHomeworks(homeworks: ArrayList<Tarea>) {
        //arrayListHomeworks=homeworks
        initRV(homeworks)
    }

}