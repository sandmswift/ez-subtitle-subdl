package com.tosmart.launcher.ez_subtitle_subdl.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.DownloadState
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.Subtitle
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.SubtitleResult
import com.tosmart.launcher.ez_subtitle_subdl.network.repository.DownloadRepository
import com.tosmart.launcher.ez_subtitle_subdl.network.repository.SubtitlesRepository
import com.tosmart.launcher.ez_subtitle_subdl.network.utils.getExternalStoragePath
import com.tosmart.launcher.ez_subtitle_subdl.network.utils.saveResponseBodyToFile
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

/**
 * @author wyq
 * @date 2024/4/23 16:13
 * @Description
 */
@HiltViewModel
class SubtitleViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    val subtitlesRepository: SubtitlesRepository,
    val downloadRepository: DownloadRepository
) : ViewModel() {

    private val TAG = SubtitleViewModel::class.simpleName
    private val _programs = MutableLiveData<SubtitleResult<List<Subtitle>>>()
    val programs: LiveData<SubtitleResult<List<Subtitle>>> get() = _programs

    fun getPrograms(filmName: String, type: String, language: String) {
        viewModelScope.launch {
            try {
                _programs.value = subtitlesRepository.getProgram(filmName, type, language)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun downloadSubtitle(path: String, name: String) {
        viewModelScope.launch {
            try {
                val downloadResponse = downloadRepository.downloadFile(path)
                if (downloadResponse is SubtitleResult.Success) {
                    saveResponseBodyToFile(appContext.applicationContext, name, downloadResponse.data)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun downloadFlowSubtitle(path: String, name: String) {
        viewModelScope.launch {
            try {
                val downloadResponse = downloadRepository.downloadFlowFile(path, File(getExternalStoragePath(appContext, name)))
                downloadResponse.collect {
                    Log.i(TAG, "downloadFlowSubtitle: $it")
                    when (it) {
                        is DownloadState.InProgress -> {
                            Log.i(TAG, "downloadFlowSubtitle: ${it.progress}")
                        }

                        is DownloadState.Success -> {
                            Log.d(TAG, "download finished.")
                        }

                        is DownloadState.Error -> {
                            Log.d(TAG, "download error: ${it.throwable}.")
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}

