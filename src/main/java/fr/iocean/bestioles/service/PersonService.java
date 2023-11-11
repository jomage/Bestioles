package fr.iocean.bestioles.service;

import fr.iocean.bestioles.dto.PersonDto;
import fr.iocean.bestioles.dto.PersonMapper;
import fr.iocean.bestioles.entity.Person;
import fr.iocean.bestioles.exception.EntityToCreateHasAnIdException;
import fr.iocean.bestioles.exception.EntityToUpdateHasNoIdException;
import fr.iocean.bestioles.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<PersonDto> findAll() {
        return personRepository.findAll()
                .stream()
                .map((person) -> personMapper.toDto(person))
                .collect(Collectors.toList());
    }

    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Page<Person> findByLastnameOrFirstname(String lastname, String firstname, Pageable pageable) {
        return personRepository.findByLastnameOrFirstname(lastname, firstname, pageable);
    }

    public PersonDto update(@Valid Person updatedPerson) {
        if (updatedPerson.getId() == null) {
            throw new EntityToUpdateHasNoIdException();
        }

        return personMapper.toDto(personRepository.save(updatedPerson));
    }

    public PersonDto create(@Valid Person updatedPerson) {
        if (updatedPerson == null || updatedPerson.getId() != null) {
            throw new EntityToCreateHasAnIdException();
        }
        return personMapper.toDto(personRepository.save(updatedPerson));
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
