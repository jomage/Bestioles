package fr.iocean.bestioles.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * Classe qui permet de prendre le pas sur la gestion d'erreurs lors de la vérif du JWT.
 * Voir https://stackoverflow.com/questions/78361586/how-to-customise-error-handling-in-jwt-authentication-with-spring-security-6
 */

//@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
//
//        // here you can parse error message in return in customse way
//        Error error = new Error();// Custom model class for you exception
//        error.setErrors("Token expired", HttpStatus.FORBIDDEN.toString());
//
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//        ServletOutputStream out = response.getOutputStream();
//        new ObjectMapper().writeValue(out, error);
//        out.flush();
    }
} 
