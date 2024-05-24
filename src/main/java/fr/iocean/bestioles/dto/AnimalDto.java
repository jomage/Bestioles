package fr.iocean.bestioles.dto;

import fr.iocean.bestioles.entity.Species;
import fr.iocean.bestioles.enums.Sex;

import java.util.List;

/**
 * DTO utilisé pour la recherche d'animaux.
 */
public class AnimalDto {
    private Integer id;
    private String name;
    private String color;
    private Sex sex;
    private Species species;
    private List<PersonSimpleDto> persons;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public List<PersonSimpleDto> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonSimpleDto> persons) {
        this.persons = persons;
    }
}
