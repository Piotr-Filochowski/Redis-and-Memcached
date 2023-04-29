package com.filochowski.pm;

import java.io.Serializable;
import java.time.LocalDate;

public class UserDto implements Serializable {
   private String id;
   private String csvId;
   private String name;
   private String description;
   private String gender;
   private String country;
   private String occupation;
   private LocalDate birthYear;
   private LocalDate deathYear;
   private String mannerOfDeath;
   private Integer ageOfDeath;

    public UserDto(
            String id,
            String csvId,
            String name,
            String description,
            String gender,
            String country,
            String occupation,
            LocalDate birthYear,
            LocalDate deathYear,
            String mannerOfDeath,
            Integer ageOfDeath) {
        this.id = id;
        this.csvId = csvId;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.country = country;
        this.occupation = occupation;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.mannerOfDeath = mannerOfDeath;
        this.ageOfDeath = ageOfDeath;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCsvId() {
        return csvId;
    }

    public void setCsvId(String csvId) {
        this.csvId = csvId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public LocalDate getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(LocalDate birthYear) {
        this.birthYear = birthYear;
    }

    public LocalDate getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(LocalDate deathYear) {
        this.deathYear = deathYear;
    }

    public String getMannerOfDeath() {
        return mannerOfDeath;
    }

    public void setMannerOfDeath(String mannerOfDeath) {
        this.mannerOfDeath = mannerOfDeath;
    }

    public Integer getAgeOfDeath() {
        return ageOfDeath;
    }

    public void setAgeOfDeath(Integer ageOfDeath) {
        this.ageOfDeath = ageOfDeath;
    }
}
