package com.tschumacher.system.com.tschumacher.system.services

import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service
class KeyGenerationServiceImpl : KeyGenerationService {
    private val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    private val random = SecureRandom()

    override fun generateKey(length: Int): String {
        val key = StringBuilder(length)
        for (i in 0 until length) {
            val index = random.nextInt(characters.length)
            key.append(characters[index])
        }
        return key.toString()
    }
}
