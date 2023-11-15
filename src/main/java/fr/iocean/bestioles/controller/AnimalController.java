package fr.iocean.bestioles.controller;

import fr.iocean.bestioles.dto.AnimalDto;
import fr.iocean.bestioles.entity.Animal;
import fr.iocean.bestioles.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("{id}")
    public Animal findById(@PathVariable Integer id) {
        return animalService.findById(id);
    }

    @GetMapping
    public List<AnimalDto> findAll() {
        return animalService.findAll();
    }

    @GetMapping("page")
    public Page<AnimalDto> findAllPage(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "25") int size
    ) {
        return animalService.findAll(
                PageRequest.of(pageNumber, size)
        );
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
