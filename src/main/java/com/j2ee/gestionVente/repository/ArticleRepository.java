package com.j2ee.gestionVente.repository;

import com.j2ee.gestionVente.entities.Article;
import com.j2ee.gestionVente.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository /* cette entité est un Bean , C-Â-D , les attributs seront privé , pas de FINALE, et toujours a le constructor par défaut sans paramètres*/
public interface ArticleRepository extends JpaRepository<Article,Long> {
    /*JpaRepository fourni par Spring Data Jpa  Lorsque on utilise JpaRepository , Hibernate est utilisé
     en arrière-plan pour gérer les opérations de persistance de l'entité associée.
     dans notre cas Article */

    List<Article> findByCategory(Category category) ;
    List<Article> findByCategoryId(Long id) ;

    List<Article> findBySell(Boolean sell) ;

}
