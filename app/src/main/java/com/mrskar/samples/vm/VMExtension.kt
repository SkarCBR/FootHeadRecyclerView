package com.mrskar.samples.vm

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

fun <T : ViewModel> FragmentActivity?.getViewModelInstance(clazz: Class<T>): T  {
    return this?.run {
        ViewModelProviders.of(this)[(clazz)]
    } ?: throw Exception("Ivalid Activity")
}