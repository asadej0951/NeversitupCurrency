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

    fun eventClauseNo1(edTest1: EditText, mainActivity: MainActivity, imageIcon1: ImageView) {
        edTest1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length >= 6) {
                    Glide.with(mainActivity).load(R.drawable.baseline_check_circle_24).into(imageIcon1)
                }else{
                    Glide.with(mainActivity).load(R.drawable.baseline_cancel_24).into(imageIcon1)
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    fun eventClauseNo2(edTest2: EditText, mainActivity: MainActivity, imageIcon2: ImageView) {
        edTest2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    val num = stringToIntArrayList(s.toString())
                   if ( isInputValid(num)){
                       Glide.with(mainActivity).load(R.drawable.baseline_check_circle_24).into(imageIcon2)
                   }else{
                       Glide.with(mainActivity).load(R.drawable.baseline_cancel_24).into(imageIcon2)
                   }
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    fun eventClauseNo3(edTest3: EditText, mainActivity: MainActivity, imageIcon3: ImageView) {
        edTest3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    val num = stringToIntArrayList(s.toString())
                    if ( isInputValidNo2(num)){
                        Glide.with(mainActivity).load(R.drawable.baseline_check_circle_24).into(imageIcon3)
                    }else{
                        Glide.with(mainActivity).load(R.drawable.baseline_cancel_24).into(imageIcon3)
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    fun eventClauseNo4(edTest4: EditText, mainActivity: MainActivity, imageIcon4: ImageView) {
        edTest4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    val num = stringToIntArrayList(s.toString())
                    if ( isInputValidNo3(num)){
                        Glide.with(mainActivity).load(R.drawable.baseline_check_circle_24).into(imageIcon4)
                    }else{
                        Glide.with(mainActivity).load(R.drawable.baseline_cancel_24).into(imageIcon4)
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

   private fun isInputValid(input: ArrayList<Int>): Boolean {
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

   private fun isInputValidNo2(input: ArrayList<Int>): Boolean {
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

    private fun isInputValidNo3(input: ArrayList<Int>): Boolean {
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