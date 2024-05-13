package com.j2ee.gestionVente.repository;

import com.j2ee.gestionVente.entities.Commande;
import com.j2ee.gestionVente.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository   /* dadns JpaRepository on spécifie l'entité en question ainsi que le type de la clé primaire qui est Long*/
public interface UserRepository extends JpaRepository<User,Long> {
    /* on hérite déja de ses methodes : save(), findById(), findAll(), delete(), etc., sans avoir à les implémenter manuellement.
    * sans avoir a les implémenter manuellement
    * */

    User findByCommande(Commande commande) ;
    User findByCommandeId(Long id) ;
    User findByPassCmd(Boolean bool) ;
    User findByEmail(String email) ;

    List<User> findByConnected(Boolean connected) ;
}
