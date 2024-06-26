package fr.iocean.bestioles.repository;

import fr.iocean.bestioles.entity.Animal;
import fr.iocean.bestioles.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, PersonRepositoryCustom {
    List<Person> findByLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(
            String lastNameFragment, String firstNameFragment);
    /**
     * Retourne les personnes ayant pour nom le premier paramètre fourni ou
     * ayant pour prénom le second paramètre fourni
     *
     * @param lastname  le nom de la Personne
     * @param firstname le prénom de la Personne
     */
    List<Person> findByLastNameOrFirstName(String lastname, String firstname);

    /**
     * Retourne toutes les personnes d’un âge supérieur ou égal au paramètre fourni
     *
     * @param ageMinimal l'age minimal des personnes renvoyées
     */
    List<Person> findByAgeGreaterThanEqual(Integer ageMinimal);

    /**
     * Retourne la liste des Personnes dont l’âge est entre « age min » et « age max ».
     *
     * @param ageMin l'age minimum requis par la Personne
     * @param ageMax l'age maximum requis par la Personne
     */
    @Query(value = "select p from Person p where p.age between :ageMin and :ageMax")
    List<Person> findPersonWhereAgeBetween(
            @Param("ageMin") Integer ageMin,
            @Param("ageMax") Integer ageMax);

    /**
     * Chercher toutes les Personnes qui possèdent l’animal donné en paramètre
     * SOLUTION 1 avec un inner join
     * @param animal l'animal que doivent 'posséder' les personnes retournées
     */
    @Query("select p from Person p inner join p.animals a where :animal = a")

    List<Person> findOwnersOfAnimal(@Param("animal") Animal animal);

}
