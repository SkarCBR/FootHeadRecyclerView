package com.mrskar.samples.model

import androidx.annotation.DrawableRes
import com.mrskar.samples.R

data class HeaderModel (
    override var text: String,
    @DrawableRes override var background: Int? = R.color.white
): FootHeadItemContract