package fr.iocean.bestioles.repository;

import fr.iocean.bestioles.entity.Animal;
import fr.iocean.bestioles.entity.Species;
import fr.iocean.bestioles.enums.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findAllByNameContainingIgnoreCase(String fragment);
}
