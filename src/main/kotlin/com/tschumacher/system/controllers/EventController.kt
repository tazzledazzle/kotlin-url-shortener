package com.tschumacher.system.com.tschumacher.system.controllers

import com.tschumacher.system.com.tschumacher.system.entities.Event
import com.tschumacher.system.com.tschumacher.system.services.EventService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/events")
class EventController(private val eventService: EventService) {

    @PostMapping
    fun logEvent(@RequestParam type: String, @RequestParam data: String): ResponseEntity<Event> {
        val event = eventService.logEvent(type, data)
        return ResponseEntity.ok(event)
    }

    @GetMapping
    fun getAllEvents(): ResponseEntity<List<Event>> {
        val events = eventService.getAllEvents()
        return ResponseEntity.ok(events)
    }

    @GetMapping("/type/{type}")
    fun getEventsByType(@PathVariable type: String): ResponseEntity<List<Event>> {
        val events = eventService.getEventsByType(type)
        return ResponseEntity.ok(events)
    }
}
