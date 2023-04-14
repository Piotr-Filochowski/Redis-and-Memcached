package com.filochowski.pm

import io.micrometer.core.annotation.Timed
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/user")
class Controller(
    val userService: UserService,
    val csvService: CsvLoaderService) {

    @Timed()
    @GetMapping
    fun getAll() = ResponseDto(userService.findAll())

    @GetMapping("/findAll")
    fun findAll() = userService.findAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody request: CreateUserRequestDto) = CreatedResponseDto(userService.save(request))

    @Cacheable(value = ["userByCsvId"], key="#p0")
    @GetMapping("/{csvId}")
    fun getUser(@PathVariable("csvId") csvId: String) = UserDto.fromEntity(userService.finByCsvId(csvId))

    @GetMapping("/load-csv")
    fun loadCsv(@RequestParam pathToFile: String) = csvService.loadCsv(pathToFile, true)

    @GetMapping("/load-csv-file")
    fun loadCsvFile(@RequestPart file: MultipartFile) = csvService.loadFromFile(file, true)
}