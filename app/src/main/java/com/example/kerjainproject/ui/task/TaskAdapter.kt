package com.example.kerjainproject.ui.task

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kerjainproject.R
import com.example.kerjainproject.TaskDetailActivity

class TaskAdapter(private val dataset: Array<Task>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(view: View, dataset: Array<Task>) : RecyclerView.ViewHolder(view){
        val topicTextView: TextView
        val deadlineTextView: TextView
        val doneButton: Button

        init {
            topicTextView = view.findViewById(R.id.task_topic)
            deadlineTextView = view.findViewById(R.id.deadline)
            doneButton = view.findViewById(R.id.done_btn)

            view.setOnClickListener(View.OnClickListener {
                val intent = Intent(it.context, TaskDetailActivity::class.java)
                intent.putExtra("topic", dataset[adapterPosition].topic)
                intent.putExtra("deadline", dataset[adapterPosition].deadlineToString())
                intent.putExtra("description", dataset[adapterPosition].description)
                it.context.startActivity(intent)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list_content, parent, false)
        return ViewHolder(view, dataset)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.topicTextView.text = dataset[position].topic
        holder.deadlineTextView.text = dataset[position].deadlineToString()
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}