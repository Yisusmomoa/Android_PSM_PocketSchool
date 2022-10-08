package com.example.psm_pocketschool.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.psm_pocketschool.News
import com.example.psm_pocketschool.R

class AdapterMisTareasFragment (private val List:Array<News>):
    RecyclerView.Adapter<AdapterMisTareasFragment.MyViewHolder>() {
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val txtNombreGrupo: TextView =itemView.findViewById(R.id.txtNombreGrupo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.item_grupo, parent, false)
        return AdapterMisTareasFragment.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=List[position]
        holder.txtNombreGrupo.text=currentItem.title
    }

    override fun getItemCount(): Int {
        return List.size
    }
}