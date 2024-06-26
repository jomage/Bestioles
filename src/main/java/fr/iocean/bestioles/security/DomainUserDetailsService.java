package fr.iocean.bestioles.security;

import fr.iocean.bestioles.entity.Authority;
import fr.iocean.bestioles.entity.User;
import fr.iocean.bestioles.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implémente l'interface UserDetailsService de Spring, nécéssaire à Spring Security
 * pour authentifier l'utilisateur depuis la BDD.
 *
 * C'est cette classe qui va "faire le lien" entre nos tables User / Authorithy et
 * Spring Security.
 */
@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    @Autowired
    UserRepository userRepository;

    /**
     * Crée et retourne un UserDetails (Spring Security) à partir de nos tables User/Authority.
     *
     * @param username le login de l'utilisateur à aller chercher
     * @return un objet UserDetails contenant les infos de l'utilisateur pour Spring Security
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Authenticating {}", username);
        return createSpringSecurityUser(userRepository.findFirstByLogin(username));
    }

    /**
     * Convertit un User (notre table) en User au sens Spring Security.
     */
    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User myUser) {
        // Récupération des authorities de notre User et convertion en GrantedAuthority pour
        // les mettre dans le User Spring
        List<GrantedAuthority> grantedAuthorities = myUser.getAuthorities().stream()
                .map(Authority::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                myUser.getLogin(),
                myUser.getPassword(),
                grantedAuthorities);
    }
}
