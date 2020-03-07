package com.mrskar.samples.presentation.model

import androidx.annotation.DrawableRes
import com.mrskar.samples.R

data class FooterModel (
    override var text: String,
    @DrawableRes override var background: Int? = R.color.white
): FootHeadItemContract