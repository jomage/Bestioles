package fr.iocean.bestioles.service;

import fr.iocean.bestioles.dto.AnimalDto;
import fr.iocean.bestioles.dto.AnimalMapper;
import fr.iocean.bestioles.entity.Animal;
import fr.iocean.bestioles.exception.EntityToCreateHasAnIdException;
import fr.iocean.bestioles.exception.EntityToUpdateHasNoIdException;
import fr.iocean.bestioles.repository.AnimalRepository;
import fr.iocean.bestioles.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    AnimalMapper animalMapper;

    public Animal findById(Integer id) {
        return animalRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<AnimalDto> findAll(String fragment) {
        List<Animal> listToReturn;
        if (fragment != null && !fragment.isEmpty()) {
            listToReturn = animalRepository.findAllByNameContainingIgnoreCase(fragment);
        } else {
            listToReturn = animalRepository.findAll();
        }

        return listToReturn
                .stream()
                .map(animalMapper::toDto)
                .toList();
    }

    public Animal update(@Valid Animal animalToUpdate) {
        if (animalToUpdate.getId() == null) {
            throw new EntityToUpdateHasNoIdException();
        }
        if (!animalRepository.existsById(animalToUpdate.getId())) {
            throw new EntityNotFoundException();
        }

        return animalRepository.save(animalToUpdate);
    }

    public Animal create(@Valid Animal animalToCreate) {
        if (animalToCreate.getId() != null) {
            throw new EntityToCreateHasAnIdException();
        }

        return animalRepository.save(animalToCreate);
    }

    public void deleteById(Integer id) {
        if (!animalRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        animalRepository.deleteById(id);
    }
}
