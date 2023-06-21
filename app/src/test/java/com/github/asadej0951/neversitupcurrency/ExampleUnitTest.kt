package com.github.asadej0951.neversitupcurrency

import com.bumptech.glide.Glide
import org.junit.Test


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

    @Test
    fun clauseNo1(){
        val text = "17283"
        val text2 = "172839"
        println("\nclauseNo1:")

        print("$text :${checkLength(text)}")
        print("\n$text2 :${checkLength(text2)}")
    }

    private fun checkLength(text : String) : Boolean{
        return text.toString().length >= 6
    }
    @Test
    fun clauseNo2(){
        val text = "111822"
        val text2 = "112762"
        println("\nclauseNo2:")

        print("$text :${isInputValidClauseNo2(stringToIntArrayList(text))}")
        print("\n$text2 :${isInputValidClauseNo2(stringToIntArrayList(text2))}")
    }

    private fun isInputValidClauseNo2(input: ArrayList<Int>): Boolean {
        val maxAllowedDuplicates = 2
        val counts = mutableMapOf<Int, Int>()
        for (num in input) {
            val count = counts.getOrDefault(num, 0)
            if (count >= maxAllowedDuplicates) {
                return false
            }
            counts[num] = count + 1
        }

        return true
    }

    @Test
    fun clauseNo3(){
        val text = "123743"
        val text2 = "321895"
        val text3 = "124578"
        println("\nclauseNo3:")

        print("$text :${isInputValidClauseNo3(stringToIntArrayList(text))}")
        print("\n$text2 :${isInputValidClauseNo3(stringToIntArrayList(text2))}")
        print("\n$text2 :${isInputValidClauseNo3(stringToIntArrayList(text3))}")
    }

    private fun isInputValidClauseNo3(input: ArrayList<Int>): Boolean {
        val maxConsecutive = 1
        var consecutiveCount = 0
        for (i in 1 until input.size) {
            if (input[i]-1 == input[i - 1] || input[i] == input[i - 1]-1) {
                consecutiveCount++
                if (consecutiveCount > maxConsecutive) {
                    return false
                }
            } else {
                consecutiveCount = 0
            }
        }

        return true
    }

    @Test
    fun clauseNo4(){
        val text = "112233"
        val text2 = "882211"
        val text3 = "887712"
        println("\nclauseNo4:")

        print("$text :${isInputValidClauseNo4(stringToIntArrayList(text))}")
        print("\n$text2 :${isInputValidClauseNo4(stringToIntArrayList(text2))}")
        print("\n$text2 :${isInputValidClauseNo4(stringToIntArrayList(text3))}")
    }

    private fun isInputValidClauseNo4(input: ArrayList<Int>): Boolean {
        val maxConsecutive = 1
        var consecutiveCount = 0
        val arrayNum = kotlin.collections.ArrayList<Int>()
        for (i in 1 until input.size) {
            if (input[i] == input[i - 1]) {
                consecutiveCount++
                if (consecutiveCount >= maxConsecutive && arrayNum.indexOf(input[i]) == -1) {
                    arrayNum.add(input[i])
                }
            } else {
                consecutiveCount = 0
            }
        }
        return arrayNum.size <= 2
    }

    private fun stringToIntArrayList(input: String): ArrayList<Int> {
        val numbers = input.map { it.toString().toInt() }
        return ArrayList(numbers)
    }
}