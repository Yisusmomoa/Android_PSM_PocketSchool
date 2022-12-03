package com.example.psm_pocketschool.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.News
import com.example.psm_pocketschool.R
import com.example.psm_pocketschool.fragments.AddGroup
import com.example.psm_pocketschool.fragments.SubGroup

class AdapterGroupsFragment(private val List: List<Grupo>):
    RecyclerView.Adapter<AdapterGroupsFragment.MyViewHolder>() {
     class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val txtNombreGrupo:TextView=itemView.findViewById(R.id.txtNombreGrupo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_groups, parent, false)
        itemView.setOnClickListener {
            val transaction =it.context as AppCompatActivity
            transaction.supportFragmentManager?.beginTransaction()
                .replace(R.id.frame_layout, SubGroup())
                .disallowAddToBackStack()
                .commit()
        }
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=List[position]
        holder.txtNombreGrupo.text=currentItem.nameGroup
        Log.d("NameGroup", currentItem.nameGroup)

    }

    override fun getItemCount(): Int {
        return List.size
    }
}