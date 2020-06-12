package com.mrskar.samples.vm

sealed class ViewStates<T> {
    class Loading<T> : ViewStates<T>()
    class Success<T>(val data: T) : ViewStates<T>()
    class Error<T>(val message: String, val data: T? = null) : ViewStates<T>()
    class EmptySuccess<T>(val message: String = "", val data: T? = null) : ViewStates<T>()
}
