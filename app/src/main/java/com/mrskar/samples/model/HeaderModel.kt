package com.mrskar.samples.model

import androidx.annotation.IntegerRes

data class HeaderModel (
    override var text: String,
    @IntegerRes override var background: Int? = null
): FootHeadItemContract