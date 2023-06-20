package com.github.asadej0951.neversitupcurrency.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    inline fun <reified T>build(urlService :String):T{
        return Retrofit.Builder()
            .baseUrl(urlService)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClientBuilder.okHttpClient())
            .build()
            .create(T::class.java)
    }
}