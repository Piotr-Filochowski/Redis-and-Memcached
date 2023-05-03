package com.filochowski.pm

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class UserService(val repo: UserRepo) {

    fun findAll(): List<UserEntity> = repo.findAll().toList()

    fun finByCsvId(csvId: String): UserEntity {
        logger.info("Querying db for $csvId")
        return repo.findByCsvId(csvId) ?: throw UserNotFoundException("User with id $csvId not found")
    }

    fun save(request: CreateUserRequestDto) = repo.save(UserEntity.fromCreateUserRequest(request)).id

    fun findById(userId: String): UserEntity {
        val optUser = repo.findById(userId)
        if(optUser.isEmpty) {
            throw UserNotFoundException("User with id $userId not found")
        } else return optUser.get()
    }

    fun count() = repo.count()
    fun deleteAll() = repo.deleteAll()

    companion object {
        val logger = LoggerFactory.getLogger(UserService::class.java)
    }
}