package com.github.asadej0951.neversitupcurrency.modules

import com.github.asadej0951.neversitupcurrency.service.GeneralRepository
import com.github.asadej0951.neversitupcurrency.service.GeneralUseCase
import org.koin.dsl.module

val repositoryModule = module {
    single { GeneralRepository(get()) }
    single { GeneralUseCase(get()) }
}