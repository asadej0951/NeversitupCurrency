package com.github.asadej0951.neversitupcurrency

import android.app.Dialog
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.github.asadej0951.neversitupcurrency.databinding.DialogListBinding
import com.github.asadej0951.neversitupcurrency.service.GeneralUseCase
import com.super_rabbit.wheel_picker.OnValueChangeListener
import com.super_rabbit.wheel_picker.WheelPicker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel constructor(private val mGeneralUseCase: GeneralUseCase) : ViewModel() {
    lateinit var mDialog: Dialog
    val rateUSD = ObservableField("")
    val rateGBP = ObservableField("")
    val rateEUR = ObservableField("")
    val disclaimer = ObservableField("")
    val timer = ObservableField("")
    val click = MutableLiveData<String>()
    val selectText = ObservableField("Currency")
    val rateSelectText = ObservableField("")
    val rateSelect = ObservableField<Double>()
    val rateBTC = ObservableField("0.00")

    val visibilityTextBTC = ObservableField(View.VISIBLE)
    val visibilityEditTextSelect = ObservableField(View.VISIBLE)

    val callGetDataCurrency = MutableLiveData<String>()

    val mResponseDataCurrency = Transformations.switchMap(callGetDataCurrency) {
        mGeneralUseCase.doFinancialAdvisorLogin()
    }

    fun onClickButton() {
        click.value = "dialog"
    }

    fun onClickCardEUR() {
        click.value = "cardEUR"
    }

    fun onClickCardUSD() {
        click.value = "cardUSD"
    }

    fun onClickCardGBP() {
        click.value = "cardGBP"
    }

    fun updateClock() {
        val currentTime = System.currentTimeMillis()
        val dateFormatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val timeText = dateFormatter.format(Date(currentTime))
        timer.set(timeText)
    }

    fun dialogList(
        context: MainActivity,
        arrayList: ArrayList<String>,
        mClickCallBack: (String) -> Unit
    ) {
        try {
            mDialog.setCanceledOnTouchOutside(false)

            mDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            val binding: DialogListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.dialog_list,
                null,
                false
            )
            mDialog.setContentView(binding.root)
            mDialog.window?.attributes!!.width =
                (context.getDeviceMetrics().widthPixels * 1)
            binding.picker.setSelectorRoundedWrapPreferred(false)
            binding.picker.setWheelItemCount(3)
            binding.picker.setMin(0)
            binding.picker.setMax(arrayList.size)
            binding.picker.setAdapter(WPWeekDaysPickerAdapter(arrayList))
            binding.picker.scrollTo(0)
            var message = ""

            binding.picker.setOnValueChangeListener(object : OnValueChangeListener {
                override fun onValueChange(picker: WheelPicker, oldVal: String, newVal: String) {
                    message = picker.getCurrentItem()
                }
            })
            binding.layoutCenter.setOnClickListener {
                mDialog.cancel()
            }

            binding.txtSelect.setOnClickListener {
                if (message == "") {
                    message = arrayList[0]
                }
                mClickCallBack.invoke(message)
                mDialog.cancel()
            }

            mDialog.setCancelable(true)
            mDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("ErrorMain", "$e")
        }
    }

}