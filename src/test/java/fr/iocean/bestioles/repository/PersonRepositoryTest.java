package fr.iocean.bestioles.repository;

import fr.iocean.bestioles.entity.Animal;
import fr.iocean.bestioles.entity.Person;
import fr.iocean.bestioles.entity.Species;
import fr.iocean.bestioles.enums.Sex;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Cette annotation remplace complètement les deux autres et permet de se passer d'écrire une Datasource H2 !
//@DataJpaTest
@SpringBootTest
@Transactional
public class PersonRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void initData() {
        System.out.println("Before Each");

        em.clear();
        Person p2 = new Person();
        p2.setFirstName("James");
        p2.setLastName("Tour");
        p2.setAge(56);
        em.persist(p2);
        Person p3 = new Person();
        p3.setFirstName("Toto");
        p3.setLastName("Toto");
        p3.setAge(12);
        em.persist(p3);

        Person p4 = new Person();
        p4.setFirstName("Toto");
        p4.setLastName("Tour");
        p4.setAge(77);
        em.persist(p4);

        em.flush(); // on flush pour forcer l'entityManager à exécuter ses opérations
    }

    @Test
    public void findByLastNameOrFirstNameTest() {
        List<Person> list = this.personRepository.findByLastNameOrFirstName("Tour", "Toto");
        Assertions.assertEquals(3, list.size());

        list = this.personRepository.findByLastNameOrFirstName("Toto", null);
        Assertions.assertEquals(1, list.size());

        list = this.personRepository.findByLastNameOrFirstName(null, null);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void findByAgeGreaterThanEqualTest() {
        List<Person> test = this.personRepository.findByAgeGreaterThanEqual(56);
        Assertions.assertEquals(2, test.size());
    }

    @Test
    public void findPersonWhereAgeBetweenTest() {
        List<Person> test = this.personRepository.findPersonWhereAgeBetween(12, 76);
        Assertions.assertEquals(2, test.size());
    }



    @Test
    public void findOwnersOfAnimalTest() {
        // Ajoute un animal lié à une personne
        Species species = new Species();
        species.setCommonName("Perroquet");
        species.setLatinName("Psittaciformes");
        em.persist(species);

        Animal a = new Animal();
        a.setColor("Bleu");
        a.setName("P");
        a.setSex(Sex.F);
        a.setSpecies(species);
        em.persist(a);
        Set<Animal> listAnimal = new HashSet<>();
        System.out.println("ID="+a.getId());
        listAnimal.add(a);

        Person p5 = new Person();
        p5.setFirstName("Jacques");
        p5.setLastName("Adi");
        p5.setAge(77);
        p5.setAnimals(listAnimal);
        em.persist(p5);

        List<Person> test = this.personRepository.findOwnersOfAnimal(a);
        Assertions.assertEquals(1, test.size());
        Assertions.assertEquals(test.get(0).getFirstName(), "Jacques");
        Assertions.assertEquals(test.get(0).getLastName(), "Adi");
    }

    @Test
    public void generateRandomPersonsFakerTest() {
        int totalBefore = personRepository.findAll().size();
        personRepository.generateRandomPersonsFaker(62);
        Assertions.assertEquals(totalBefore + 62,  personRepository.findAll().size());
    }

    @Test
    public void deletePersonsWithoutAnimalTest() {
        // Aucune personne avec Animal dans le jeu de données
        personRepository.deletePersonsWithoutAnimal();
        Assertions.assertEquals(0, personRepository.findAll().size());
    }

}
