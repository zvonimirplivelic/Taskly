package com.zvonimirplivelic.taskly.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.zvonimirplivelic.taskly.repository.TasklyRepository
import com.zvonimirplivelic.taskly.db.TasklyDatabase
import com.zvonimirplivelic.taskly.db.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasklyViewModel(application: Application) : AndroidViewModel(application) {
    val getAllTasks: LiveData<List<Task>>
    private val repository: TasklyRepository

    init {
        val tasklyDao = TasklyDatabase.getDatabase(application).taskDao()
        repository = TasklyRepository(tasklyDao)
        getAllTasks = repository.getAllTasks
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }
}