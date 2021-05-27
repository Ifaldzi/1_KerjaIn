package com.example.kerjainproject

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.format.DateFormat
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.kerjainproject.database.Task
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    lateinit var dateText: TextView
    lateinit var timeText: TextView
    var priority = 0

    companion object {
        const val NEW_TASK_REPLY = "com.example.kerjainproject.NEW_TASK_REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task)

        val calendar = Calendar.getInstance()
        dateText = findViewById<TextView>(R.id.deadline_date)
        dateText.text = getFormattedDate(calendar)
        dateText.setOnClickListener(View.OnClickListener {
            showDatePicker(calendar)
        })

        timeText = findViewById(R.id.deadline_time)
        timeText.text = getFormattedTime(calendar)
        timeText.setOnClickListener {
            showTimePicker(calendar)
        }

        val priorityGroup = findViewById<RadioGroup>(R.id.priority_group)
        priorityGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.priority_radio_1 -> priority = 1
                R.id.priority_radio_2 -> priority = 2
                R.id.priority_radio_3 -> priority = 3
            }
        }

        val saveButton = findViewById<Button>(R.id.addButton)
        saveButton.setOnClickListener {
            val topic = findViewById<EditText>(R.id.editTextTopic).text
            val taskDescription = findViewById<EditText>(R.id.editTextDescription).text
            if(priority == 0 || topic.isEmpty()) {
                Toast.makeText(this, "Form belum terisi seluruhnya", Toast.LENGTH_SHORT)
                    .show()
            }
            else {
                val newTask = Task(topic.toString(), priority, calendar, taskDescription.toString())
                val replyIntent = Intent()
                replyIntent.putExtra(NEW_TASK_REPLY, newTask)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDatePicker(calendar: Calendar){
        val cYear = calendar.get(Calendar.YEAR)
        val cMonth = calendar.get(Calendar.MONTH)
        val cDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, {
                _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            dateText.text = getFormattedDate(calendar)
        }, cYear, cMonth, cDay)
        datePickerDialog.show()
    }

    private fun showTimePicker(calendar: Calendar) {
        val timePickerDialog = TimePickerDialog(this, {
                _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            timeText.text = getFormattedTime(calendar)
        }, calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            DateFormat.is24HourFormat(this))
        timePickerDialog.show()
    }

    private fun getFormattedDate(calendar: Calendar): String {
        val day = String.format("%ta", calendar)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = String.format("%tB", calendar)
        val year = calendar.get(Calendar.YEAR)
        return getString(R.string.date, day, dayOfMonth, month, year)
    }

    private fun getFormattedTime(calendar: Calendar): String {
        var hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        if(DateFormat.is24HourFormat(this)){
            return getString(R.string.time, hour, minute)
        }
        hour = String.format("%tl", calendar).toInt()
        return getString(R.string.time_12_hour_format, hour, minute, String.format("%Tp", calendar))
    }
}