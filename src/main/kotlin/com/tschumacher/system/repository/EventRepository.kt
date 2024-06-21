package com.tschumacher.system.com.tschumacher.system.repository

import com.tschumacher.system.com.tschumacher.system.entities.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : JpaRepository<Event, Long> {
    fun findByType(type: String): List<Event>
}