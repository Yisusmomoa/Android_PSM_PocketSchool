package com.example.psm_pocketschool.Adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.psm_pocketschool.Controller.DeleteHomework.DeleteHomeworkController
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.News
import com.example.psm_pocketschool.R
import com.example.psm_pocketschool.UpdateInfoHomework
import com.example.psm_pocketschool.UpdatePdfHomework
import com.example.psm_pocketschool.fragments.DetalleTarea
import com.example.psm_pocketschool.fragments.MisTareas
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdapterMisTareasFragment(private val mContext: Context, private val List: ArrayList<Tarea>):
    RecyclerView.Adapter<AdapterMisTareasFragment.MyViewHolder>() {
    var deleteHomeworkController=DeleteHomeworkController()
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val txtNombreGrupo:TextView=itemView.findViewById(R.id.txtNombreGrupo)
        val txtTituloTarea:TextView=itemView.findViewById(R.id.txtTituloTarea)
        val txtDescrTarea:TextView=itemView.findViewById(R.id.txtDescrTarea)
        val txtFechaTarea:TextView=itemView.findViewById(R.id.txtFechaTarea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.item_grupo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=List[position]
        holder.txtNombreGrupo.text=currentItem.grupoStruct?.nameGroup
        holder.txtTituloTarea.text=currentItem.title
        holder.txtDescrTarea.text=currentItem.description
        holder.itemView.setOnLongClickListener {

            val options= arrayOf<CharSequence>(
                "Editar Información",
                "Añadir pdf",
                "Eliminar",
                "Cancelar"
            )
            var builder: AlertDialog.Builder= AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("¿Qué quieres hacer?")
            builder.setItems(options, DialogInterface.OnClickListener { dialog, which ->
                when(which){
                    0->{
                        //Mandar info desde aquí
                        val intent=Intent(mContext, UpdateInfoHomework::class.java)

                        intent.putExtra("uid", currentItem.uid)
                        intent.putExtra("title", currentItem.title)
                        intent.putExtra("descr", currentItem.description)
                        intent.putExtra("fechaFin", currentItem.dateFin)
                        mContext.startActivity(intent)
                    }
                    1->{
                        val intent=Intent(mContext, UpdatePdfHomework::class.java)
                        intent.putExtra("pdfs", currentItem.pdfsTarea as ArrayList)
                        mContext.startActivity(intent)
                    }
                    2->{
                        deleteHomeworkController.onDeleteHomework(currentItem.uid)
                            Toast.makeText(mContext, "Tarea, eliminada con exito", Toast.LENGTH_SHORT).show()
                            val fragmentMisTareasBinding=MisTareas()
                            val transaction =it.context as AppCompatActivity
                            transaction.supportFragmentManager?.beginTransaction()
                                .replace(R.id.frame_layout, fragmentMisTareasBinding)
                                .disallowAddToBackStack()
                                .commit()
                    }
                    3->{
                        dialog.dismiss()
                    }
                }
            })
            builder.show()
            return@setOnLongClickListener true
        }

        holder.itemView.setOnClickListener {
            val bundle= Bundle()
            val gson = Gson()
            val myObjectString = gson.toJson(currentItem)
            bundle.putString("tarea", myObjectString)
            val fragmentDetalleTareaBinding= DetalleTarea()
            fragmentDetalleTareaBinding.arguments=bundle

            val transaction =it.context as AppCompatActivity
            transaction.supportFragmentManager?.beginTransaction()
                .replace(R.id.frame_layout, fragmentDetalleTareaBinding)
                .disallowAddToBackStack()
                .commit()
        }

    }

    override fun getItemCount(): Int {
        return List.size
    }

}