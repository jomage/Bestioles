package fr.iocean.bestioles.service;

import fr.iocean.bestioles.dto.AnimalDto;
import fr.iocean.bestioles.dto.AnimalMapper;
import fr.iocean.bestioles.entity.Animal;
import fr.iocean.bestioles.exception.EntityToCreateHasAnIdException;
import fr.iocean.bestioles.exception.EntityToUpdateHasNoIdException;
import fr.iocean.bestioles.repository.AnimalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<AnimalDto> findAll() {
        return animalRepository.findAll()
                .stream()
                .map((animal) -> animalMapper.toDto(animal))
                .toList();
    }

    public Page<AnimalDto> findAll(Pageable pageable) {
        return animalRepository.findAll(pageable)
                .map((animal) -> animalMapper.toDto(animal));
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

    public Animal create(@Valid Animal animalToCreaet) {
        if (animalToCreaet.getId() != null) {
            throw new EntityToCreateHasAnIdException();
        }

        return animalRepository.save(animalToCreaet);
    }

    public void deleteById(Integer id) {
        if (!animalRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        animalRepository.deleteById(id);
    }
}
