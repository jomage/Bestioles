package fr.iocean.bestioles.security.jwt;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;

public class JwtUtils {

    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;

    public static final String AUTHORITIES_KEY = "auth";
}
