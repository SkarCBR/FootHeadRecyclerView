package com.mrskar.samples.model

import androidx.annotation.IntegerRes

data class ContentModel (
    override var text: String,
    var description: String,
    @IntegerRes override var background: Int? = null
): FootHeadItemContract