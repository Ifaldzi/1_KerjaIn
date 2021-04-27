package com.example.kerjainproject

import android.app.Application
import com.example.kerjainproject.database.TaskRepository
import com.example.kerjainproject.database.TaskRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class KerjainApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { TaskRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}