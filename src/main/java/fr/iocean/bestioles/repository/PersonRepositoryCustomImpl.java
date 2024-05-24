package fr.iocean.bestioles.repository;

import com.github.javafaker.Faker;
import fr.iocean.bestioles.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public int deletePersonsWithoutAnimal() {
        // Autres solutions

        // En JPQL - Simple mais plante sur MySQL ?
        // Query q = em.createQuery("DELETE FROM Person WHERE animals IS EMPTY");


        // En SQL
         Query sqlQuery = em.createNativeQuery(
                 "DELETE FROM person WHERE person.id NOT IN (" +
                 "  SELECT person_id FROM person_animals" +
                 ")");

        // Ne marche pas sur toutes les BDD (Notamment h2 pour les tests)
//        Query sqlQuery = em.createNativeQuery("delete p from person p left join person_animals pa on p.id = pa.person_id where pa.animals_id is null");
        return sqlQuery.executeUpdate();
    }

    /**
     * Pour "contrer" l'erreur avec MySQL quand on écrit en JPQL "DELETE FROM Person WHERE animals IS EMPTY"
     * @return
     */
    @Override
    public int deletePersonsWithoutAnimalMySQL() {
        List<Person> personToDelete = em.createQuery(
                "SELECT p FROM Person p WHERE p.animals IS EMPTY",
                Person.class
        ).getResultList();

        System.out.println("Personnes à supprimer : " + personToDelete);
        int i = 0;
        for (Person p : personToDelete) {
            em.remove(p);
            i++;
        }
        return i;
    }

    @Override
    public List<Person> generateRandomPersonsFaker(int count) {
        Faker faker = new Faker(Locale.FRANCE);
        List<Person> personList = new ArrayList<>();

        for (int i = 0 ; i < count ; i++) {
            Person newPerson = new Person();
            newPerson.setFirstName(faker.name().firstName());
            newPerson.setLastName(faker.name().lastName());
            newPerson.setAge(faker.number().numberBetween(0, 120));
            em.persist(newPerson);
            personList.add(newPerson);
        }

        return personList;
    }

    /**
     * Méthode d'exemple avec paramètres qui peuvent être null ("optionels)
     *
     * @param firstname le prénom à chercher dans les personnes
     * @param lastname le nom de famille à chercher dans les personnes
     * @param age l'age des personnes retournées
     * @return la liste des personnes qui correspondent aux critères fournis
     */
    @Override
    public List<Person> testCriterias(
            String firstname,
            String lastname,
            Integer age
    ) {
        // Utilisation de JPA Criterias
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        Root<Person> rootPerson = cq.from(Person.class);

        // Une liste de prédicats qui va être complétée selon les paramètres fournis
        // Paramètre null = on ne met pas dans la liste
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(firstname)) {
            predicates.add(cb.like(rootPerson.get("firstname"), "%" + firstname + "%"));
        }

        if (StringUtils.hasText(lastname)) {
            predicates.add(cb.like(rootPerson.get("lastname"), "%" + lastname + "%"));
        }

        if (age != null && age > 0) {
            predicates.add(cb.equal(rootPerson.get("age"), age));
        }

        // Utilisation de la liste de prédicats pour créer une clause "where"
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

}
