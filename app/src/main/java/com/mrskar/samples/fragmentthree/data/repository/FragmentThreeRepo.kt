package com.mrskar.samples.fragmentthree.data.repository

import com.mrskar.samples.fragmentthree.data.model.TestModel
import com.mrskar.samples.fragmentthree.data.sources.FragmentThreeDS

class FragmentThreeRepo {
    private val fragmentThreeDS = FragmentThreeDS()
    fun getPipeDreamTestRequest(): TestModel {
        return fragmentThreeDS.testRequest()
    }
}