package com.filochowski.pm

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.LocalDate
import java.util.*


@Component
class CsvLoaderService(val repo: UserRepo) {


    fun loadCsv(pathString: String, deleteAll: Boolean = false) {
        var savedUserCounter = 0
        if(deleteAll) {
            repo.deleteAll()
            log.info("Deleted all elements")
        }
        val fr = FileReader(File(pathString))

        val br = BufferedReader(fr)
        var line: String = br.readLine() // skipping one line
        while(true) {
            line = br.readLine()
            if(line == null) {
                break;
            }
            mapAndSave(line)
            savedUserCounter++
            if(savedUserCounter % 100 == 0) {
                log.info("saved user ${savedUserCounter}")
            }
        }


    }
    
    private fun mapAndSave(line: String) {
        val split = line.split(",")
        if(split.size != 10 ) {
            log.warn("invalid line: $line. Size: ${split.size}")
            return
        }
        val user = UserEntity(
            id = UUID.randomUUID().toString(),
            csvId = split[0],
            name = split[1],
            description = split[2],
            gender = split[3],
            country = split[4],
            occupation = split[5],
            birthYear = parseDate(split[6]),
            deathYear = parseDate(split[7]),
            mannerOfDeath = split[8],
            ageOfDeath = parseAgeOfDeath(split[9])
        )
        repo.save(user)
    }

    private fun parseAgeOfDeath(s: String): Int {
        if(s.isBlank()) {
            return -1
        } else return Integer.parseInt(s)
    }

    private fun parseDate(deathYear: String): LocalDate {
        if(deathYear.isBlank()) {
            return LocalDate.MIN
        }
        return LocalDate.of(Integer.parseInt(deathYear), 6, 15)
    }

    companion object {
        private val log = LoggerFactory.getLogger(CsvLoaderService::class.java)
    }
}