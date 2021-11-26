package com.zvonimirplivelic.taskly.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zvonimirplivelic.taskly.db.model.Task

@Dao
interface TasklyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM task_table ORDER BY taskId ASC")
    fun getAllTasks(): LiveData<List<Task>>
}