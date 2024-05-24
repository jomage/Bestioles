package fr.iocean.bestioles.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    @NotBlank
    @Size(max = 50)
    private String firstname;

    @Column(length = 50)
    @NotBlank
    @Size(max = 50)
    private String lastname;

    @Min(0)
    @Max(120)
    private Integer age;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})//(fetch = FetchType.EAGER)
    @JoinTable(
            name = "person_animals",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "animals_id")
    )
    @JsonIgnoreProperties("persons")
    Set<Animal> animals;

    // Utility

//    public void addAnimal(Animal animal) {
//        this.animals.add(animal);
//    }
//
//    public void removeAnimal(Animal animal) {
//        this.animals.remove(animal);
//    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", animals=" + animals +
                '}';
    }

    // Getters / setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }
}
