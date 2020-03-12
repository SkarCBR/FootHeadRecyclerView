package com.mrskar.samples.data.sources

import com.mrskar.samples.data.model.TestModel
import kotlin.random.Random

class FragmentThreeDS {
    fun testRequest(): TestModel {
        return TestModel(Random.nextInt(),"Some stupid name like Patata", listOf("1","2","3"))
    }
}