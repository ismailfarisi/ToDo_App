package com.example.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Task::class,Project::class),version = 1,exportSchema = false)
abstract class TaskDatabase : RoomDatabase(){

    abstract fun taskDao() : TaskDao

    companion object{
        @Volatile
        private var INSTANCE : TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "TaskDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}