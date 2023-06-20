package com.github.asadej0951.neversitupcurrency.service

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*
import java.util.concurrent.TimeUnit

object OkHttpClientBuilder {
    @SuppressLint("LogNotTimber", "HardwareIds")
    fun okHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor {
            val original: Request = it.request()
            val request: Request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("accept", "*/*")
                .build()
            return@addInterceptor it.proceed(request)
        }.addInterceptor(interceptor)
            .connectTimeout(Constants.TIME_CONNECT, TimeUnit.SECONDS)
            .readTimeout(Constants.TIME_CONNECT, TimeUnit.SECONDS)
            .build()
        return httpClient.build()
    }
}