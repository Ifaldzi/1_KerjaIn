package com.example.kerjainproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.kerjainproject.database.Task
import com.example.kerjainproject.ui.task.TaskViewModel
import com.example.kerjainproject.ui.task.TaskViewModelFactory

class TaskDetailActivity : AppCompatActivity() {
    private val taskViewModel: TaskViewModel by viewModels{
        TaskViewModelFactory((application as KerjainApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_detail)

        val task = intent.getSerializableExtra("task") as Task

        val topic = task.topic
        val deadline = task.deadlineToString(this)
        val description = task.description

        findViewById<TextView>(R.id.topic_detail).text = topic
        findViewById<TextView>(R.id.deadline_detail).text = deadline
        findViewById<TextView>(R.id.task_detail).text = description

        val doneButton = findViewById<TextView>(R.id.detail_done_btn)
        doneButton.setOnClickListener {
            taskViewModel.delete(task)
            finish()
        }
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