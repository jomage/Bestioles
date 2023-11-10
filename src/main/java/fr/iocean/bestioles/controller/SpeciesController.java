package fr.iocean.bestioles.controller;

import fr.iocean.bestioles.entity.Species;
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

import java.util.List;
import java.util.Optional;

@Controller
//Request Mapping permet de préfixer tous les mappings par la valeur donnée
@RequestMapping("/species")
public class SpeciesController {
    
    @Autowired
    private SpeciesRepository speciesRepository;
    
    @GetMapping
    public String listSpecies(Model model) {
        List<Species> speciesList = this.speciesRepository.findAll();
        model.addAttribute("speciesList", speciesList);
        return "species/list_species";
    }
    
    @GetMapping("/{id}")
    public String getSpeciesById(@PathVariable("id") Integer id , Model model) {
        Optional<Species> speciesOptional = this.speciesRepository.findById(id);
        if (speciesOptional.isPresent()) {
            model.addAttribute("species", speciesOptional.get());
            return "species/update_species";
        }
        return "error";
    }
    
    @GetMapping("/create")
    public String getSpeciesCreate(Model model) {
        model.addAttribute(new Species());
        return "species/create_species";
    }
    
    @PostMapping
    public String createOrUpdate(@Valid Species species, BindingResult result, Model model) {
        if (result.hasErrors()) {
            if (species.getId() != null) {
                return "species/update_species";
            }
            return "species/create_species";
        }
        
        this.speciesRepository.save(species);
        return "redirect:/species";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer speciesId) {
        Optional<Species> speciesToDelete = this.speciesRepository.findById(speciesId);
        speciesToDelete.ifPresent((p) -> this.speciesRepository.delete(p));
        return "redirect:/species";
    }
    
}
