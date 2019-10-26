package com.mrskar.observerpattern

interface Subject {
    fun register(newObserver: Observer)
    fun unregister(newObserver: Observer)
    fun notifyObservers()
}