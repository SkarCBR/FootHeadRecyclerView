package com.mrskar.samples.model

import androidx.annotation.AnimRes
import androidx.annotation.DrawableRes
import com.mrskar.samples.R

data class BounceAnimationModel (
    override var text: String,
    @AnimRes var animation: Int,
    @DrawableRes override var background: Int? = R.drawable.content_bg
): FootHeadItemContract