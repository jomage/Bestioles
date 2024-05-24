package fr.iocean.bestioles.repository;

import fr.iocean.bestioles.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    List<Species> findAllByLatinNameContainingIgnoreCaseOrCommonNameContainingIgnoreCase(
            String LatinNamefragment, String CommonNamefragment);
}
