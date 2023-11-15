package fr.iocean.bestioles.dto;

import fr.iocean.bestioles.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public PersonDto toDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setAge(person.getAge());
        dto.setName(person.getLastname().toUpperCase() + " " + person.getFirstname());
        if (person.getAnimals() != null) {
            List<String> animalList = person.getAnimals()
                    .stream()
                    .map((animal) -> animal.getName() + " (" + animal.getSpecies().getCommonName() + ")")
                    .toList();

            dto.setAnimals(animalList);
        }
        return dto;
    }
}
