package fr.iocean.bestioles.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.stream.Collectors;


/**
 * Classe utilitaire contenant des méthodes pour obtenir et valider un JWT.
 */
@Service
public class JWTTokenProvider {

    private final Logger log = LoggerFactory.getLogger(JWTTokenProvider.class);

    @Value("${jwt.base64-secret}")
    private String secretKey;

    private final JwtEncoder jwtEncoder;

    private JwtParser jwtParser;

    private final long validityInMilliseconds = 3600000; // 1h

    public JWTTokenProvider(final JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostConstruct
    protected void init() {
        // Encode la secret key en base64 et la transforme en key

        SecretKey key = new SecretKeySpec(
                Base64.getDecoder().decode(secretKey),
                Jwts.SIG.HS256.toString()
        );

        jwtParser = Jwts.parser().verifyWith(key).build();
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        Instant now = Instant.now();
        Instant validity = now.plus(this.validityInMilliseconds, ChronoUnit.MILLIS);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(authentication.getName())
                .claim(JwtUtils.AUTHORITIES_KEY, authorities)
                .build();

        org.springframework.security.oauth2.jwt.JwsHeader jwsHeader = JwsHeader.with(JwtUtils.JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    /**
     * Vérifie la conformité du token JWT
     * @param authToken un String représentant le JWT.
     * @return true si valide, false ou throw exception sinon.
     */
    public boolean validateToken(String authToken) {

        try {
            jwtParser.parseSignedClaims(authToken);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            log.trace("Invalid JWT token.", e);
        } catch (IllegalArgumentException e) {
            log.error("Token validation error {}", e.getMessage());
        }

        return false;
    }

    /**
     * Récupère les informations de connexion contenus dans le JWT.
     *
     * @param token le JWT
     * @return un objet Authentication (Spring Security) contenant les informations de connexion.
     */
    public Authentication getAuthentication(String token) {
        // Récupère les données du jwt
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();

        // Construit une collection d'Authorities de Spring Security à partir des claims du JWT
        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get("roles").toString().split("[,;\\[\\]]"))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // Construit un Utilisateur au sens Spring Security, avec les droits
        User principal = new User(claims.getSubject(), "", authorities);

        // Retourne un objet Authentication de Spring Security
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
}
