package com.example.kerjainproject.database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    companion object{
        @TypeConverter
        @JvmStatic
        fun fromDeadline(value: Calendar): Long{
            return value.timeInMillis
        }

        @TypeConverter
        @JvmStatic
        fun toDeadline(value: Long): Calendar {
            val cal = Calendar.getInstance()
            cal.timeInMillis = value
            return cal
        }
    }
}