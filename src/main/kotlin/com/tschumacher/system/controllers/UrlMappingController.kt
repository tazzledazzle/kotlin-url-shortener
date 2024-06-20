package com.tschumacher.system.com.tschumacher.system.controllers

import com.tschumacher.system.com.tschumacher.system.services.UrlMappingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView


@RestController
@RequestMapping("/api/urls")
class UrlMappingController(private val urlMappingService: UrlMappingService) {

    @GetMapping("/{shortUrl}")
    fun redirect(@PathVariable shortUrl: String): RedirectView {
        val targetUrl = urlMappingService.getTargetUrl(shortUrl)
        return if (targetUrl != null) {
            RedirectView(targetUrl)
        } else {
            RedirectView("/error")
        }
    }

    @PostMapping
    fun createShortUrl(@RequestParam shortUrl: String, @RequestParam targetUrl: String): ResponseEntity<Any> {
        val urlMapping = urlMappingService.createShortUrl(shortUrl, targetUrl)
        return ResponseEntity.ok(urlMapping)
    }
}
