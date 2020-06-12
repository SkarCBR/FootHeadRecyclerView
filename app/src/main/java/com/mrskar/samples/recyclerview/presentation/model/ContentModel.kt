package com.mrskar.samples.recyclerview.presentation.model

import androidx.annotation.DrawableRes
import com.mrskar.samples.R

data class ContentModel (
    override var text: String,
    var description: String,
    @DrawableRes override var background: Int? = R.drawable.content_bg
): FootHeadItemContract