package com.zvonimirplivelic.taskly.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int,
    val taskName: String,
    val taskDetails: String,
    val taskPriority: Int
)