package com.example.kerjainproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kerjainproject.KerjainApplication
import com.example.kerjainproject.R
import com.example.kerjainproject.TaskDetailActivity
import com.example.kerjainproject.database.Task
import com.example.kerjainproject.ui.task.TaskViewModel
import com.example.kerjainproject.ui.task.TaskViewModelFactory
import org.w3c.dom.Text

class HomeFragment : Fragment() {
    private val taskViewModel: TaskViewModel by viewModels{
        TaskViewModelFactory((activity?.application as KerjainApplication).repository)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val button = root.findViewById<Button>(R.id.add_task_button)
        button.setOnClickListener {
            val intent = Intent("com.example.kerjainproject.AddTaskActivity")
            startActivity(intent)
        }

        taskViewModel.tasks.observe(viewLifecycleOwner){tasks ->
            attachDataToDashboard(root, tasks)
        }

        return root
    }

    private fun attachDataToDashboard(root: View ,tasks: List<Task>) {
        var totalTask: Int
        tasks.let {totalTask = it.size}
        val taskTotalView = root.findViewById<TextView>(R.id.task_total)
        taskTotalView.text = totalTask.toString()
        val upcomingTask = tasks.first()
        root.findViewById<TextView>(R.id.task_topic_home).text = upcomingTask.topic
        root.findViewById<TextView>(R.id.deadline_task_home).text = upcomingTask.deadlineToString()

        val taskContainer = root.findViewById<LinearLayout>(R.id.task_home_container)
        taskContainer.setOnClickListener {
            val intent = Intent(it.context, TaskDetailActivity::class.java)
            intent.putExtra("topic", upcomingTask.topic)
            intent.putExtra("deadline", upcomingTask.deadlineToString())
            intent.putExtra("description", upcomingTask.description)
            it.context.startActivity(intent)
        }
    }
}