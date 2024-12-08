package com.example.routing.response

import com.example.data.model.Priority
import com.example.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class TaskResponse(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name:String,
    val description:String,
    val priority: Priority
)