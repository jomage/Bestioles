package fr.iocean.bestioles.dto;

import fr.iocean.bestioles.entity.Animal;
import org.springframework.stereotype.Component;

@Component
public class AnimalSimpleMapper {
    public AnimalSimpleDto toSimpleDto(Animal animal) {
        AnimalSimpleDto dto = new AnimalSimpleDto();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        return dto;
    }
}
