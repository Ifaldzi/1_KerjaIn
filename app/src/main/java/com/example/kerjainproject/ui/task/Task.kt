package com.example.kerjainproject.ui.task

import java.text.SimpleDateFormat
import java.util.*

data class Task(val topic: String,
           val priority: Int,
           val deadline: GregorianCalendar,
           val description: String) {

    fun deadlineToString(): String{
        val day =  deadline.get(GregorianCalendar.DAY_OF_MONTH).toString()
        val monthFormat = SimpleDateFormat("MMMM")
        val month = monthFormat.format(deadline.time)
        val year = deadline.get(GregorianCalendar.YEAR).toString()
        val hour = deadline.get(GregorianCalendar.HOUR_OF_DAY).toString()
        val minute = deadline.get(GregorianCalendar.MINUTE)

        return "$day $month $year\n$hour : $minute"
    }
}