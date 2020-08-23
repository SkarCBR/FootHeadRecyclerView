package com.mrskar.samples.googlemaps.data.repository

import com.mrskar.samples.googlemaps.data.model.TestModel
import com.mrskar.samples.googlemaps.data.sources.FragmentThreeDS

class FragmentThreeRepo {
    private val fragmentThreeDS = FragmentThreeDS()
    fun getPipeDreamTestRequest(): TestModel {
        return fragmentThreeDS.testRequest()
    }
}