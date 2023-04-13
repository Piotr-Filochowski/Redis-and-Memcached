package com.filochowski.pm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserServiceRedisApplication

fun main(args: Array<String>) {
    runApplication<UserServiceRedisApplication>(*args)
}
