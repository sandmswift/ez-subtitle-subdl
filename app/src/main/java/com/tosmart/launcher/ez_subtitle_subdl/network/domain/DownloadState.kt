package com.tosmart.launcher.ez_subtitle_subdl.network.domain

import java.io.File

sealed class DownloadState {
    data class InProgress(val progress: Int) : DownloadState()
    data class Success(val file: File) : DownloadState()
    data class Error(val throwable: Throwable) : DownloadState()
}
