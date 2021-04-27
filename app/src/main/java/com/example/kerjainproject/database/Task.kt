package com.example.kerjainproject.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "task")
data class Task(@ColumnInfo(name = "topic")val topic: String,
                @ColumnInfo(name = "priority")val priority: Int,
                @ColumnInfo(name = "deadline")val deadline: Calendar,
                @ColumnInfo(name = "description")val description: String) : Serializable {

    @PrimaryKey(autoGenerate = true) var id: Int? = null

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