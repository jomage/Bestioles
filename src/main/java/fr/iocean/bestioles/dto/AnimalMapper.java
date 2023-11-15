package fr.iocean.bestioles.dto;

import fr.iocean.bestioles.entity.Animal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnimalMapper {
    public AnimalDto toDto(Animal animal) {
        AnimalDto dto = new AnimalDto();
        dto.setId(animal.getId());
        dto.setColor(animal.getColor());
        dto.setName(animal.getName());
        dto.setSex(animal.getSex());
        dto.setSpecies(animal.getSpecies().getCommonName());

        String personList = animal.getPersons()
                .stream()
                .map((person) -> person.getLastname() + " " + person.getFirstname())
                .collect(Collectors.joining(","));

        dto.setPersons(personList);
        return dto;
    }
}
