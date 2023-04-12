package com.filochowski.pm

import com.opencsv.CSVReader
import com.opencsv.exceptions.CsvValidationException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.FileReader
import java.io.IOException
import java.time.LocalDate
import java.util.*


@Component
class CsvLoaderService(private val repo: UserRepo) {


    fun loadCsv(pathString: String, deleteAll: Boolean = false) {
        var savedUserCounter: Long = 0
        var invalidLinesCounter: Long = 0
        if(deleteAll) {
            repo.deleteAll()
            log.info("Deleted all elements")
        }
        val reader = CSVReader(FileReader(pathString))
        var nextLine: Array<String>? = emptyArray()
        while (true) {
            try {
                nextLine = reader.readNext()
                mapAndSave(nextLine)
                savedUserCounter++
                log.info("Saved line ${printNumberNicely(savedUserCounter)}")
            } catch(exception: IOException) {
                invalidLinesCounter++
                log.warn("Read invalid line ${printNumberNicely(invalidLinesCounter)} ${exception.message} $nextLine")
            } catch(exception: CsvValidationException) {
                invalidLinesCounter++
                log.warn("Read invalid line ${printNumberNicely(invalidLinesCounter)} ${exception.message} $nextLine")
            } catch(exception: ArrayIndexOutOfBoundsException) {
                log.warn("Read invalid line ${printNumberNicely(invalidLinesCounter)} ${exception.message} $nextLine")
            }
        }

    }

    private fun mapAndSave(split: Array<String>) {
        val user = UserEntity()
        user.id = UUID.randomUUID().toString()
        user.csvId = split[0]
        user.name = split[1]
        user.description = split[2]
        user.gender = split[3]
        user.country = split[4]
        user.occupation = split[5]
        user.birthYear = parseDate(split[6])
        user.deathYear = parseDate(split[7])
        user.mannerOfDeath = split[8]
        user.ageOfDeath = parseAgeOfDeath(split[9])
        repo.save(user)
    }

    private fun parseAgeOfDeath(s: String): Int? {
        try {
            if (s.isBlank()) {
                return null
            } else return Integer.parseInt(s)
        } catch (exception: Exception) {
            log.warn("Not parsable age of death")
            return null
        }
    }

    private fun parseDate(deathYear: String): LocalDate? {
        try {
            if (deathYear.isBlank()) {
                return null
            }
            return LocalDate.of(Integer.parseInt(deathYear), 6, 15)
        } catch (exception: Exception) {
            log.warn("Not parsable date")
            return null
        }
    }

    private fun printNumberNicely(long: Long) = String.format(Locale.US, "%,d", long).replace(',', ' ')


    companion object {
        private val log = LoggerFactory.getLogger(CsvLoaderService::class.java)

    }
}