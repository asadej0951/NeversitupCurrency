package com.github.asadej0951.neversitupcurrency.service

import com.github.asadej0951.neversitupcurrency.model.ModelCurrency

class GeneralUseCase constructor(
    private val generalRepository: GeneralRepository,
) {
    fun doFinancialAdvisorLogin() =
        object : NetworkBoundResource<ModelCurrency>() {
            override fun saveCallResult(item: String) {}
            override fun createCall() = generalRepository.onDataCurrency()
        }.asLiveData()
}