package com.example.psm_pocketschool.core

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.psm_pocketschool.Model.Grupo.Grupo
import com.example.psm_pocketschool.Model.Tarea.AddTarea
import com.example.psm_pocketschool.Model.Tarea.Tarea
import com.example.psm_pocketschool.Session.UserApplication.Companion.prefs
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
                    //SetDB.tblGrupo.COL_UID_Grupo_TareaP+ " VARCHAR(50), "+
                    SetDB.tblGrupo.COL_ID_TAREAFK+" INTEGER)"
            db?.execSQL(createGrupoTable)

            val createPdfTable:String="CREATE TABLE "+SetDB.tblListPdfs.TABLE_NAME+"("+
                    SetDB.tblListPdfs.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    SetDB.tblListPdfs.COL_PDF_Str+ " VARCHAR(10), "+
                    SetDB.tblListPdfs.COL_UID_Tarea+ " VARCHAR(50), "+
                    SetDB.tblListPdfs.COL_ID_PDF_TareaFK+" INTEGER, "+
                    SetDB.tblListPdfs.PDF_File+ " BLOB)"
            db?.execSQL(createPdfTable)

            val createDraftTarea:String="CREATE TABLE "+SetDB.tbldraftTarea.TABLE_NAME+"("+
                    SetDB.tbldraftTarea.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    SetDB.tbldraftTarea.COL_TITLE_TAREA+" VARCHAR(255), "+
                    SetDB.tbldraftTarea.COL_DESCR_TAREA+ " VARCHAR(255), "+
                    SetDB.tbldraftTarea.COL_DATE_FIN+ " VARCHAR(255) )"
            db?.execSQL(createDraftTarea)

            val createDraftGrupo:String="CREATE TABLE "+SetDB.tbldraftGrupos.TABLE_NAME+"("+
                    SetDB.tbldraftGrupos.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    SetDB.tbldraftGrupos.COL_UID_GRUPO+" VARCHAR(255), "+
                    SetDB.tbldraftGrupos.COL_NAMEGRUPO_GRUPO+" VARCHAR(255) )"
            db?.execSQL(createDraftGrupo)

            val createDraftPdf:String="CREATE TABLE "+SetDB.tbldraftPdf.TABLE_NAME+"("+
                    SetDB.tbldraftPdf.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    SetDB.tbldraftPdf.COL_STR64+" VARCHAR(10), "+
                    SetDB.tbldraftPdf.COL_PDF_NAME+" VARCAR(255) )"
            db?.execSQL(createDraftPdf)

            val createCreateTareaGrupo:String="CREATE TABLE "+SetDB.tblGruposCreateTarea.TABLE_NAME+"("+
                    SetDB.tblGruposCreateTarea.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    SetDB.tblGruposCreateTarea.COL_UID_GRUPO+" VARCHAR(255), "+
                    SetDB.tblGruposCreateTarea.COL_NAMEGRUPO+" VARCHAR(255) )"
            db?.execSQL(createCreateTareaGrupo)

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

        val dropDraftTarea="DROP TABLE IF EXISTS ${SetDB.tbldraftTarea.TABLE_NAME}"
        db!!.execSQL(dropDraftTarea)
        val dropDraftGrupo="DROP TABLE IF EXISTS ${SetDB.tbldraftGrupos.TABLE_NAME}"
        db!!.execSQL(dropDraftGrupo)
        val dropDraftPdf="DROP TABLE IF EXISTS ${SetDB.tbldraftPdf.TABLE_NAME}"
        db!!.execSQL(dropDraftPdf)

        val dropCreateTareaGrupo="DROP TABLE IF EXISTS ${SetDB.tblGruposCreateTarea.TABLE_NAME}"
        db!!.execSQL(dropCreateTareaGrupo)

        Log.e("ENTRO","Update TABLAS")
    }

    //insertar tareas de la pantalla home, para consultar offline
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
                //values.put(SetDB.tblGrupo.COL_UID_Grupo_TareaP, tarea.uid)
                values.put(SetDB.tblGrupo.COL_ID_TAREAFK, id)
                val resultGrupo=dataBase.insert(SetDB.tblGrupo.TABLE_NAME, null, values)
                //Insert en la tabla grupo

                //Insert en la tabla pdfs
                values.clear()
                tarea.pdfsTarea?.forEach {
                    values.put(SetDB.tblListPdfs.COL_UID_Tarea, tarea.uid)
                    values.put(SetDB.tblListPdfs.COL_PDF_Str, it)
                    values.put(SetDB.tblListPdfs.COL_ID_PDF_TareaFK, id)
                    //values.put(SetDB.tblListPdfs.COL_UID_Tarea, tarea.uid)
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

    //insertar tarea draft
    fun insertDraftTarea(tarea:AddTarea, listGrupos:List<String>):Boolean{
        val dataBase:SQLiteDatabase = this.writableDatabase
        val values: ContentValues = ContentValues()
        var boolResult:Boolean =  true
        //insert en draftTarea
        values.put(SetDB.tbldraftTarea.COL_TITLE_TAREA, tarea.title)
        values.put(SetDB.tbldraftTarea.COL_DESCR_TAREA, tarea.description)
        //values.put(SetDB.tbldraftTarea.COL_DATE_FIN, tarea.dateFin)
        try {
            val result=dataBase.insert(SetDB.tbldraftTarea.TABLE_NAME, null, values)
            if (result!=(0).toLong()){
                //Insert en la tabla draftgrupo
                values.clear()
                listGrupos.forEach {
                    values.put(SetDB.tbldraftGrupos.COL_UID_GRUPO, it)
                    //values.put(SetDB.tbldraftGrupos.COL_NAMEGRUPO_GRUPO, it.nameGroup)

                    dataBase.insert(SetDB.tbldraftGrupos.TABLE_NAME, null, values)

                    values.clear()
                }

                tarea.pdfsTarea?.forEach {
                    values.put(SetDB.tbldraftPdf.COL_STR64, it)

                    dataBase.insert(SetDB.tbldraftPdf.TABLE_NAME, null, values)

                    values.clear()
                }
                //la bandera de si guardo un borrador para subirlo luegos
                prefs.saveDraft(true)
                boolResult=true
            }

        }
        catch (e: Exception){
            Log.e("Execption", e.toString())
            boolResult=false
        }
        dataBase.close()
        return boolResult
    }

    //Obtener la información de tarea draft
    @SuppressLint("Range")
    fun getDraftTarea(): AddTarea? {
        var tarea:AddTarea?=null
        val dataBase:SQLiteDatabase = this.writableDatabase
        val columns:Array<String> =  arrayOf(
            SetDB.tbldraftTarea.COL_ID,
            SetDB.tbldraftTarea.COL_TITLE_TAREA,
            SetDB.tbldraftTarea.COL_DESCR_TAREA,
            //SetDB.tbldraftTarea.COL_DATE_FIN
        )
        val data=dataBase.query(SetDB.tbldraftTarea.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            SetDB.tbldraftTarea.COL_ID + " ASC"
        )
        if (data.moveToFirst()){
            tarea= AddTarea()
            tarea.title=data.getString(data.getColumnIndex(SetDB.tbldraftTarea.COL_TITLE_TAREA)).toString()
            tarea.description=data.getString(data.getColumnIndex(SetDB.tbldraftTarea.COL_DESCR_TAREA)).toString()
            //tarea.dateFin=data.getString(data.getColumnIndex(SetDB.tbldraftTarea.COL_DATE_FIN)).toString()
        }
        data.close()
        return tarea
    }
    @SuppressLint("Range")
    fun getDraftGrupos():MutableList<Grupo>{
        val List:MutableList<Grupo> = ArrayList()
        val dataBase:SQLiteDatabase = this.writableDatabase
        val columns:Array<String> =  arrayOf(
            SetDB.tbldraftGrupos.COL_ID,
            SetDB.tbldraftGrupos.COL_UID_GRUPO
        )
        val data =  dataBase.query(SetDB.tbldraftGrupos.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            SetDB.tbldraftGrupos.COL_ID + " ASC")

        if (data.moveToFirst()){
            do {
                val grupo:Grupo= Grupo()
                grupo.uid=data.getString(data.getColumnIndex(SetDB.tbldraftGrupos.COL_UID_GRUPO)).toString()
                //grupo.nameGroup=data.getString(data.getColumnIndex(SetDB.tbldraftGrupos.COL_NAMEGRUPO_GRUPO)).toString()
                List.add(grupo)
            }while (data.moveToNext())
        }
        return List
    }
    @SuppressLint("Range")
    fun getDraftPdfs():MutableList<String>{
        val List:MutableList<String> = ArrayList()
        val dataBase:SQLiteDatabase = this.writableDatabase
        val columns:Array<String> =  arrayOf(
            SetDB.tbldraftPdf.COL_ID,
            SetDB.tbldraftPdf.COL_STR64
        )
        val data =  dataBase.query(SetDB.tbldraftPdf.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            SetDB.tbldraftPdf.COL_ID + " ASC")
        if (data.moveToFirst()){
            do {
                var str64:String=""
                str64=data.getString(data.getColumnIndex(SetDB.tbldraftPdf.COL_STR64)).toString()
                List.add(str64)
            }while (data.moveToNext())
        }
        return List
    }

    //convertir la información en un array de tareas para subirse a la bd
    fun getDraftInfo():ArrayList<AddTarea>{
        var tarea:AddTarea?=null
        val listpdfs:List<String>
        val listGrupos:List<Grupo>
        var listTareas:ArrayList<AddTarea> = ArrayList()
        var mutableListpdf:MutableList<String> = mutableListOf()

        //obtengo la lista de grupos, de pdfs y la info de la tarea
        listGrupos=getDraftGrupos()
        listpdfs=getDraftPdfs()
        tarea=getDraftTarea()

        //Lleno la lista de pdfs de la tarea
        listpdfs.forEach {
            mutableListpdf.add(it)
        }
        tarea?.pdfsTarea =mutableListpdf

        //voy creando una tarea por grupo
        listGrupos.forEach {
            var grupo:Grupo= Grupo()
            grupo.uid=it.uid
            grupo.nameGroup=it.nameGroup
            tarea?.grupo=grupo.uid
            //tarea?.grupoStruct =grupo
            listTareas.add(tarea!!)
        }

        return listTareas
    }

    //Obtener las tareas que se muestran en la pantalla de Home
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

    //cargar grupos en la pantalla de crear tarea
    @SuppressLint("Range")
    fun insertGroupsCreateTarea(listGroups:List<Grupo>){
        val dataBase:SQLiteDatabase = this.writableDatabase
        val values: ContentValues = ContentValues()
        listGroups.forEach {
            values.put(SetDB.tblGruposCreateTarea.COL_UID_GRUPO,it.uid )
            values.put(SetDB.tblGruposCreateTarea.COL_NAMEGRUPO, it.nameGroup)
            try {
                val result=dataBase.insert(SetDB.tblGruposCreateTarea.TABLE_NAME, null, values)
                values.clear()
            }
            catch (e: Exception){
                Log.e("Execption", e.toString())
            }
        }
        dataBase.close()
    }

    //cargar grupos en la pantalla de crear tarea
    @SuppressLint("Range")
    fun getGroupsCreateTarea():ArrayList<Grupo>{
        var listGrupos:ArrayList<Grupo> = ArrayList()
        val dataBase:SQLiteDatabase = this.writableDatabase
        val columns:Array<String> =  arrayOf(
            SetDB.tblGruposCreateTarea.COL_ID,
            SetDB.tblGruposCreateTarea.COL_UID_GRUPO,
            SetDB.tblGruposCreateTarea.COL_NAMEGRUPO
        )
        val data =  dataBase.query(SetDB.tblGruposCreateTarea.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            SetDB.tblGruposCreateTarea.COL_ID + " ASC")
        if (data.moveToFirst()){
            do {
                val grupo:Grupo= Grupo()
                grupo.uid=data.getString(data.getColumnIndex(SetDB.tblGruposCreateTarea.COL_UID_GRUPO)).toString()
                grupo.nameGroup=data.getString(data.getColumnIndex(SetDB.tblGruposCreateTarea.COL_NAMEGRUPO)).toString()
                listGrupos.add(grupo)
            }while (data.moveToNext())
        }
        return listGrupos
    }

    //borrar el borrador de la tarea que quieres dar de alta offline
    fun deleteDraft(){
        val db = this.writableDatabase
        try {
            //primer borrar la tabla de pdf, luego la de grupo, y al final la de tarea
            db.delete(SetDB.tbldraftPdf.TABLE_NAME, null, null)
            db.delete(SetDB.tbldraftGrupos.TABLE_NAME, null, null)
            db.delete(SetDB.tbldraftTarea.TABLE_NAME, null, null)

            db.close()

        }
        catch (e: Exception){
            Log.e("Execption", e.toString())
        }
    }

    //borrar los grupos que se muestran en la pantalla create tarea
    fun deleteGroups(){
        val db = this.writableDatabase
        try{
            db.delete(SetDB.tblGruposCreateTarea.TABLE_NAME, null, null)
            db.close()        }
        catch (e: Exception){
            Log.e("Execption", e.toString())
        }
    }

    //borrar las tareas que se muestran en la pantalla de home
    fun deleteTareas(){
        val db = this.writableDatabase
        try {
            db.delete(SetDB.tblListPdfs.TABLE_NAME, null, null)
            db.delete(SetDB.tblGrupo.TABLE_NAME, null, null)
            db.delete(SetDB.tblTarea.TABLE_NAME, null, null)

            db.close()
        }
        catch (e: Exception){
            Log.e("Execption", e.toString())
        }
    }



}