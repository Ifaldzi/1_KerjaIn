package com.example.kerjainproject.helper

import android.text.format.DateFormat
import com.example.kerjainproject.R
import java.util.*
import android.content.res.Resources

class DateFormatter {
    companion object {
        fun getFormattedDate(calendar: Calendar): String {
            val day = String.format("%ta", calendar)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val month = String.format("%tB", calendar)
            val year = calendar.get(Calendar.YEAR)
            return String.format("%s, %d %s %d", day, dayOfMonth, month, year)
        }

        fun getFormattedTime(calendar: Calendar, is24HourFormat: Boolean): String {
            var hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            if(is24HourFormat){
                return String.format("%02d:%02d", hour, minute)
            }
            hour = String.format("%tl", calendar).toInt()
            return String.format("%02d:%02d %s",
                hour,
                minute,
                String.format("%Tp", calendar))
        }
    }
}