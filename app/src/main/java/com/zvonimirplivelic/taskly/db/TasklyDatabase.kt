package com.zvonimirplivelic.taskly.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zvonimirplivelic.taskly.db.dao.TasklyDao
import com.zvonimirplivelic.taskly.db.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
public abstract class TasklyDatabase : RoomDatabase() {

    abstract fun taskDao(): TasklyDao

    companion object {
        @Volatile
        private var INSTANCE: TasklyDatabase? = null

        fun getDatabase(context: Context): TasklyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TasklyDatabase::class.java,
                    "taskly_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}