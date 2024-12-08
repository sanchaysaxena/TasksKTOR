package com.example.routing.request

import com.example.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class DeleteTaskRequest(
    @Serializable(with = UUIDSerializer::class)
    val id:UUID
)
