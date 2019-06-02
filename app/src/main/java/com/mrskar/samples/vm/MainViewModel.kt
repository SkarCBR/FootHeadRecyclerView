package com.mrskar.samples.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrskar.samples.R
import com.mrskar.samples.model.BounceAnimationModel
import com.mrskar.samples.model.ContentModel
import com.mrskar.samples.model.FootHeadItemContract
import com.mrskar.samples.model.FooterModel
import com.mrskar.samples.model.HeaderModel

class MainViewModel: ViewModel() {

    private val items = listOf(
        HeaderModel("Titulo 1"),
        BounceAnimationModel("Bounce Animation", R.anim.interpolator_bounce),
        ContentModel("Contenido 1", "Prueba con el contenido estatico"),
        ContentModel("Contenido 2", "Prueba con el contenido estatico", R.drawable.alternate_content_bg),
        ContentModel("Contenido 3", "Prueba con el contenido estatico"),
        ContentModel("Contenido 4", "Prueba con el contenido estatico", R.drawable.alternate_content_bg),
        ContentModel("Contenido 5", "Prueba con el contenido estatico"),
        ContentModel("Contenido 6", "Prueba con el contenido estatico", R.drawable.alternate_content_bg),
        ContentModel("Contenido 7", "Prueba con el contenido estatico"),
        ContentModel("Contenido 8", "Prueba con el contenido estatico", R.drawable.alternate_content_bg),
        ContentModel("Contenido 9", "Prueba con el contenido estatico"),
        ContentModel("Contenido 10", "Prueba con el contenido estatico", R.drawable.alternate_content_bg),
        FooterModel("by Oscar Olivella")
    )

    private val itemsLiveData = MutableLiveData<List<FootHeadItemContract>>()

    init {
        itemsLiveData.value = items
    }

    fun getItemsLiveData(): LiveData<List<FootHeadItemContract>> = itemsLiveData
}