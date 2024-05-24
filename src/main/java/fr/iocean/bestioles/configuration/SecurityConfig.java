package fr.iocean.bestioles.configuration;

import fr.iocean.bestioles.security.jwt.JWTConfigurer;
import fr.iocean.bestioles.security.jwt.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    /**
     * Va intercepter les requêtes HTTP et les fait passer à travers une chaîne de filtres
     * pour s'assurer que la ressource demandées est accessible à l'utilisateur.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//            .cors().and()
//            .cors().disable()
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Disable CSRF because of state-less session-management
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN")

                // /auth est accessible à tout le monde
                .requestMatchers("/auth/**").permitAll()

                // api-docs est accessible à tout le monde
                .requestMatchers("api-docs/**").permitAll()
            )
            // on applique le filtre qui check le JWT
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(jwtTokenProvider);
    }



//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("user123"))
//                .roles("USER")
//                .build();
//        UserDetails userAdmin = User.withUsername("admin")
//                .password(passwordEncoder().encode("admin456"))
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, userAdmin);
//    }
}
