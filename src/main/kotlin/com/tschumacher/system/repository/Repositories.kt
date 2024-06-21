package com.tschumacher.system.com.tschumacher.system.repository


import com.tschumacher.system.com.tschumacher.system.entities.Event
import com.tschumacher.system.com.tschumacher.system.entities.UrlMapping
import com.tschumacher.system.com.tschumacher.system.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>


@Repository
interface UrlMappingRepository : JpaRepository<UrlMapping, Long> {
    fun findByShortUrl(shortUrl: String): UrlMapping?
}


@Repository
interface EventRepository : JpaRepository<Event, Long>
