package com.example.psm_pocketschool.Model.Pdf

import android.util.Base64

class PdfClass {
    constructor()
    constructor(pdfName: String, pdfBase64String: String) {
        this.pdfName = pdfName
        this.pdfBase64String = pdfBase64String
    }

    var pdfName:String = ""
    //var pdfBase64: Base64
    var pdfBase64String: String=""

}