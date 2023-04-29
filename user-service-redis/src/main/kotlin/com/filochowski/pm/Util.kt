package com.filochowski.pm
object Util {


    fun fromEntity(entity: UserEntity): UserDto {
        return UserDto(
                entity.id,
                entity.csvId,
                entity.name,
                entity.description,
                entity.gender,
                entity.country,
                entity.occupation,
                entity.birthYear,
                entity.deathYear,
                entity.mannerOfDeath,
                entity.ageOfDeath
        )
    }
}