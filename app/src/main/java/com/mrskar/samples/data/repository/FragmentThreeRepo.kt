package com.mrskar.samples.data.repository

import com.mrskar.samples.data.model.TestModel
import com.mrskar.samples.data.sources.FragmentThreeDS

class FragmentThreeRepo {
    private val fragmentThreeDS = FragmentThreeDS()
    fun getPipeDreamTestRequest(): TestModel {
        return fragmentThreeDS.testRequest()
    }
}