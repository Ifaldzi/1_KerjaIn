package com.example.kerjainproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TaskDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_detail)

        val topic = intent.getStringExtra("topic")
        val deadline = intent.extras?.getString("deadline")
        val description = intent.extras?.getString("description")

        findViewById<TextView>(R.id.topic_detail).text = topic
        findViewById<TextView>(R.id.deadline_detail).text = deadline
        findViewById<TextView>(R.id.task_detail).text = description

    }
}