package com.example.todo.ui.main

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel(activity: Activity) : ViewModel() {
    private val repository: TaskRepository
    init {
        val taskDao = TaskDatabase.getDatabase(activity).taskDao()
        repository = TaskRepository(taskDao)
    }
    val projects : LiveData<List<Project>> = repository.getProjects.asLiveData()
    val tasks : Flow<List<ProjectTasks>> get() = repository.getTasks




    fun addNewProject(project: Project) = viewModelScope.launch { repository.insertNewProject(project) }


}

