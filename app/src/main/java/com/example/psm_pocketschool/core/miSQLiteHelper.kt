package com.example.psm_pocketschool.core

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.Tarea.Tarea
import java.lang.Exception

class miSQLiteHelper(var context: Context):SQLiteOpenHelper(context, SetDB.DB_NAME, null, SetDB.DB_VERSION) {
    ///creaos una orden de create table
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val createTareaTable:String="CREATE TABLE "+ SetDB.tblTarea.TABLE_NAME+"("+
                    SetDB.tblTarea.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    SetDB.tblTarea.COL_TITLE+ " VARCHAR(50), " +
                    SetDB.tblTarea.COL_DESCR+ " VARCHAR(256), " +
                    SetDB.tblTarea.COL_UID+ " VARCHAR(50), " +
                    SetDB.tblTarea.COL_Grupo_ID + " VARCHAR(50)) "
            db?.execSQL(createTareaTable)

            val createGrupoTable:String="CREATE TABLE "+SetDB.tblGrupo.TABLE_NAME+"("+
                    SetDB.tblGrupo.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    SetDB.tblGrupo.COL_NAME_GROUP+ " VARCHAR(50)," +
                    SetDB.tblGrupo.COL_UID_Group+ " VARCHAR(50), "+
                    SetDB.tblGrupo.COL_UID_Grupo_TareaP+ " VARCHAR(50), "+
                    SetDB.tblGrupo.COL_ID_TAREAFK+" INTEGER)"
            db?.execSQL(createGrupoTable)

