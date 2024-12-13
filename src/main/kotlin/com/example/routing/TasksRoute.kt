package com.example.routing

import com.example.data.model.Priority
import com.example.data.model.Task
import com.example.routing.request.AddTaskRequest
import com.example.routing.response.TaskResponse
import com.example.service.TaskService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.tasksRoute(taskService: TaskService){
    post{
        val addTaskRequest=call.receive<AddTaskRequest>()
        addTaskRequest.priority=addTaskRequest.priority.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        if(addTaskRequest.priority!="Low" && addTaskRequest.priority!="Medium" && addTaskRequest.priority!="High" && addTaskRequest.priority!="Vital"){
            return@post call.respondText("Priority Value is incorrect", status = HttpStatusCode.BadRequest)
        }
        val newTask=taskService.addTask(
            task = addTaskRequest.toModel()
        )?:return@post call.respond(HttpStatusCode.BadRequest)

        call.response.header(
            name = "id",
            value = newTask.id.toString()
        )

        call.respondText("New ${newTask.priority} priority task added Successfully - ${newTask.id} - ${newTask.name} - ${newTask.description}", status = HttpStatusCode.Created)
    }
    get {
        val tasks = taskService.getAllTasks()
        call.respond(message = tasks.map(Task::toResponse), status = HttpStatusCode.OK)
    }
    delete("/{id}") {
        val id: String = call.parameters["id"]
            ?: return@delete call.respond(HttpStatusCode.BadRequest)
        val taskToDelete=taskService.deleteTask(id)
        call.respondText("Task with ID ${id} deleted Successfully", status = HttpStatusCode.OK)
    }
    get("byPriority/{priority}"){
        val priorityAsText= call.parameters["priority"]?.lowercase()?.capitalize()
            ?:return@get call.respond(HttpStatusCode.BadRequest)
        if(priorityAsText!="Low" && priorityAsText!="Medium" && priorityAsText!="High" && priorityAsText!="Vital"){
            return@get call.respondText("Priority Value is incorrect", status = HttpStatusCode.BadRequest)
        }
        try {
            val prior = Priority.valueOf(priorityAsText)
            val tasks = taskService.getTaskByPriority(prior)
            if (tasks.isEmpty()) return@get call.respondText(
                "No task found for given priority",
                status = HttpStatusCode.NotFound
            )

            call.respond(message = tasks.map(Task::toResponse))
        }
        catch (ex:IllegalArgumentException){
            call.respond(HttpStatusCode.BadRequest)
        }
    }

}

private fun AddTaskRequest.toModel(): Task =Task(
        id = UUID.randomUUID(),
        name = this.name,
        description = this.description,
        priority=Priority.valueOf(this.priority)
    )

private fun Task.toResponse():TaskResponse=
    TaskResponse(
        id=this.id,
        name = this.name,
        description=this.description,
        priority=this.priority
    )