package com.example.kerjainproject.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("Select * FROM task")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM task ORDER BY priority ASC, deadline ASC")
    fun getSortedTask(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("DELETE FROM task")
    suspend fun deleteAll()
}