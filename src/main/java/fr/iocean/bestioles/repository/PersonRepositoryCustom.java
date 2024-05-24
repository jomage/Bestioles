package fr.iocean.bestioles.repository;

import fr.iocean.bestioles.entity.Person;

import java.util.List;

public interface PersonRepositoryCustom {
    int deletePersonsWithoutAnimal();
    int deletePersonsWithoutAnimalMySQL();

    List<Person> generateRandomPersonsFaker(int numberToCreate);

    List<Person> testCriterias(String firstname, String lastname, Integer age);
}
