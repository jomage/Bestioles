package fr.iocean.bestioles.dto;

import fr.iocean.bestioles.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonMapper {
    @Autowired
    AnimalSimpleMapper animalSimpleMapper;

    public PersonDto toDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setAge(person.getAge());
        dto.setFirstName(person.getFirstname());
        dto.setLastName(person.getLastname());
        if (person.getAnimals() != null) {
            List<AnimalSimpleDto> animalList = person.getAnimals()
                    .stream()
                    .map(animalSimpleMapper::toSimpleDto)
                    .toList();

            dto.setAnimals(animalList);
        }
        return dto;
    }
}
