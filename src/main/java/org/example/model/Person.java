package org.example.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int idPerson;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String fullName;
    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private int yearBirthday;

    public Person() {
    }

    public Person(String fullName, int yearBirthday) {
        this.fullName = fullName;
        this.yearBirthday = yearBirthday;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearBirthday() {
        return yearBirthday;
    }

    public void setYearBirthday(int yearBirthday) {
        this.yearBirthday = yearBirthday;
    }
}
