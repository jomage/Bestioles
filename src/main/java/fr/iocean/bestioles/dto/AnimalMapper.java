package fr.iocean.bestioles.dto;

import fr.iocean.bestioles.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimalMapper {
    @Autowired
    PersonSimpleMapper personSimpleMapper;

    public AnimalDto toDto(Animal animal) {
        AnimalDto dto = new AnimalDto();
        dto.setId(animal.getId());
        dto.setColor(animal.getColor());
        dto.setName(animal.getName());
        dto.setSex(animal.getSex());
        dto.setSpecies(animal.getSpecies());

        List<PersonSimpleDto> personList = animal.getPersons()
                .stream()
                .map(personSimpleMapper::toSimpleDto)
                .toList();

        dto.setPersons(personList);
        return dto;
    }
}
