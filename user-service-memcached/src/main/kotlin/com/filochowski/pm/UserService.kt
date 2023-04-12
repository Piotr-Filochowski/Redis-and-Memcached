package com.filochowski.pm

import org.springframework.stereotype.Component
import java.util.*

@Component
class UserService(val repo: UserRepo) {

    fun findAll() = repo.findAll().toMutableSet()

    fun save(request: CreateUserRequestDto) = repo.save(UserEntity.fromCreateUserRequest(request)).id

    fun findById(userId: String): UserEntity {
        val optUser = repo.findById(userId)
        if(optUser.isEmpty) {
            throw UserNotFoundException("User with id $userId not found")
        } else return optUser.get()
    }
}