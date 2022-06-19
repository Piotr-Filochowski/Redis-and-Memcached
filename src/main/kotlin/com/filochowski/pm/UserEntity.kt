package com.filochowski.pm

import org.springframework.data.redis.core.RedisHash
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.LocalDate
import java.util.*

// Id,Name,Short description,Gender,Country,Occupation,Birth year,Death year,Manner of death,Age of death

@RedisHash("user")
data class UserEntity(
    val id: String,
    val csvId: String,
    val name: String,
    val description: String,
    val gender: String,
    val country: String,
    val occupation: String,
    val birthYear: LocalDate,
    val deathYear: LocalDate,
    val mannerOfDeath: String,
    val ageOfDeath: Int
) {

    companion object {
        @JvmStatic
        fun fromCreateUserRequest(request: CreateUserRequestDto) = UserEntity(
            id = UUID.randomUUID().toString(),
            csvId = request.csvId,
            name = request.name,
            description = request.description,
            gender = request.gender,
            country = request.country,
            occupation = request.occupation,
            birthYear = request.birthYear,
            deathYear = request.deathYear,
            mannerOfDeath = request.mannerOfDeath,
            ageOfDeath = request.ageOfDeath
        )
    }
}

class ResponseDto(val data: Collection<UserEntity>)

class CreateUserRequestDto(
    val csvId: String,
    val name: String,
    val description: String,
    val gender: String,
    val country: String,
    val occupation: String,
    val birthYear: LocalDate,
    val deathYear: LocalDate,
    val mannerOfDeath: String,
    val ageOfDeath: Int
)

class CreatedResponseDto(val id: String)


data class UserDto(
    val id: String,
    val csvId: String,
    val name: String,
    val description: String,
    val gender: String,
    val country: String,
    val occupation: String,
    val birthYear: LocalDate,
    val deathYear: LocalDate,
    val mannerOfDeath: String,
    val ageOfDeath: Int
) {
    companion object {
        @JvmStatic
        fun fromEntity(entity: UserEntity) = UserDto(
            id = entity.id,
            csvId = entity.csvId,
            name = entity.name,
            description = entity.description,
            gender = entity.gender,
            country = entity.country,
            occupation = entity.occupation,
            birthYear = entity.birthYear,
            deathYear = entity.deathYear,
            mannerOfDeath = entity.mannerOfDeath,
            ageOfDeath = entity.ageOfDeath
        )
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(message: String?) : RuntimeException(message)