package com.mrskar.observerpattern

interface Observer {
    fun update(eventName: String, pairsList: List<Pair<String, String>>?)
}