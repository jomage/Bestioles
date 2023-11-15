package fr.iocean.bestioles.dto;

import java.util.List;

public class PersonDto {
    private Integer id;
    private Integer age;
    private String name;
    private List<String> animals;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAnimals() {
        return animals;
    }

    public void setAnimals(List<String> animals) {
        this.animals = animals;
    }
}
