package com.mrskar.samples.presentation.model

import androidx.annotation.AnimRes
import androidx.annotation.DrawableRes
import com.mrskar.samples.R

data class AnimationModel (
    override var text: String,
    @AnimRes var animation: Int,
    @DrawableRes override var background: Int? = R.drawable.content_bg
): FootHeadItemContract