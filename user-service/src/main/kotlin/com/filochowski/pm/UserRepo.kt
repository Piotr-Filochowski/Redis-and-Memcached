package com.filochowski.pm

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: CrudRepository<UserEntity, String> {
    fun findByCsvId(csvId: String): List<UserEntity>
}