package com.filochowski.pm

import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: CrudRepository<UserEntity, String> {
    @Cacheable(value = ["userByCsvId"])
    fun findByCsvId(csvId: String): UserEntity?
}