package com.mrskar.samples.data.sources

import com.mrskar.samples.data.model.TestModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
class PipedreamDS {

    suspend fun testRequest() {
        val httpClient = HttpClient(CIO)
        return httpClient.use { client ->
            client.get<HttpResponse>("https://enl1s9wdn77zj.x.pipedream.net") {
                header("Content-Type", "application/json")
                body = TestModel(0,"test", listOf("patata","test"))
            }
        }
    }
}