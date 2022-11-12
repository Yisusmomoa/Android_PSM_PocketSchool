package com.example.psm_pocketschool.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.psm_pocketschool.view.News
import com.example.psm_pocketschool.R
import com.example.psm_pocketschool.view.fragments.DetalleTarea
import java.time.LocalDate
import kotlin.collections.ArrayList

class AdapterHomeFragment (private val List: ArrayList<News>):
    RecyclerView.Adapter<AdapterHomeFragment.MyViewHolder>(){
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val txtNombreGrupo:TextView=itemView.findViewById(R.id.txtNombreGrupo)
        val txtTituloTarea:TextView=itemView.findViewById(R.id.txtTituloTarea)
        val txtDescrTarea:TextView=itemView.findViewById(R.id.txtDescrTarea)
        val txtFechaTarea:TextView=itemView.findViewById(R.id.txtFechaTarea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_grupo, parent, false)
        itemView.setOnClickListener {
            val transaction =it.context as AppCompatActivity
            transaction.supportFragmentManager?.beginTransaction()
                .replace(R.id.frame_layout, DetalleTarea())
                .disallowAddToBackStack()
                .commit()
        }
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=List[position]
        holder.txtNombreGrupo.text=currentItem.title
        holder.txtTituloTarea.text=currentItem.title
        holder.txtDescrTarea.text=currentItem.desc
        holder.txtFechaTarea.text= LocalDate.now().toString()
    }

    override fun getItemCount(): Int {
        return List.size
    }
}