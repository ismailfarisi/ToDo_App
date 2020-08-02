package com.example.todo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewProject(project: Project)

    @Transaction
    @Query("SELECT * FROM Project")
    fun getTasks(): Flow<List<ProjectTasks>>

    @Query("select * from project")
    fun getProjects():Flow<List<Project>>



}

