package com.example.repository

import com.example.data.model.Priority
import com.example.data.model.Task
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import java.util.UUID

class TasksRepository{
    private val tasksList= mutableListOf<Task>()
//    private val client= KMongo.createClient().coroutine
//    private val database= client.getDatabase("TasksDatabase")
//
//    private val tasks= database.getCollection<Task>()

    fun getAllTasks():List<Task> =  tasksList

    fun getTaskById(id:UUID):Task? = tasksList.firstOrNull{it.id==id}

    fun addTask(task: Task):Boolean= tasksList.add(task)

    fun deleteTask(task: Task):Boolean = tasksList.remove(task)

    fun taskByPriority(priority: Priority) = tasksList.filter {it.priority==priority}

//    suspend fun getTaskById(id:UUID):Task? = tasks.findOneById(id.toString())
//
//    suspend fun addTask(task: Task):Boolean= tasks.insertOne(task).wasAcknowledged()
//
//    suspend fun getAllTasks():List<Task> =  tasks.find().toList()
//
//    fun getDatabase(): MongoDatabase {
//        val client= MongoClient.create(connectionString = "mongodb+srv://Cluster46982:sanchay123@cluster46982.xnehh.mongodb.net/?retryWrites=true&w=majority&appName=Cluster46982")
//        return client.getDatabase(databaseName = "TasksDatabase")
//    }
}