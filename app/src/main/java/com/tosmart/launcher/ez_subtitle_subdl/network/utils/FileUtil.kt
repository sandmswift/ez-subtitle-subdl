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

const val SPLIT = "/"
const val FILE_SUFFIX = "."

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

fun removeFileExtension(fileName: String): String {
    val lastDotIndex = fileName.lastIndexOf(FILE_SUFFIX)
    return if (lastDotIndex != -1) {
        fileName.substring(0, lastDotIndex)
    } else {
        fileName
    }
}

fun filterFileName(fileName: String): String {
    var path = fileName
    if (path.contains(SPLIT)) {
        val lastSplitIndex = path.lastIndexOf(SPLIT)
        if (lastSplitIndex < path.length - 1) {
            path = path.substring(lastSplitIndex + 1)
        }
    }
    return removeFileExtension(path)
}

inline fun saveToFile(responseBody: ResponseBody, file: File, progressListener: (Int) -> Unit) {
    val total = responseBody.contentLength()
    var bytesCopied = 0
    var emittedProgress = 0
    file.outputStream().use { output ->
        val input = responseBody.byteStream()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        var bytes = input.read(buffer)
        while (bytes >= 0) {
            output.write(buffer, 0, bytes)
            bytesCopied += bytes
            bytes = input.read(buffer)
            val progress = (bytesCopied * 100 / total).toInt()
            if (progress - emittedProgress > 0) {
                progressListener(progress)
                emittedProgress = progress
            }
        }
    }
}

