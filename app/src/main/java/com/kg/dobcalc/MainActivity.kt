package com.kg.dobcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {


    private var TVSelected : TextView? = null
    private var TVAnswer : TextView? = null
    private var TVBottom : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        val buttondatepicker : Button = findViewById(R.id.btDatePicker)
        TVSelected = findViewById(R.id.TVSelected)
        TVAnswer = findViewById(R.id.TVAnswer)
        TVBottom = findViewById(R.id.TVBottom)
//        buttondatepicker.setOnClickListener {
//            Toast.makeText(this,
//                "Date is selected!" , Toast.LENGTH_SHORT).show()
//        }
        click(buttondatepicker)
    }

    @SuppressLint("SetTextI18n")
    private fun click(buttondatepicker : Button)
    {
        buttondatepicker.setOnClickListener {
            val myCalendar = Calendar.getInstance()
            val year = myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val day = myCalendar.get(Calendar.DAY_OF_MONTH)
            val datepicker = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, syear, smonth, sdayOfmonth ->
                Toast.makeText(this,
                "$sdayOfmonth/${smonth+1}/$syear is selected" , Toast.LENGTH_SHORT).show()
                val selecteddate = "$sdayOfmonth/${smonth+1}/$syear"
                TVSelected?.setText(selecteddate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val thedate = sdf.parse(selecteddate)
                thedate?.let {
                    val selectedDateindays = thedate.time / 86400000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateindays = currentDate.time / 86400000
                        val difference = currentDateindays - selectedDateindays
                        TVAnswer?.text = (difference).toString()
                        TVBottom?.text = "You are ${TVAnswer?.text} days old"
                    }
                }
            },
            year,
            month,
            day)
            datepicker.datePicker.maxDate = myCalendar.timeInMillis
            datepicker.show()
        }
    }
}