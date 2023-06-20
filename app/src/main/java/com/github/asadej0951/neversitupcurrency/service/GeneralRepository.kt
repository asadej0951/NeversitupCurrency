package com.github.asadej0951.neversitupcurrency.service

import io.reactivex.Observable
import com.github.asadej0951.neversitupcurrency.model.ModelCurrency

class GeneralRepository constructor(private val apiService: APIService) {
    fun onDataCurrency() : Observable<ModelCurrency> = apiService.getDataCurrency()
}