package com.example.psm_pocketschool.core

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import java.io.ByteArrayOutputStream

class ImageManager {
    companion object
    {
        fun base64_EncodeImage(context: Context, uri: Uri) : String
        {
            val bm = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            val BAOS = ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, BAOS) // bm is the bitmap object

            val byteArrayImage: ByteArray = BAOS.toByteArray()

            val encodedImage: String = Base64.encodeToString(byteArrayImage, Base64.DEFAULT)
            return encodedImage;
        }
    }
}