package fr.iocean.bestioles.dto;

/**
 * DTO utilisé dans le dto de la recherche de personnes PersonDto
 */
public class AnimalSimpleDto {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
