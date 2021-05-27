package com.example.kerjainproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kerjainproject.KerjainApplication
import com.example.kerjainproject.MainActivity
import com.example.kerjainproject.R
import com.example.kerjainproject.TaskDetailActivity
import com.example.kerjainproject.database.Task
import com.example.kerjainproject.ui.task.TaskViewModel
import com.example.kerjainproject.ui.task.TaskViewModelFactory

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
            startActivityForResult(intent, MainActivity.NEW_TASK_REQUEST_ACTIVITY)
        }

        taskViewModel.tasks.observe(viewLifecycleOwner){tasks ->
            attachDataToDashboard(root, tasks)
            setContainer(root, tasks.isEmpty())
        }

        return root
    }

    private fun attachDataToDashboard(root: View ,tasks: List<Task>) {
        var totalTask: Int
        tasks.let {totalTask = it.size}
        val taskTotalView = root.findViewById<TextView>(R.id.task_total)
        taskTotalView.text = totalTask.toString()
        if (tasks.isNotEmpty()) {
            val upcomingTask = tasks.first()
            root.findViewById<TextView>(R.id.task_topic_home).text = upcomingTask.topic
            root.findViewById<TextView>(R.id.deadline_task_home).text = context?.let {
                upcomingTask.deadlineToString(
                    it
                )
            }

            val taskContainer = root.findViewById<LinearLayout>(R.id.task_home_container)
            taskContainer.setOnClickListener {
                val intent = Intent(it.context, TaskDetailActivity::class.java)
                intent.putExtra("task", upcomingTask)
                it.context.startActivity(intent)
            }

            root.findViewById<Button>(R.id.home_task_done_btn).setOnClickListener {
                taskViewModel.delete(upcomingTask)
            }
        }
    }

    private fun setContainer(view: View, listEmpty: Boolean) {
        val nonEmptyContainer = view.findViewById<LinearLayout>(R.id.not_empty_container)
        val emptyContainer = view.findViewById<TextView>(R.id.empty_container)
        val doneButton = view.findViewById<Button>(R.id.home_task_done_btn)
        if(listEmpty) {
            nonEmptyContainer.visibility = View.GONE
            doneButton.visibility = View.GONE
            emptyContainer.visibility = View.VISIBLE
        }
        else {
            nonEmptyContainer.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE
            emptyContainer.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val parentActivity = activity as MainActivity
        parentActivity.onActivityResult(requestCode, resultCode, data)
    }
}