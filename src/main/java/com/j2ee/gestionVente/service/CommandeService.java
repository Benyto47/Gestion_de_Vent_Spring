package com.j2ee.gestionVente.service;

import com.j2ee.gestionVente.entities.Commande;

import java.util.List;


public interface CommandeService {

    Commande saveCommande(Commande commande) ;
    List<Commande> findByUserId(Long id) ;
    void deleteCommande(Commande commande) ;
    void deleteAllCommande() ;
    void deleteCommandeById(Long id) ;
    Commande getCommandeById(Long id) ;
    List<Commande> getAllCommande() ;
}
