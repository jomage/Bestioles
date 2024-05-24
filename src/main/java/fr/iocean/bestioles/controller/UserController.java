package fr.iocean.bestioles.controller;

import fr.iocean.bestioles.entity.User;
import fr.iocean.bestioles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public User createUser(
            @RequestParam("login") String login,
            @RequestParam("mdp") String mdp) {
        return userService.createUser(login, mdp);
    }
}
