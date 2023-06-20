package com.github.asadej0951.neversitupcurrency.modules

import com.github.asadej0951.neversitupcurrency.service.APIService
import com.github.asadej0951.neversitupcurrency.service.RetrofitBuilder
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitBuilder }

    single<APIService> {
        get<RetrofitBuilder>().build("https://api.coindesk.com")
    }
}