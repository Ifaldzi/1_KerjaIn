package com.example.kerjainproject.ui.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kerjainproject.R

class TaskAdapter(private val dataset: Array<Task>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val topicTextView: TextView
        val deadlineTextView: TextView
        val doneButton: Button

        init {
            topicTextView = view.findViewById(R.id.task_topic)
            deadlineTextView = view.findViewById(R.id.deadline)
            doneButton = view.findViewById(R.id.done_btn)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.topicTextView.text = dataset[position].topic
        holder.deadlineTextView.text = dataset[position].deadlineToString()
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}