package com.mrskar.observerpattern

class EventsObserver(tracker: EventsTracker): Observer {

    private var eventName: String = ""
    private var pairs: List<Pair<String, String>>? = null

    private var observerIDTracker = 0
    private val observerID: Int

    init {
        observerID = observerIDTracker++
        tracker.register(this)
    }

    //Here is were we will do whatever we want with the event (ex. Firebase)
    override fun update(eventName: String, pairsList: List<Pair<String, String>>?) {
        this.eventName = eventName
        this.pairs = pairsList
    }
}