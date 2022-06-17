package com.filochowski.pm

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class Controller(val service: UserService) {


    @GetMapping
    fun getAll() = ResponseDto(service.findAll())

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody request: CreateUserRequestDto) = CreatedResponseDto(service.save(request))

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: String) = UserDto.fromEntity(service.findById(userId))
}