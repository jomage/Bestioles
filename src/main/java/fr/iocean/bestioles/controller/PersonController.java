package fr.iocean.bestioles.controller;


import fr.iocean.bestioles.entity.Person;
import fr.iocean.bestioles.repository.AnimalRepository;
import fr.iocean.bestioles.repository.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
//Request Mapping permet de préfixer tous les mappings par la valeur donnée
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private AnimalRepository animalRepository;
    
    @GetMapping
    public String listSpecies(Model model) {
        List<Person> personList = this.personRepository.findAll();
        model.addAttribute("personList", personList);
        return "person/list_person";
    }
    
    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") Integer id , Model model) {
        Optional<Person> personOpt = this.personRepository.findById(id);
        if (personOpt.isPresent()) {
            model.addAttribute("animalList", animalRepository.findAll());
            model.addAttribute("person", personOpt.get());
            return "person/update_person";
        }
        return "error";
    }
    
    @GetMapping("/create")
    public String getPersonCreate(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("animalList", animalRepository.findAll());
        return "person/create_person";
    }

    @PostMapping
    public String createOrUpdate(@Valid Person person, BindingResult bindingResult, Model model) {
        System.out.println("\n\nPERSONNE = " + person + "\n\n");
        if (bindingResult.hasErrors()) {
            if (person.getId() != null) {
                model.addAttribute("animalList", animalRepository.findAll());
                return "person/update_person";
            }
            return "person/create_person";
        }
        
        this.personRepository.save(person);
        return "redirect:/person";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer personId) {
        Optional<Person> personToDelete = this.personRepository.findById(personId);
        personToDelete.ifPresent((p) -> this.personRepository.delete(p));
        return "redirect:/person";
    }

    @GetMapping("delete-no-animal")
    public String deletePersonNoAnimal() {
        personRepository.deletePersonsWithoutAnimal();
        return "redirect:/person";
    }

    @GetMapping("generate")
    @Transactional
    public String generateRandomPersons(
            @RequestParam("nb") Integer numberToCreate
    ) {
        personRepository.generateRandomPersonsFaker(numberToCreate);
        return "redirect:/person";
    }
}
