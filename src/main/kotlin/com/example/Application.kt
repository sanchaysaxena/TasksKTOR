package com.example

import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.repository.TasksRepository
import com.example.service.TaskService
import io.ktor.server.application.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val tasksRepository=TasksRepository()
    val taskService=TaskService(tasksRepository)
//    val database=tasksRepository.getDatabase()

    configureSerialization()
    configureRouting(taskService)
}
