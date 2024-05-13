package com.j2ee.gestionVente.restcontroller;

import com.j2ee.gestionVente.entities.User;
import com.j2ee.gestionVente.service.ArticleService;
import com.j2ee.gestionVente.service.CommandeService;
import com.j2ee.gestionVente.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventes")
@CrossOrigin
public class VenteController {

    @Autowired
    private UserService userService ;
    @Autowired
    private ArticleService articleService ;
    @Autowired
    private CommandeService commandeService ;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

}
