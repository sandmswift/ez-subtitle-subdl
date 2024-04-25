package com.tosmart.launcher.ez_subtitle_subdl.network.module

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Qualifier
import javax.inject.Singleton


/**
 * @author wyq
 * @date 2024/4/22 15:53
 * @Description define network module
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val API_KEY = "your api key"
    private const val BASE_URL = "https://api.subdl.com/"
    private const val DOWNDLOAD_BASE_URL = "https://dl.subdl.com/"

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class SubtitleRetrofit

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class DownloadRetrofit

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(LogInterceptor())
            .build()
    }

    @Singleton
    @Provides
    @SubtitleRetrofit
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @DownloadRetrofit
    fun provideDownloadRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DOWNDLOAD_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    class LogInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            Log.d(TAG, "request = " + request.toString())
            return chain.proceed(request)
        }

        companion object {
            private const val TAG = "LogInterceptor"
        }
    }


}