package com.tschumacher.system.com.tschumacher.system.services

import com.tschumacher.system.com.tschumacher.system.entities.Event
import com.tschumacher.system.com.tschumacher.system.repository.EventRepository
import org.springframework.stereotype.Service


@Service
class EventService(private val eventRepository: EventRepository) {

    fun logEvent(type: String, data: String): Event {
        val event = Event(type = type, data = data, timestamp = System.currentTimeMillis())
        return eventRepository.save(event)
    }

    fun getAllEvents(): List<Event> = eventRepository.findAll()

    // todo: find by type isn't working currently
    fun getEventsByType(type: String): List<Event> {
        return eventRepository.findAll()
    }
}