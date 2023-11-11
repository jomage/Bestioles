package fr.iocean.bestioles.service;

import fr.iocean.bestioles.entity.Species;
import fr.iocean.bestioles.exception.EntityToCreateHasAnIdException;
import fr.iocean.bestioles.exception.EntityToUpdateHasNoIdException;
import fr.iocean.bestioles.repository.SpeciesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {

    @Autowired
    SpeciesRepository speciesRepository;

    public Species findById(Integer id) {
        return speciesRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Species> findAll() {
        return speciesRepository.findAll();
    }

    public Page<Species> findAll(Pageable pageable) {
        return speciesRepository.findAll(pageable);
    }

    public Species update(@Valid Species speciesToUpdate) {
        if (speciesToUpdate.getId() == null) {
            throw new EntityToUpdateHasNoIdException();
        }
        if (!speciesRepository.existsById(speciesToUpdate.getId())) {
            throw new EntityNotFoundException();
        }

        return speciesRepository.save(speciesToUpdate);
    }

    public Species create(@Valid Species speciesToCreate) {
        if (speciesToCreate.getId() != null) {
            throw new EntityToCreateHasAnIdException();
        }

        return speciesRepository.save(speciesToCreate);
    }

    public void deleteById(Integer id) {
        if (!speciesRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        speciesRepository.deleteById(id);
    }

}
