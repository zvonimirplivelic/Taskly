package com.zvonimirplivelic.taskly

import androidx.lifecycle.LiveData
import com.zvonimirplivelic.taskly.db.dao.TasklyDao
import com.zvonimirplivelic.taskly.db.model.Task

class TasklyRepository(private val tasklyDao: TasklyDao) {
    val getAllTasks: LiveData<List<Task>> = tasklyDao.getAllTasks()

    suspend fun addTask(task: Task) {
        tasklyDao.addTask(task)
    }
}