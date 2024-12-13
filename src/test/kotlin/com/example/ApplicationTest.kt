package com.example

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import kotlin.test.assertEquals
import com.example.data.model.Priority
import com.example.data.model.Task
import com.example.routing.request.AddTaskRequest
import com.example.routing.response.TaskResponse
import io.ktor.client.call.*
import org.litote.kmongo.json
import java.util.*
import kotlin.test.*
class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

//    @Test
//    fun tasksCanBeFoundByPriority() = testApplication {
//        application {
//            module()
//        }
//        val client = createClient {
//            install(ContentNegotiation) {
//                json()
//            }
//        }
//        val response = client.get("/api/tasks/byPriority/Low")
//        val results = response.body<List<Task>>()
//
//        assertEquals(HttpStatusCode.OK, response.status)
//
//        val expectedTaskNames = listOf("gardening", "painting")
//        val actualTaskNames = results.map(Task::name)
//        assertContentEquals(expectedTaskNames, actualTaskNames)
//    }
    @Test
    fun newTasksCanBeAdded() = testApplication {
        application {
            module()
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val task = AddTaskRequest("swimming", "Go to the beach", "Low")
        val response1 = client.post("/api/tasks") {
            header(
                HttpHeaders.ContentType,
                ContentType.Application.Json
            )

            setBody(task)
        }
        assertEquals(HttpStatusCode.Created, response1.status)

        val response2 = client.get("/api/tasks")
        assertEquals(HttpStatusCode.OK, response2.status)

        val taskNames = response2
            .body<List<TaskResponse>>()
            .map { it.name }

        assertContains(taskNames, "swimming")
    }
}
