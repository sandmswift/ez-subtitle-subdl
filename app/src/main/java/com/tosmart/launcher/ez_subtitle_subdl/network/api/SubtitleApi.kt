package com.tosmart.launcher.ez_subtitle_subdl.network.domain.network.api

import com.tosmart.launcher.ez_subtitle_subdl.network.domain.ProgramWrapper
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import retrofit2.http.Streaming

/**
 * @author wyq
 * @date 2024/4/22 15:53
 * @Description
 */
interface SubtitleApi {

    @GET("api/v1/subtitles")
    suspend fun selectProgram(@QueryMap map: Map<String, String>): Response<ProgramWrapper>


}

interface DownloadApi {
    @Streaming
    @GET("{path}")
    suspend fun download(@Path("path") path: String): Response<ResponseBody>

}