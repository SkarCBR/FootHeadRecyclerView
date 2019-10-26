package com.mrskar.observerpattern

// aka RecoTracker
class EventsTracker : Subject {

    private var observers: MutableList<Observer> = mutableListOf()
    private var eventName: String = ""
    private var pairsList: List<Pair<String, String>>? = null

    override fun register(newObserver: Observer) {
        observers.add(newObserver)
    }

    override fun unregister(newObserver: Observer) {
        observers.remove(newObserver)
    }

    override fun notifyObservers() {
        observers.forEach {
            it.update(eventName, pairsList)
        }
    }

    //When we need to track a Reco event
    fun onNewEvent(name: String, pairs: List<Pair<String, String>>?) {
        eventName = name
        pairsList = pairs
        notifyObservers()
    }
}
