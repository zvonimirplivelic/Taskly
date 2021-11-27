package com.zvonimirplivelic.taskly.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zvonimirplivelic.taskly.db.model.Task

@Dao
interface TasklyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    fun deleteAllTasks()

    @Query("SELECT * FROM task_table WHERE taskName LIKE :taskNameSearchQuery")
    fun searchTaskByName(taskNameSearchQuery: String): LiveData<List<Task>>

    @Query("SELECT * FROM task_table ORDER BY taskId ASC")
    fun getAllTasks(): LiveData<List<Task>>
}