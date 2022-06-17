package com.filochowski.pm

import org.apache.catalina.User
import org.springframework.stereotype.Component

@Component
class UserService(val repo: UserRepo) {

    fun findAll() = repo.findAll().toMutableSet()

    fun save(request: CreateUserRequestDto) = repo.save(UserEntity.fromCreateUserRequest(request)).id
    fun findById(userId: String): UserEntity {
        var optUser = repo.findById(userId)
        if(optUser.isEmpty) {
            throw UserNotFoundException("User with id $userId not found")
        } else return optUser.get()
    }
}