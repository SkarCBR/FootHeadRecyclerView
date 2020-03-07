package com.mrskar.samples.data.repository

import com.mrskar.samples.data.sources.PipedreamDS

class PipeDreamRepo constructor(
    val pipedreamDS: PipedreamDS
) {
    suspend fun getPipeDreamTestRequest() {
        return pipedreamDS.testRequest()
    }
}