            val createPdfTable:String="CREATE TABLE "+SetDB.tblListPdfs.TABLE_NAME+"("+
                    SetDB.tblListPdfs.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    SetDB.tblListPdfs.COL_PDF_Str+ " VARCHAR(10), "+
                    SetDB.tblListPdfs.COL_UID_Tarea+ " VARCHAR(50), "+
                    SetDB.tblListPdfs.COL_ID_PDF_TareaFK+" INTEGER, "+
                    SetDB.tblListPdfs.PDF_File+ " BLOB)"
            db?.execSQL(createPdfTable)
            Log.e("ENTRO","CREO TABLAS")
        }
        catch (e: Exception){
            Log.e("Execption", e.toString())
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado="DROP TABLE IF EXISTS ${SetDB.tblTarea.TABLE_NAME}"
        db!!.execSQL(ordenBorrado)
        val dropGroup="DROP TABLE IF EXISTS ${SetDB.tblGrupo.TABLE_NAME}"
        db!!.execSQL(dropGroup)
        val dropPdf="DROP TABLE IF EXISTS ${SetDB.tblListPdfs.TABLE_NAME}"
        db!!.execSQL(dropPdf)
        Log.e("ENTRO","Update TABLAS")
    }

    public fun insertTarea(tarea: Tarea):Boolean{
        //Log.d("ENTRO", "Inserta tablas")
        val dataBase:SQLiteDatabase = this.writableDatabase
        val values: ContentValues = ContentValues()
        var boolResult:Boolean =  true

        //Insert en la tabla tarea
        values.put(SetDB.tblTarea.COL_TITLE, tarea.title)
        values.put(SetDB.tblTarea.COL_DESCR, tarea.description)
        values.put(SetDB.tblTarea.COL_UID, tarea.uid)
        values.put(SetDB.tblTarea.COL_Grupo_ID, tarea.grupo)
        //Insert en la tabla tarea

        try {
            //Insert en la tabla tarea
            val result=dataBase.insert(SetDB.tblTarea.TABLE_NAME, null, values)
            //Insert en la tabla tarea

            //obtener el ultimo id insertado
            val cursor=dataBase.rawQuery("SELECT last_insert_rowid()", null)
            var id:Int=0
            if (cursor.moveToLast()){
                id=cursor.getInt(0)
            }
            //obtener el ultimo id insertado

            if (result==(0).toLong()){
                Toast.makeText(this.context, "Failed", Toast.LENGTH_SHORT).show()
            }
            else{
                //Insert en la tabla grupo
                values.clear()
                values.put(SetDB.tblGrupo.COL_UID_Group, tarea.grupoStruct?.uid)
                values.put(SetDB.tblGrupo.COL_NAME_GROUP, tarea.grupoStruct?.nameGroup)
                values.put(SetDB.tblGrupo.COL_UID_Grupo_TareaP, tarea.uid)
                values.put(SetDB.tblGrupo.COL_ID_TAREAFK, id)
                val resultGrupo=dataBase.insert(SetDB.tblGrupo.TABLE_NAME, null, values)
                //Insert en la tabla grupo

                //Insert en la tabla pdfs
                values.clear()
                tarea.pdfsTarea?.forEach {
                    values.put(SetDB.tblListPdfs.COL_UID_Tarea, tarea.uid)
                    values.put(SetDB.tblListPdfs.COL_PDF_Str, it)
                    values.put(SetDB.tblListPdfs.COL_ID_PDF_TareaFK, id)
                    values.put(SetDB.tblListPdfs.COL_UID_Tarea, tarea.uid)
                }
                //Insert en la tabla pdfs

                //Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                Log.d("ENTRO", "Inserta tablas 2")
            }
        }
        catch (e: Exception){
            Log.e("Execption", e.toString())
            boolResult =  false
        }
        dataBase.close()
        return boolResult
    }

    @SuppressLint("Range")
    public fun getTareas():MutableList<Tarea>{
        val List:MutableList<Tarea> = ArrayList()

        val dataBase:SQLiteDatabase = this.writableDatabase
        val columns:Array<String> =  arrayOf(SetDB.tblTarea.COL_ID,
                                            SetDB.tblTarea.COL_TITLE,
                                            SetDB.tblTarea.COL_DESCR,
                                            SetDB.tblTarea.COL_UID,
                                            SetDB.tblTarea.COL_Grupo_ID)
        val data =  dataBase.query(SetDB.tblTarea.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            SetDB.tblTarea.COL_ID + " ASC")
        Log.d("ENTRO", "Recupera tablas")
        //obtener las tareas y los grupos
        //TODO: Falta recuperar los pdf
        if (data.moveToFirst()){
            do {
                val tarea:Tarea=Tarea()
                var idTareaAux=data.getString(data.getColumnIndex(SetDB.tblTarea.COL_ID)).toInt()
                tarea.uid=data.getString(data.getColumnIndex(SetDB.tblTarea.COL_UID)).toString()
                tarea.title=data.getString(data.getColumnIndex(SetDB.tblTarea.COL_TITLE)).toString()
                tarea.description=data.getString(data.getColumnIndex(SetDB.tblTarea.COL_DESCR)).toString()
                tarea.grupo=data.getString(data.getColumnIndex(SetDB.tblTarea.COL_Grupo_ID)).toString()
                tarea.grupoStruct=getGroup(idTareaAux)

                List.add(tarea)

            }while (data.moveToNext())
        }
        return List
    }

    @SuppressLint("Range")
    public fun getGroup(idTarea:Int): Grupo? {
        var grupo:Grupo?=null
        val dataBase:SQLiteDatabase = this.writableDatabase
        val columns:Array<String> =  arrayOf(SetDB.tblGrupo.COL_ID,
        SetDB.tblGrupo.COL_UID_Group,
        SetDB.tblGrupo.COL_NAME_GROUP)

        //val where:String =  SetDB.tblAlbum.COL_ID + "= ${intID.toString()}"
        val where:String=SetDB.tblGrupo.COL_ID_TAREAFK+"= ${idTarea.toString()}"
        val data =  dataBase.query(SetDB.tblGrupo.TABLE_NAME,
            columns,
            where,
            null,
            null,
            null,
            SetDB.tblGrupo.COL_ID + " ASC")

        if (data.moveToFirst()){
            grupo=Grupo()
            grupo.uid=data.getString(data.getColumnIndex(SetDB.tblGrupo.COL_UID_Group)).toString()
            grupo.nameGroup=data.getString(data.getColumnIndex(SetDB.tblGrupo.COL_NAME_GROUP)).toString()
        }
        data.close()
        return grupo
    }

    @SuppressLint("Range")
    public fun getPdf(idTarea:Int):String?{
        var pdfStr:String=""
        val dataBase:SQLiteDatabase = this.writableDatabase
        val columns:Array<String> =  arrayOf(
            SetDB.tblListPdfs.COL_ID,
            SetDB.tblListPdfs.COL_PDF_Str,
            SetDB.tblListPdfs.COL_ID_PDF_TareaFK)
        val where:String=SetDB.tblListPdfs.COL_ID_PDF_TareaFK+"=${idTarea.toString()}"

        val data=dataBase.query(SetDB.tblListPdfs.TABLE_NAME,
        columns,
        where,
        null,
        null,
        null,
        SetDB.tblListPdfs.COL_ID + " ASC")

        if (data.moveToFirst()){
            pdfStr=data.getString(data.getColumnIndex(SetDB.tblListPdfs.COL_PDF_Str)).toString()
        }
        data.close()
        return pdfStr
    }

}