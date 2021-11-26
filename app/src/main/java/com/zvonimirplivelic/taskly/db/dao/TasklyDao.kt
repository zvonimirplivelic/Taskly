package com.zvonimirplivelic.taskly.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zvonimirplivelic.taskly.db.model.Task

@Dao
interface TasklyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    fun deleteAllTasks()

    @Query("SELECT * FROM task_table ORDER BY taskId ASC")
    fun getAllTasks(): LiveData<List<Task>>
}