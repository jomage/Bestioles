package fr.iocean.bestioles.dto;

import fr.iocean.bestioles.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonSimpleMapper {
    public PersonSimpleDto toSimpleDto(Person person) {
        PersonSimpleDto dto = new PersonSimpleDto();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstname());
        dto.setLastName(person.getLastname());
        return dto;
    }
}
