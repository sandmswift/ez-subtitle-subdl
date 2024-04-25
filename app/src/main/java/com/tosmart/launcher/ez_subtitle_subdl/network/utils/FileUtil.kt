package com.tosmart.launcher.ez_subtitle_subdl.network.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * @author wyq
 * @date 2024/4/24 16:52
 * @Description
 */


/**
 * get external storage path and create subtitle file path
 */
fun getExternalStoragePath(context: Context, fileName: String): String {
    val downloadsDirectory = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
    return "${downloadsDirectory?.absolutePath}/$fileName.zip"
}


/**
 * save subtitle file to target path
 */
fun saveResponseBodyToFile(context: Context, fileName: String, responseBody: ResponseBody): Boolean {
    Log.i("TAG", "saveResponseBodyToFile: " + getExternalStoragePath(context, fileName))
    val file = File(getExternalStoragePath(context, fileName))
    val fos = FileOutputStream(file)

    try {
        val bytesCopied = fos.use { output ->
            responseBody.byteStream().use { input ->
                input.copyTo(output)
            }
        }
        return bytesCopied > 0
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        responseBody.close()
    }
    return false
}