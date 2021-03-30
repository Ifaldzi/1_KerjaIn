package com.example.kerjainproject.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kerjainproject.MainActivity
import com.example.kerjainproject.R
import java.time.Duration
import java.util.*
import kotlin.Comparator

class TaskListFragment : Fragment() {

    /* Example Data Set */
    val tasks = arrayOf(Task("Kalkulus", 2,
        GregorianCalendar(2021, Calendar.APRIL, 24, 23, 59),
        "Mantap"),
        Task("PBO, Inheritance", 1,
            GregorianCalendar(2021, Calendar.APRIL, 3, 23, 59),
            "Tugas membuat aplikasi sederhana dengan konsep inheritance"),
        Task("B. Inggris, Introduction", 3,
            GregorianCalendar(2021, Calendar.APRIL, 7, 17, 0),
            "Make it Perfect"),
        Task("DDP, Pseudocode", 1,
            GregorianCalendar(2021, Calendar.APRIL, 5, 23, 59),
            "Membuat pseudocode untuk sistem vending machine"),
        Task("SDA, Tree", 1,
            GregorianCalendar(2021, Calendar.APRIL, 10, 23, 59),
            "Membuat pengaplikasian Tree"),
        Task("Linear Algebra, Eigen", 2,
            GregorianCalendar(2021, Calendar.APRIL, 20, 10, 30),
            "Kerjakan soal"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_task_list, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.task_recycler_view)
        sortTask()
        val taskAdapter = TaskAdapter(tasks)
        recyclerView.adapter = taskAdapter
        return root
    }

    private fun sortTask(){
        tasks.sortWith(Comparator {first: Task, second: Task ->
            if(first.priority != second.priority){
                first.priority - second.priority
            }
            else {
                (first.deadline.time.time - second.deadline.time.time).toInt()
            }
        })
    }
}