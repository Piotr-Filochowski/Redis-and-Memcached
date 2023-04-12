package com.filochowski.pm

import io.micrometer.core.annotation.Timed
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/user")
class Controller(
    val userService: UserService,
    val csvService: CsvLoaderService) {


    @Timed()
    @GetMapping
    fun getAll() = ResponseDto(userService.findAll())

    @GetMapping("/fuck")
    fun fuck() = userService.findAll().stream().map { it.id }.collect(Collectors.toList())

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody request: CreateUserRequestDto) = CreatedResponseDto(userService.save(request))

    @GetMapping("/{userId}")
    fun getUser(@PathVariable("userId") userId: String) = UserDto.fromEntity(userService.findById(userId))

    @GetMapping("/load-csv")
    fun loadCsv(@RequestParam pathToFile: String) = csvService.loadCsv(pathToFile, true)
}