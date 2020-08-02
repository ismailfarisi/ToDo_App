package com.example.todo

import android.content.Context
import android.media.audiofx.Equalizer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.core.internal.deps.guava.base.Equivalence
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todo.data.Project
import com.example.todo.data.TaskDao
import com.example.todo.data.TaskDatabase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dao : TaskDao
    private lateinit var database: TaskDatabase

    @Before
    fun createdb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context,TaskDatabase::class.java).build()
        dao = database.taskDao()
    }
    @After
    fun closedb(){
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun writedata_readdata(){

        val project = Project(1,"first","my first project")
        runBlocking { dao.insertNewProject(project)
            val byName = dao.getProjects()
            byName.collect {
                assertThat(it.first(),equalTo(project))
            }
        }
    }
}