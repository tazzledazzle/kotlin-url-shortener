package com.tschumacher.system.com.tschumacher.system.controllers

import com.tschumacher.system.com.tschumacher.system.services.KeyGenerationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class KeyGenerationController(private val keyGenerationService: KeyGenerationService) {

    @GetMapping("/generateKey")
    fun generateKey(@RequestParam length: Int): String {
        return keyGenerationService.generateKey(length)
    }
}
