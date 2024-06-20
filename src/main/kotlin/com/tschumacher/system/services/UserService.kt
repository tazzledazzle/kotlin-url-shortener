package com.tschumacher.system.com.tschumacher.system.services

import com.tschumacher.system.com.tschumacher.system.entities.User
import com.tschumacher.system.com.tschumacher.system.repository.UserRepository
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: Long): User? = userRepository.findById(id).orElse(null)

    fun createUser(user: User): User = userRepository.save(user)

    fun updateUser(id: Long, user: User): User? {
        return if (userRepository.existsById(id)) {
            userRepository.save(user.copy(id = id))
        } else {
            null
        }
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}