package com.filochowski.pm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class UserServiceMemcachedApplication

fun main(args: Array<String>) {
    runApplication<UserServiceMemcachedApplication>(*args)
}
