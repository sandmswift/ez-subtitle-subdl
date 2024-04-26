package com.tosmart.launcher.ez_subtitle_subdl.network.repository

import android.os.Message
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.DownloadState
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.Subtitle
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.SubtitleResult
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.network.api.DownloadApi
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.network.api.SubtitleApi
import com.tosmart.launcher.ez_subtitle_subdl.network.module.NetworkModule.API_KEY
import com.tosmart.launcher.ez_subtitle_subdl.network.utils.filterFileName
import com.tosmart.launcher.ez_subtitle_subdl.network.utils.saveToFile
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import java.io.File
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author wyq
 * @date 2024/4/23 15:54
 * @Description
 */
interface SubtitlesRepository {
    suspend fun getProgram(fileName: String, type: String, language: String): SubtitleResult<List<Subtitle>>

}

interface DownloadRepository {
    suspend fun downloadFile(filePath: String): SubtitleResult<ResponseBody>
    suspend fun downloadFlowFile(filePath: String, file: File): Flow<DownloadState>
}

@Singleton
class SubtitlesRepositoryImpl @Inject constructor(
    private val subtitlesService: SubtitleApi
) : SubtitlesRepository {

    override suspend fun getProgram(fileName: String, type: String, language: String): SubtitleResult<List<Subtitle>> {
        try {
            val map = HashMap<String, String>()
            map["api_key"] = API_KEY
            map["film_name"] = fileName
            map["type"] = type
            map["languages"] = language
            val program = subtitlesService.selectProgram(map).body()
            if (program != null) {
                val subtitleList = program.subtitles
                return SubtitleResult.success(subtitleList)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return SubtitleResult.error(Message())
    }

}

@Singleton
class DownloadRepositoryImpl @Inject constructor(
    private val downloadService: DownloadApi
) : DownloadRepository {

    override suspend fun downloadFile(filePath: String): SubtitleResult<ResponseBody> {
        val response = downloadService.download(filterFileName(filePath))
        if (response.isSuccessful && response.body() != null) {
            return SubtitleResult.success(response.body()!!)
        } else {
            return SubtitleResult.error(Message())
        }
    }

    override suspend fun downloadFlowFile(filePath: String, file: File): Flow<DownloadState> {
        return flow {
            val response = downloadService.download(filterFileName(filePath))
            if (response.isSuccessful) {
                saveToFile(response.body()!!, file) {
                    emit(DownloadState.InProgress(it))
                }
                emit(DownloadState.Success(file))
            } else {
                emit(DownloadState.Error(IOException(response.toString())))
            }
        }.catch {
            emit(DownloadState.Error(it))
        }.flowOn(Dispatchers.IO)

    }

}


@Module
@InstallIn(SingletonComponent::class)
interface NewsRepositoryModule {
    @Binds
    fun bindSubtitlesRepository(it: SubtitlesRepositoryImpl): SubtitlesRepository

    @Binds
    fun bindDownloadRepository(it: DownloadRepositoryImpl): DownloadRepository
}