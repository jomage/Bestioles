package fr.iocean.bestioles.repository;

import fr.iocean.bestioles.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    /**
     * Retourne la première Species dont le nom commun est égal au paramètre fourni
     * @param commonName nom commun de la Species à retourner
     */
    Species findFirstByCommonName(String commonName);

    /**
     * Retourne une liste de Species dont le nom latin contient le paramètre fourni en ignorant la casse
     * @param nameFragment Fragment du nom qui doit être contenu dans le nom latin de la Species
     */
    List<Species> findByLatinNameContainsIgnoreCase(String nameFragment);











    /**
     * Retourne une liste de toutes les Species, ordonnées par nom commun ascendant
     */
    @Query("select s from Species s order by s.commonName asc")
    List<Species> findSpeciesOrder();

    /**
     * Retourne les Species avec un nom commun LIKE le paramètre fourni
     */
    @Query("from Species where commonName LIKE :nomCommun")
    List<Species> findSpeciesWhereCommonNameLike(@Param("nomCommun") String nomCommun);
}
