package com.tosmart.launcher.ez_subtitle_subdl.network.domain

import android.os.Message

/**
 * @author wyq
 * @date 2024/4/22 16:10
 * @Description define request response result ResultType
 */
sealed class SubtitleResult<ResultType> {

    data class Success<ResultType>(val data: ResultType) : SubtitleResult<ResultType>()

    data class Error<ResultType>(val message: Message) : SubtitleResult<ResultType>()

    data class Loading<ResultType>(val isLoading: ResultType) : SubtitleResult<ResultType>()

    companion object {
        fun <ResultType> success(data: ResultType): SubtitleResult<ResultType> = Success(data)

        fun <ResultType> error(message: Message): SubtitleResult<ResultType> = Error(message)

        fun <ResultType> loading(isLoading: ResultType): SubtitleResult<ResultType> = Loading(isLoading)
    }
}