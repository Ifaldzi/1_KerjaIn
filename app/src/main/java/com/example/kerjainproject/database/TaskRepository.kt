package com.example.kerjainproject.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: Flow<List<Task>> = taskDao.getAll()
    val sortedTasks: Flow<List<Task>> = taskDao.getSortedTask()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(task: Task){
        taskDao.insert(task)
    }
}