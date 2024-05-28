package fr.iocean.bestioles.controller;

import fr.iocean.bestioles.entity.User;
import fr.iocean.bestioles.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT,RequestMethod.GET})
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200"})
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public Authentication getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PostMapping
    public User createUser(
            @RequestParam("login") String login,
            @RequestParam("mdp") String mdp) {
        return userService.createUser(login, mdp);
    }
}
