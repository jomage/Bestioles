package fr.iocean.bestioles.service;

import fr.iocean.bestioles.dto.PersonDto;
import fr.iocean.bestioles.dto.PersonMapper;
import fr.iocean.bestioles.entity.Person;
import fr.iocean.bestioles.exception.EntityNotFoundException;
import fr.iocean.bestioles.exception.EntityToCreateHasAnIdException;
import fr.iocean.bestioles.exception.EntityToUpdateHasNoIdException;
import fr.iocean.bestioles.repository.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;

    public Person findById(Integer id) {
        return personRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<PersonDto> findAll(String fragment) {
        List<Person> listToReturn;
        if (fragment != null && !fragment.isEmpty()) {
            listToReturn = personRepository.findByLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(
                    fragment, fragment);
        } else {
            listToReturn = personRepository.findAll();
        }

        return listToReturn
                .stream()
                .map((person) -> personMapper.toDto(person))
                .collect(Collectors.toList());
    }

    public Person update(@Valid Person updatedPerson) {
        if (updatedPerson.getId() == null) {
            throw new EntityToUpdateHasNoIdException();
        }

        return personRepository.save(updatedPerson);
    }

    public Person create(@Valid Person updatedPerson) {
        if (updatedPerson != null && updatedPerson.getId() != null) {
            throw new EntityToCreateHasAnIdException();
        }
        return personRepository.save(updatedPerson);
    }

    public void deleteById(Integer id) {
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        personRepository.deleteById(id);
    }

    public List<Person> search(
            String firstname,
            String lastname,
            Integer age
    ) {
        return personRepository.testCriterias(firstname, lastname, age);
    }

}
