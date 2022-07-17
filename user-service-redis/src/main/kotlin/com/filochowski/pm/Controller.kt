package com.filochowski.pm

import io.micrometer.core.annotation.Timed
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class Controller(
    val userService: UserService,
    val csvService: CsvLoaderService) {


    @Timed()
    @GetMapping
    fun getAll() = ResponseDto(userService.findAll())

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody request: CreateUserRequestDto) = CreatedResponseDto(userService.save(request))

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: String) = UserDto.fromEntity(userService.findById(userId))

    @GetMapping("/load-csv")
    fun loadCsv() = csvService.loadCsv("/users_dataset.csv", true)
}