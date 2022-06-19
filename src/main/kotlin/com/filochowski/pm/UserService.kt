package com.filochowski.pm

import org.apache.catalina.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.util.*
import java.util.stream.Stream

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