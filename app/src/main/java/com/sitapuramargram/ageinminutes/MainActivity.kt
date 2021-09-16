package com.sitapuramargram.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         btnDatePicker.setOnClickListener { view ->
             showDataPicker(view)
         }
    }

    fun showDataPicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val mYear = myCalendar.get(Calendar.YEAR)
        val mMonth = myCalendar.get(Calendar.MONTH)
        val mDay = myCalendar.get(Calendar.DAY_OF_MONTH)

       val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            val selectedDate = "$dayOfMonth/${month+1}/$year"
            tvSelectedDate.text = selectedDate

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate = simpleDateFormat.parse(selectedDate)
            val selectDateInMinutes = theDate!!.time / 60000
            val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time /60000
            val differenceInMinutes = currentDateInMinutes - selectDateInMinutes
            tvSelectedDateInMinutes.text = differenceInMinutes.toString()

        }, mYear, mMonth, mDay)

        datePickerDialog.datePicker.maxDate = Date().time - 86400000
        datePickerDialog.show()
    }
}