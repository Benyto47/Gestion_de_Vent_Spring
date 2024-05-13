package com.j2ee.gestionVente.repository;

import com.j2ee.gestionVente.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {
    List<Commande> findByUserId(Long id) ;/* pour des request , on va plûtot suivre ce format déjà établit par spring boot

    en ajouter a la fin le nom de l'attriut de l'entité en question, automatiquement sans implémenter manuellement de methodes
    on va avoir tout les enregistrement lié a cet attribut sur l'entité*/


}
