package com.mrskar.samples.model

import androidx.annotation.IntegerRes

data class FooterModel (
    override var text: String,
    @IntegerRes override var background: Int? = null
): FootHeadItemContract