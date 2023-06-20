package com.github.asadej0951.neversitupcurrency.service

class ModelError {
    data class ModelError(
        val errors: Errors
    )

    data class Errors(val message: String?)
}