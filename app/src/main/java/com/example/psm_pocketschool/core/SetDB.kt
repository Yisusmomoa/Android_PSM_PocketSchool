package com.example.psm_pocketschool.core

class SetDB {

    //DECLARAMOS  EL NOMBRE Y VERSION DE TAL FOR QUE PUEDA SER VISIBLES PARA CUALQUIER CLASE
    companion object{
        val DB_NAME =  "bdProject.db"
        val DB_VERSION =  13
    }

    abstract class tblTarea{
        companion object{
            val TABLE_NAME="Tarea"
            val COL_ID="_id"
            val COL_UID="uid"
            val COL_TITLE="title"
            val COL_DESCR="description"
            val COL_Grupo_ID="grupo"

        }
    }

    abstract class tblGrupo{
        companion object{
            val TABLE_NAME="Grupo"
            val COL_ID="_id"
            val COL_NAME_GROUP="nameGroup"
            val COL_UID_Group="uid"
            //val COL_UID_Grupo_TareaP="uidTarea"
            val COL_ID_TAREAFK="idTarea"
        }
    }

    abstract class tblListPdfs{
        companion object{
            val TABLE_NAME="Pdfs"
            val COL_ID="_id"
            val PDF_File="pdfbase"
            val COL_PDF_Str="pdfStr"
            val COL_ID_PDF_TareaFK="idTarea"
            val COL_UID_Tarea="uidTarea"
        }
    }

    abstract class tbldraftTarea{
        companion object{
            val TABLE_NAME="DraftTarea"
            val COL_ID="_id"
            val COL_TITLE_TAREA="title"
            val COL_DESCR_TAREA="descr"
            val COL_DATE_FIN="dateFin"
        }
    }

    abstract class tbldraftGrupos{
        companion object{
            val TABLE_NAME="DraftGrupo"
            val COL_ID="_id"
            val COL_UID_GRUPO="uid"
            val COL_NAMEGRUPO_GRUPO="nameGroup"
        }
    }

    abstract class tbldraftPdf{
        companion object{
            val TABLE_NAME="DraftPdfs"
            val COL_ID="_id"
            val COL_STR64="pdfsTarea"
            val COL_PDF_NAME="pdfName"
        }
    }

    abstract class tblGruposCreateTarea{
        companion object{
            val TABLE_NAME="createGrupo"
            val COL_ID="_id"
            val COL_UID_GRUPO="uid"
            val COL_NAMEGRUPO="nameGroup"
        }
    }

}