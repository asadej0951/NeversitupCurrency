package com.github.asadej0951.neversitupcurrency

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun main() {
        val n = 10
        println("Fibonacci Sequence:")
        for (i in 0 until n) {
            val fibonacciNumber = fibonacci(i)
            print("$fibonacciNumber ")
        }
    }

    private fun fibonacci(n: Int): Int {
        return if (n <= 1) {
            n
        } else {
            fibonacci(n - 1) + fibonacci(n - 2)
        }
    }

    @Test
    fun primeNumber() {
        val n = 10

        println("\nPrime Numbers:")
        var count = 0
        var number = 2

        while (count < n) {
            if (isPrime(number)) {
                print("$number ")
                count++
            }
            number++
        }
    }

    private fun isPrime(number: Int): Boolean {
        if (number <= 1) {
            return false
        }

        for (i in 2 until number) {
            if (number % i == 0) {
                return false
            }
        }

        return true
    }
}