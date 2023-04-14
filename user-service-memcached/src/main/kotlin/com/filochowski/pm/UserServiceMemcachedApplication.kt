package com.filochowski.pm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserServiceMemcachedApplication

fun main(args: Array<String>) {
    runApplication<UserServiceMemcachedApplication>(*args)
}
