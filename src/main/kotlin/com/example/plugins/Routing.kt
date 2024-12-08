package com.example.plugins

import com.example.routing.tasksRoute
import com.example.service.TaskService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    taskService: TaskService
) {
    routing {
        route("/api/tasks") {
            tasksRoute(taskService)
        }
    }
}
