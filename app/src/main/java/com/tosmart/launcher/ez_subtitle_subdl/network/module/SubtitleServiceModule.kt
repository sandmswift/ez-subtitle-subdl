package com.tosmart.launcher.ez_subtitle_subdl.network.module

import com.tosmart.launcher.ez_subtitle_subdl.network.domain.network.api.DownloadApi
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.network.api.SubtitleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SubtitleServiceModule {

    @Singleton
    @Provides
    fun provideSubtitleService(@NetworkModule.SubtitleRetrofit retrofit: Retrofit): SubtitleApi = retrofit.create(SubtitleApi::class.java)


    @Singleton
    @Provides
    fun provideDownloadService(@NetworkModule.DownloadRetrofit retrofit: Retrofit): DownloadApi = retrofit.create(DownloadApi::class.java)
}