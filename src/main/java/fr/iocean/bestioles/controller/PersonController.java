package fr.iocean.bestioles.controller;

import fr.iocean.bestioles.dto.PersonDto;
import fr.iocean.bestioles.entity.Person;
import fr.iocean.bestioles.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("plante")
    public PersonDto getToto() {
        return personService.create(null);
    }

    @GetMapping("{id}") // localhost:8080/rest/person/{id}
    public Person findById(@PathVariable Integer id) {
        return personService.findById(id);
    }

    /**
     * Exemple avec une gestion du ResponseEntity "à la main"
     */
    @GetMapping("{id}/") // localhost:8080/rest/person/{id}/
    public ResponseEntity<?> findById2(@PathVariable Integer id) {
        Person person;
        try {
            person = personService.findById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Pas d'entité avec cette ID");
        }
        return ResponseEntity.ok(person);
    }

    /**
     * Méthode bonus "search" avec critères optionnels et utilisation de la Criterias API de JPA
     */
    @GetMapping("search")
    public List<Person> search(
            @RequestParam(value = "firstname", required = false) String firstname,
            @RequestParam(value = "lastname", required = false) String lastname,
            @RequestParam(value = "age", required = false) Integer age
    ) {
        return personService.search(firstname, lastname, age);
    }

    @GetMapping
    public List<PersonDto> findAll() {
        return personService.findAll();
    }

    @GetMapping("page")
    public Page<Person> findAll(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "25") int size
    ) {
        return personService.findAll(
                PageRequest.of(pageNumber, size)
        );
    }

    @PostMapping
    public PersonDto createPerson(@RequestBody @Valid Person personToCreate) {
        return personService.create(personToCreate);
    }

    @PutMapping("{id}") // pUt = Update
    public PersonDto updatePerson(@PathVariable Integer id, @RequestBody @Valid Person personToUpdate) {
        return personService.update(personToUpdate);
    }

    @DeleteMapping("{id}")
    public void deletePerson(@PathVariable Integer id) {
        personService.deleteById(id);
    }
}
