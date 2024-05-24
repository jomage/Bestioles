package fr.iocean.bestioles.repository;

import fr.iocean.bestioles.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findFirstByLogin(String login);
}
