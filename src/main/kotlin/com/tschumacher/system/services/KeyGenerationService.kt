package com.tschumacher.system.com.tschumacher.system.services

interface KeyGenerationService {
    fun generateKey(length: Int): String
}