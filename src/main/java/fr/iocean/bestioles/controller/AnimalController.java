package fr.iocean.bestioles.controller;

import fr.iocean.bestioles.dto.AnimalDto;
import fr.iocean.bestioles.entity.Animal;
import fr.iocean.bestioles.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/animal")
//@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT,RequestMethod.GET})
@CrossOrigin(origins = "http://localhost:4200")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("{id}")
    public Animal findById(@PathVariable Integer id) {
        return animalService.findById(id);
    }

    @GetMapping
    public List<AnimalDto> findAll(@RequestParam(value = "contains", required = false) String fragment) {
        return animalService.findAll(fragment);
    }

    @PostMapping
    public Animal create(@RequestBody @Valid Animal animalToUpdate) {
        return animalService.create(animalToUpdate);
    }

    @PutMapping("{id}")
    public Animal update(@PathVariable Integer id, @RequestBody @Valid Animal animalToUpdate) {
        return animalService.update(animalToUpdate);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        animalService.deleteById(id);
    }

}
