package fr.iocean.bestioles.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    @Size(max = 50)
    @NotBlank
    private String commonName;

    @Column(length = 200)
    @Size(max = 200)
    @NotBlank
    private String latinName;

    @OneToMany(mappedBy = "species")
    private List<Animal> animals;

    @Override
    public String toString() {
        return "Species{" +
                "id=" + id +
                ", commonName='" + commonName + '\'' +
                ", latinName='" + latinName + '\'' +
                '}';
    }

    // Getters / setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }


    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
