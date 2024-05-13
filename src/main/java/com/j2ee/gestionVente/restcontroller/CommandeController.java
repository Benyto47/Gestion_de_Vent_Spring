package com.j2ee.gestionVente.restcontroller;

import com.j2ee.gestionVente.entities.Article;
import com.j2ee.gestionVente.entities.Commande;
import com.j2ee.gestionVente.repository.CommandeRepository;
import com.j2ee.gestionVente.service.ArticleService;
import com.j2ee.gestionVente.service.CommandeService;
import com.j2ee.gestionVente.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commandes")
@CrossOrigin
public class CommandeController {

    @Autowired
    private CommandeService commandeService ;

    @Autowired
    private UserService userService ;
    @Autowired
    private ArticleService articleService ;
    @Autowired
    private CommandeRepository commandeRepository;


    @PostMapping
    public Commande saveCommanded(@RequestBody Commande commande) {

        return commandeService.saveCommande(commande);
    }

    @GetMapping("/users/{id}")
    public List<Commande> findCommandesByUserId(@PathVariable("id")Long id){

        return commandeService.findByUserId(id) ;
    }

    @PutMapping
    public Commande putCommanded(@RequestBody Commande commande) {

        return commandeService.saveCommande(commande);
    }

    @GetMapping(value="/{id}")
    public Commande findCommandById(@PathVariable Long id){

        return commandeService.getCommandeById(id);
    }

    @GetMapping("/all")
    public List<Commande> findAllCommande(){

        return commandeService.getAllCommande() ;
    }

    @DeleteMapping("/{id}")
    public void deleteCommandeById(@PathVariable Long id) {

        commandeService.deleteCommandeById(id);

    }

    @GetMapping("/admin")
    public Page<Commande> getArticles(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return commandeRepository.findAll(pageable);
    }
}
