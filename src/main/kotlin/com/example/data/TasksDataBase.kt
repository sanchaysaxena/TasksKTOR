package com.example.data

import com.example.data.model.Task
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.toList
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import java.util.UUID





//
//suspend fun getTaskById(id:UUID):Task? = tasks.findOneById(id.toString())
//
//suspend fun addTask(task: Task):Boolean= tasks.insertOne(task).wasAcknowledged()
//
//suspend fun getAllTasks():List<Task> =  tasks.find().toList()