package com.example.kerjainproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}