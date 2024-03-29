package com.filochowski.pm

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception
import java.time.LocalDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

// Id,Name,Short description,Gender,Country,Occupation,Birth year,Death year,Manner of death,Age of death


@Entity
@Table(name = "users")
open class UserEntity {
    @Id
    open var id: String= ""

    @Column
    var csvId: String = ""

    @Column
    var name: String = ""

    @Column
    var description: String = ""

    @Column
    var gender: String = ""

    @Column
    var country: String = ""

    @Column
    var occupation: String = ""

    @Column
    var birthYear: LocalDate? = null

    @Column
    var deathYear: LocalDate? = null

    @Column
    var mannerOfDeath: String = ""

    @Column
    var ageOfDeath: Int? = 0

    companion object {
        @JvmStatic
        fun fromCreateUserRequest(request: CreateUserRequestDto): UserEntity {
            val entity = UserEntity()
            entity.id = UUID.randomUUID().toString()
            entity.csvId = request.csvId
            entity.name = request.name
            entity.description = request.description
            entity.gender = request.gender
            entity.country = request.country
            entity.occupation = request.occupation
            entity.birthYear = parseLocalDate(request.birthYear)
            entity.deathYear = parseLocalDate(request.deathYear)
            entity.mannerOfDeath = request.mannerOfDeath
            entity.ageOfDeath = request.ageOfDeath
            return entity
        }

        private fun parseLocalDate(yearString: String): LocalDate? {
            return try {
                val year = yearString.toInt()
                LocalDate.of(year, 1, 1)
            } catch (ex: Exception) {
                null
            }
        }
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
    val birthYear: String,
    val deathYear: String,
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
    val birthYear: Int?,
    val deathYear: Int?,
    val mannerOfDeath: String,
    val ageOfDeath: Int?
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
            birthYear = entity.birthYear?.year,
            deathYear = entity.deathYear?.year,
            mannerOfDeath = entity.mannerOfDeath,
            ageOfDeath = entity.ageOfDeath
        )
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(message: String?) : RuntimeException(message)