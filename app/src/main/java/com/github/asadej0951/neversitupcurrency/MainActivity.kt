package com.github.asadej0951.neversitupcurrency

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.github.asadej0951.neversitupcurrency.databinding.ActivityMainBinding
import com.github.asadej0951.neversitupcurrency.model.Bpi
import com.github.asadej0951.neversitupcurrency.model.ModelCurrency
import com.github.asadej0951.neversitupcurrency.service.Status
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mViewModel: MainViewModel by viewModel()
    private val mDataArrayList = ArrayList<ModelCurrency>()
    private lateinit var timer: Timer
    private lateinit var handler: Handler
    private val arrayListString = ArrayList<String>()
    private var indexDataArrayList = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mViewModel
        eventClickCard()
        mViewModel.mDialog = Dialog(this)

        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    mViewModel.updateClock()
                }
            }
        }, 0, 1000)
        handler = Handler(Looper.getMainLooper())


        mViewModel.callGetDataCurrency.value = "get"
        val delayMillis: Long = 60000
        handler.postDelayed(apiRunnable, delayMillis)
        setInit()
    }

    private val apiRunnable = object : Runnable {
        override fun run() {
            mViewModel.callGetDataCurrency.value = "get"
            // วนซ้ำเรียกใช้งาน API ทุก 1 นาที
            val delayMillis: Long = 60000
            handler.postDelayed(this, delayMillis)
        }
    }

    private fun setInit() {
        mViewModel.mResponseDataCurrency.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        arrayListString.add(data.time.updated)
                        mDataArrayList.add(data)
                        indexDataArrayList = 0
                        mViewModel.disclaimer.set("disclaimer : ${data.disclaimer}")
                        setDataView(data.bpi)
                    }
                }
                Status.ERROR -> {
                    Log.i("errorService", it.message.toString())
                }
                Status.LOADING -> {}
            }
        })

        mViewModel.click.observe(this, Observer {
            if (it == "dialog"){
                mViewModel.dialogList(this, arrayListString) { string ->
                    indexDataArrayList = arrayListString.indexOf(string)
                    setDataView(mDataArrayList[indexDataArrayList].bpi)
                    if (binding.editTextSelect.text.toString() != ""){
                        calculate(binding.editTextSelect.text.toString())
                    }
                }
            }else{
                eventClickCard()
                when (it) {
                    "cardEUR" -> {
                        mViewModel.selectText.set("EUR")
                        mViewModel.rateSelect.set(mDataArrayList[indexDataArrayList].bpi.EUR.rate_float)
                        binding.cardEUR.setCardBackgroundColor(resources.getColorStateList(R.color.teal_700))
                    }
                    "cardUSD" -> {
                        mViewModel.selectText.set("USD")
                        mViewModel.rateSelect.set(mDataArrayList[indexDataArrayList].bpi.USD.rate_float)
                        binding.cardUSD.setCardBackgroundColor(resources.getColorStateList(R.color.teal_700))
                    }
                    "cardGBP" -> {
                        mViewModel.selectText.set("GBP")
                        mViewModel.rateSelect.set(mDataArrayList[indexDataArrayList].bpi.GBP.rate_float)
                        binding.cardGBP.setCardBackgroundColor(resources.getColorStateList(R.color.teal_700))
                    }
                }
                if (binding.editTextSelect.text.toString() != ""){
                    calculate(binding.editTextSelect.text.toString())
                }
            }

        })

        binding.editTextSelect.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty() && mViewModel.selectText.get().toString() != "Currency") {
                    calculate(s.toString())
                }else if ( mViewModel.selectText.get().toString() != "Currency"){
                    calculate("0")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun calculate(text: String) {
        val number = text.toDouble() / mViewModel.rateSelect.get()!!
        val formattedNumber = String.format("%.6f", number)
        mViewModel.rateBTC.set(formattedNumber)
    }

    private fun eventClickCard() {
        binding.cardUSD.setCardBackgroundColor(null)
        binding.cardEUR.setCardBackgroundColor(null)
        binding.cardGBP.setCardBackgroundColor(null)
    }

    private fun setDataView(mBpi: Bpi) {
        mViewModel.rateEUR.set(mBpi.EUR.rate)
        mViewModel.rateUSD.set(mBpi.USD.rate)
        mViewModel.rateGBP.set(mBpi.GBP.rate)
    }


    fun getDeviceMetrics(): DisplayMetrics {
        val metrics = DisplayMetrics()
        val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        display.getMetrics(metrics)
        return metrics
    }


    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        handler.removeCallbacks(apiRunnable)
    }

}