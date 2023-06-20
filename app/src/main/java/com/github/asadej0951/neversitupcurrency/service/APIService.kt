package com.github.asadej0951.neversitupcurrency.service

import com.github.asadej0951.neversitupcurrency.model.ModelCurrency
import retrofit2.http.GET

interface APIService {
    @GET("/v1/bpi/currentprice.json")
    fun getDataCurrency(): io.reactivex.Observable<ModelCurrency>
}