package fr.iocean.bestioles.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

/**
 * Entité représentant une Authority au sens Spring Security.
 * Par défaut il y en a 2 : ROLE_ADMIN et ROLE_USER (ont été ajoutés à la main dans la base).
 */
@Entity
public class Authority {

    @NotNull
    @Id
    @Column(length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
