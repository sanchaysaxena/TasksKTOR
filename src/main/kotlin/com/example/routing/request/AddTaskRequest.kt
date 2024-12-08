package com.example.routing.request

import com.example.data.model.Priority
import kotlinx.serialization.Serializable

@Serializable
data class AddTaskRequest(
    val name:String,
    val description:String,
    var priority: String
)
