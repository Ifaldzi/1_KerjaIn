package com.example.kerjainproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TaskRoomDatabase : RoomDatabase(){
    abstract fun taskDao(): TaskDao

    private class TaskDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var taskDao = database.taskDao()
                    taskDao.deleteAll()
                    var task = Task("Kalkulus", 2,
                        GregorianCalendar(2021, Calendar.APRIL, 24, 23, 59),
                        "Mantap")
                    taskDao.insert(task)
                    task = Task("PBO, Inheritance", 1,
                        GregorianCalendar(2021, Calendar.APRIL, 3, 23, 59),
                        "Tugas membuat aplikasi sederhana dengan konsep inheritance")
                    taskDao.insert(task)
                    task = Task( "B. Inggris, Introduction", 3,
                        GregorianCalendar(2021, Calendar.APRIL, 7, 17, 0),
                        "Make it Perfect")
                    taskDao.insert(task)
                    task = Task("DDP, Pseudocode", 1,
                        GregorianCalendar(2021, Calendar.APRIL, 5, 23, 59),
                        "Membuat pseudocode untuk sistem vending machine")
                    taskDao.insert(task)
                    task = Task("SDA, Tree", 1,
                        GregorianCalendar(2021, Calendar.APRIL, 10, 23, 59),
                        "Membuat pengaplikasian Tree")
                    taskDao.insert(task)
                    task = Task("Linear Algebra, Eigen", 2,
                        GregorianCalendar(2021, Calendar.APRIL, 20, 10, 30),
                        "Kerjakan soal")
                    taskDao.insert(task)
                }
            }
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TaskRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_database")
                    .addCallback(TaskDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}