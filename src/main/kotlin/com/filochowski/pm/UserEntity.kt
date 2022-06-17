package com.filochowski.pm

import org.springframework.data.redis.core.RedisHash
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@RedisHash("user")
data class UserEntity(
    val id: String,
    val name: String,
    val age: Int
) {

    companion object {
        @JvmStatic
        fun fromCreateUserRequest(request: CreateUserRequestDto) = UserEntity(
            id = UUID.randomUUID().toString(),
            name = request.name,
            age = request.age
        )
    }
}

class ResponseDto(val data: Collection<UserEntity>)

class CreateUserRequestDto(
    val name: String,
    val age: Int
)

class CreatedResponseDto(val id: String)


data class UserDto(
    val id: String,
    val name: String,
    val age: Int
) {
    companion object {
        @JvmStatic
        fun fromEntity(entity: UserEntity) = UserDto(
            id = entity.id,
            name = entity.name,
            age = entity.age
        )
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(message: String?) : RuntimeException(message)