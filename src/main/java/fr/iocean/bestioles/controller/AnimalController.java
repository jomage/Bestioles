package fr.iocean.bestioles.controller;

import fr.iocean.bestioles.entity.Animal;
import fr.iocean.bestioles.repository.AnimalRepository;
import fr.iocean.bestioles.repository.SpeciesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
//Request Mapping permet de préfixer tous les mappings par la valeur donnée
@RequestMapping("/animal")
public class AnimalController {
    
    @Autowired
    private AnimalRepository animalRepository;
    
    @Autowired
    private SpeciesRepository speciesRepository;
    
    // correspond à localhost:8080/animal
    @GetMapping
    public String listAnimal(Model model) {
        List<Animal> animalList = this.animalRepository.findAll();
        model.addAttribute("animalList", animalList);
        return "animal/list_animal";
    }

    // correspond à localhost:8080/animal/4
    @GetMapping("/{id}")
    public String getAnimalById(@PathVariable("id") Integer id , Model model) {
        Optional<Animal> animalOpt = this.animalRepository.findById(id);
        if (animalOpt.isPresent()) {
            model.addAttribute("allSpecies", this.speciesRepository.findAll());
            model.addAttribute(animalOpt.get());
            return "animal/update_animal";
        }
        return "error";
    }
    
    // correspond à localhost:8080/animal/create
    @GetMapping("/create")
    public String getAnimalCreate(Model model) {
        model.addAttribute(new Animal());
        model.addAttribute("allSpecies", this.speciesRepository.findAll());
        return "animal/create_animal";
    }

    @PostMapping
    public String createOrUpdate(@Valid Animal animal, BindingResult result, Model model) {
        if (result.hasErrors()) {;
            model.addAttribute("allSpecies", this.speciesRepository.findAll());
            if (animal.getId() != null) {
                return "animal/update_animal";
            }
            return "animal/create_animal";
        }
        
        this.animalRepository.save(animal);
        return "redirect:/animal";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer animalId, RedirectAttributes ra) {
        Optional<Animal> animalToDelete = this.animalRepository.findById(animalId);
        animalToDelete.ifPresent((a) -> this.animalRepository.delete(a));
        return "redirect:/animal";
    }
    
    
}
