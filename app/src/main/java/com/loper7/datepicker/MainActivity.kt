package com.loper7.datepicker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.loper7.date_time_picker.DateTimeConfig
import com.loper7.date_time_picker.StringUtils
import com.loper7.date_time_picker.dialog.CardDatePickerDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var maxDate: Long = 0
    private var minDate: Long = 0
    private var defaultDate: Long = 0

    private lateinit var context: Context

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this


        tvMaxDate.setOnClickListener {
            CardDatePickerDialog.builder(this)
                .setTitle("SET MAX DATE")
                .setOnChoose {
                    maxDate = it
                    tvMaxDate.text = StringUtils.conversionTime(it, "yyyy-MM-dd HH:mm:ss")
                }.build().show()

        }

        btnClearMax.setOnClickListener {
            maxDate = 0L
            tvMaxDate.text = ""
        }

        tvMinDate.setOnClickListener {
            CardDatePickerDialog.builder(this)
                .setTitle("SET MIN DATE")
                .setOnChoose {
                    minDate = it
                    tvMinDate.text = StringUtils.conversionTime(it, "yyyy-MM-dd HH:mm:ss")
                }.build().show()
        }

        btnClearMin.setOnClickListener {
            minDate = 0L
            tvMinDate.text = ""
        }

        tvDefaultDate.setOnClickListener {
            CardDatePickerDialog.builder(this)
                .setTitle("SET DEFAULT DATE")
                .setOnChoose {
                    defaultDate = it
                    tvDefaultDate.text =
                        StringUtils.conversionTime(it, "yyyy-MM-dd HH:mm:ss")
                }.build().show()
        }

        btnClearDefault.setOnClickListener {
            defaultDate = 0L
            tvDefaultDate.text = ""
        }


        btnCardDialogShow.setOnClickListener {

            val displayList = mutableListOf<Int>()
            if (checkYear.isChecked)
                displayList.add(DateTimeConfig.YEAR)
            if (checkMonth.isChecked)
                displayList.add(DateTimeConfig.MONTH)
            if (checkDay.isChecked)
                displayList.add(DateTimeConfig.DAY)
            if (checkHour.isChecked)
                displayList.add(DateTimeConfig.HOUR)
            if (checkMin.isChecked)
                displayList.add(DateTimeConfig.MIN)
            if (checkSecond.isChecked)
                displayList.add(DateTimeConfig.SECOND)


            var model = CardDatePickerDialog.CARD
            if (radioModelCard.isChecked)
                model = CardDatePickerDialog.CARD
            if (radioModelCube.isChecked)
                model = CardDatePickerDialog.CUBE
            if (radioModelStack.isChecked)
                model = CardDatePickerDialog.STACK
            if (radioModelCustom.isChecked)
                model = R.drawable.shape_bg_dialog_custom

            CardDatePickerDialog.builder(context)
                .setTitle("CARD DATE PICKER DIALOG")
                .setDisplayType(displayList)
                .setBackGroundModel(model)
                .showBackNow(checkBackNow.isChecked)
                .setDefaultTime(defaultDate)
                .setMaxTime(maxDate)
                .setMinTime(minDate)
                .setWrapSelectorWheel(false)
                .setThemeColor(if (model == R.drawable.shape_bg_dialog_custom) Color.parseColor("#FF8000") else 0)
                .showDateLabel(checkUnitLabel.isChecked)
                .showFocusDateInfo(checkDateInfo.isChecked)
                .setOnChoose("选择") {
                    tvChooseDate.text = "◉  ${StringUtils.conversionTime(it, "yyyy-MM-dd HH:mm:ss")}    ${StringUtils.getWeek(it)}"
                }
                .setOnCancel("关闭") {
                }.build().show()
        }


        //dateTimePicker使用示例
        dateTimePicker.setLabelText("年", "月", "日", "时", "分","秒")
        dateTimePicker.setWrapSelectorWheel(DateTimeConfig.MONTH,DateTimeConfig.DAY,wrapSelector = false)
        dateTimePicker.setOnDateTimeChangedListener {
            tvDateTimePickerValue.text = "${StringUtils.conversionTime(it, "yyyy年MM月dd日 HH时mm分ss秒")}  ${StringUtils.getWeek(it)}"
        }
    }
}
