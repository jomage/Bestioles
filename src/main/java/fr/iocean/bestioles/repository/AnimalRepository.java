package fr.iocean.bestioles.repository;

import fr.iocean.bestioles.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findAllByNameContainingIgnoreCase(String fragment);
    Boolean existsBySpeciesId(Integer speciesId);
}
