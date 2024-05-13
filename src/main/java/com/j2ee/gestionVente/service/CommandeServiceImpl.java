package com.j2ee.gestionVente.service;

import com.j2ee.gestionVente.entities.Article;
import com.j2ee.gestionVente.entities.Commande;
import com.j2ee.gestionVente.entities.User;
import com.j2ee.gestionVente.repository.ArticleRepository;
import com.j2ee.gestionVente.repository.CommandeRepository;
import com.j2ee.gestionVente.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class CommandeServiceImpl implements CommandeService{

    @Autowired
    private CommandeRepository commandeRepository ;
    @Autowired
    private UserRepository userRepository  ;
    @Autowired
    private ArticleRepository articleRepository ;


    @Override
    public Commande saveCommande(Commande commande) {



        //  userRepository.save(user) ;*/
        List<Article> articles = commande.getUser().getArticles();
        User user = commande.getUser() ;
        List<Article> userArt = new ArrayList<>() ;

        for(Article article : articles){
            article.setCommander(true);
            userArt.add(article) ;

            //pour que l'utilisateur , ne vois plus les articles qu'il a commander sur le PANIER
            //IL A DÉJÀ TÉLECHARGER LE PDF
            //comme ça il peut ajouter des nouveaux articles dans le PANIER et faire la commande
            article.setUser(null);
            articleRepository.save(article) ;
        }

        // compléter la relations bidirectionnelles des deux côtés
        user.setArticles(null);
        userRepository.save(user) ;

        Date date = new Date(); // Obtenir la date actuelle
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        String formattedDate = dateFormat.format(date);
        commande.setDateCmd(formattedDate);

        return commandeRepository.save(commande);
    }

    @Override
    public List<Commande> findByUserId(Long id) {
        return commandeRepository.findByUserId(id);
    }



    @Override
    public void deleteCommande(Commande commande) {
            commandeRepository.delete(commande);
    }

    @Override
    public void deleteAllCommande() {

        commandeRepository.deleteAll();
    }

    @Override
    public void deleteCommandeById(Long id) {

        commandeRepository.deleteById(id);
    }

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).get();
    }

    @Override
    public List<Commande> getAllCommande() {
        return commandeRepository.findAll();
    }

}
