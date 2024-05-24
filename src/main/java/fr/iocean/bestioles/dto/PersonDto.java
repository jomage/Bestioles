package fr.iocean.bestioles.dto;

import java.util.List;

public class PersonDto {
    private Integer id;
    private Integer age;
    private String firstName;
    private String lastName;
    private List<AnimalSimpleDto> animals;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<AnimalSimpleDto> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalSimpleDto> animals) {
        this.animals = animals;
    }
}
