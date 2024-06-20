package com.tschumacher.system.com.tschumacher.system.services

import com.tschumacher.system.com.tschumacher.system.entities.UrlMapping
import com.tschumacher.system.com.tschumacher.system.repository.UrlMappingRepository
import org.springframework.stereotype.Service

@Service
class UrlMappingService(private val urlMappingRepository: UrlMappingRepository) {

    fun getTargetUrl(shortUrl: String): String? {
        val urlMapping = urlMappingRepository.findByShortUrl(shortUrl)
        return urlMapping?.targetUrl
    }

    fun createShortUrl(shortUrl: String, targetUrl: String): UrlMapping {
        val urlMapping = UrlMapping(shortUrl = shortUrl, targetUrl = targetUrl)
        return urlMappingRepository.save(urlMapping)
    }
}