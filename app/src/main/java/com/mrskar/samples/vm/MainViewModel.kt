package com.mrskar.samples.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrskar.samples.R
import com.mrskar.samples.fragmentthree.data.model.TestModel
import com.mrskar.samples.fragmentthree.data.repository.FragmentThreeRepo
import com.mrskar.samples.recyclerview.presentation.model.AnimationModel
import com.mrskar.samples.recyclerview.presentation.model.ContentModel
import com.mrskar.samples.recyclerview.presentation.model.FootHeadItemContract
import com.mrskar.samples.recyclerview.presentation.model.FooterModel
import com.mrskar.samples.recyclerview.presentation.model.HeaderModel

class MainViewModel: ViewModel() {

    private val fragmentThreeRepo = FragmentThreeRepo()
    private val items = createList()

    private val itemsLiveData = MutableLiveData<List<FootHeadItemContract>>()
    private val testModelLiveData = MutableLiveData<TestModel>()

    init {
        itemsLiveData.value = items
    }

    fun getItemsLiveData(): LiveData<List<FootHeadItemContract>> = itemsLiveData
    fun getTestModelLiveData(): LiveData<TestModel> = testModelLiveData

    private fun createList(): List<FootHeadItemContract> {
        val list = mutableListOf<FootHeadItemContract>()
        for (i in 0..10) {
            when (i) {
                0 -> list.add(HeaderModel("Titulo"))
                1 -> list.add(AnimationModel("Bounce Animation", R.anim.interpolator_bounce))
                10 -> list.add(FooterModel("by Oscar Olivella"))
                else -> list.add(ContentModel("Contenido $i", "Prueba con el contenido estatico"))
            }
        }
        return list
    }

    fun requestTestData() {
        testModelLiveData.postValue(fragmentThreeRepo.getPipeDreamTestRequest())
    }
}