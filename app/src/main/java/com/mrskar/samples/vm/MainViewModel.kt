package com.mrskar.samples.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrskar.samples.R
import com.mrskar.samples.model.AnimationModel
import com.mrskar.samples.model.ContentModel
import com.mrskar.samples.model.FootHeadItemContract
import com.mrskar.samples.model.FooterModel
import com.mrskar.samples.model.HeaderModel

class MainViewModel: ViewModel() {

    private val items = createList()

    private val itemsLiveData = MutableLiveData<List<FootHeadItemContract>>()

    init {
        itemsLiveData.value = items
    }

    fun getItemsLiveData(): LiveData<List<FootHeadItemContract>> = itemsLiveData

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
}