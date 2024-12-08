package com.example.service

import com.example.data.model.Priority
import com.example.data.model.Task
import com.example.repository.TasksRepository
import java.util.*

class TaskService(private val tasksRepository: TasksRepository) {

    fun getAllTasks():List<Task> = tasksRepository.getAllTasks()

    fun getTaskById(id: String): Task? = tasksRepository.getTaskById(id=UUID.fromString(id))

    fun getTaskByPriority(priority: Priority) = tasksRepository.taskByPriority(priority)

    fun addTask(task: Task):Task?{
        if(getTaskById(task.id.toString())==null){
            tasksRepository.addTask(task)
            return task
        }
        else return null
    }

    fun deleteTask(id: String):Task?{
        val taskToDelete:Task?=getTaskById(id)
        if(taskToDelete!=null){
            tasksRepository.deleteTask(taskToDelete)
            return taskToDelete
        }
        else return null
    }

}