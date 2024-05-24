package fr.iocean.bestioles.controller;

import fr.iocean.bestioles.entity.Species;
import fr.iocean.bestioles.service.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/species")
public class SpeciesController {

    @Autowired
    private SpeciesService speciesService;

    @GetMapping("{id}")
    public Species findById(@PathVariable Integer id) {
        return speciesService.findById(id);
    }

    @GetMapping
    public List<Species> findAll(@RequestParam(value = "contains", required = false) String fragment) {
        return speciesService.findAll(fragment);
    }

    @PostMapping
    public Species create(@RequestBody @Valid Species speciesToUpdate) {
        return speciesService.create(speciesToUpdate);
    }

    @PutMapping("{id}")
    public Species update(@PathVariable Integer id, @RequestBody @Valid Species speciesToUpdate) {
        return speciesService.update(speciesToUpdate);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        speciesService.deleteById(id);
    }

}
