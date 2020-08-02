package com.example.todo.data

import kotlinx.coroutines.flow.Flow

class TaskRepository (private val taskDao: TaskDao){
    val getTasks : Flow<List<ProjectTasks>> = taskDao.getTasks()

    val getProjects : Flow<List<Project>> get() = taskDao.getProjects()

    suspend fun insertNewProject(project: Project) {
        taskDao.insertNewProject(project)
    }




}