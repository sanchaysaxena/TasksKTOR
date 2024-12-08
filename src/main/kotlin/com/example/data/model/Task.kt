package com.example.data.model

import kotlinx.serialization.Serializable
import java.util.UUID

data class Task(
    val id:UUID,
    val name:String,
    val description:String,
    val priority: Priority
)
@Serializable
enum class Priority{
    Low,
    Medium,
    High,
    Vital
}
