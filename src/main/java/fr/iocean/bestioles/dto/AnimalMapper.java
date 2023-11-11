package fr.iocean.bestioles.dto;

import fr.iocean.bestioles.entity.Animal;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {
    public AnimalDto toDto(Animal animal) {
        AnimalDto dto = new AnimalDto();
        dto.setId(animal.getId());
        dto.setColor(animal.getColor());
        dto.setName(animal.getName());
        dto.setSex(animal.getSex());
        return dto;
    }
}
