package com.example.todo.data

import androidx.room.*
import java.sql.Date
import java.sql.Timestamp

@Entity
data class Project (
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    val projectName : String,
    val projectDescription : String? = null
)

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)val taskId : Int,
    val taskName : String,
    val projectId : Int
)

data class  ProjectTasks(
    @Embedded val project : Project,
    @Relation(
        parentColumn = "id",
        entityColumn = "projectId"
    )val tasks : Task
)