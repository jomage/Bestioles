package fr.iocean.bestioles.controller;

import fr.iocean.bestioles.dto.PersonDto;
import fr.iocean.bestioles.entity.Person;
import fr.iocean.bestioles.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
//@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT,RequestMethod.GET})
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("{id}")
    public Person findById(@PathVariable Integer id) {
        return personService.findById(id);
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
    public List<PersonDto> findAll(@RequestParam(value = "contains", required = false) String fragment) {
        return personService.findAll(fragment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody @Valid Person personToCreate) {
        return personService.create(personToCreate);
    }

    @PutMapping("{id}")
    public Person updatePerson(@PathVariable Integer id, @RequestBody @Valid Person personToUpdate) {
        return personService.update(personToUpdate);
    }

    @DeleteMapping("{id}")
    public void deletePerson(@PathVariable Integer id) {
        personService.deleteById(id);
    }
}
