package com.github.asadej0951.neversitupcurrency.modules

import com.github.asadej0951.neversitupcurrency.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}