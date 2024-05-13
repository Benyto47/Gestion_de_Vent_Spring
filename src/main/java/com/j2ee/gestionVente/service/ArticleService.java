package com.j2ee.gestionVente.service;

import com.j2ee.gestionVente.entities.Article;
import com.j2ee.gestionVente.entities.Category;

import java.util.List;


public interface ArticleService {
    Article saveArticle(Article article) ;
    Article updateArticle(Article article) ;
    void deleteArticleById(Long id) ;
    void deleteArticle(Article article) ;
    List<Article> getAllArticle() ;
    Article getArticleById(Long id) ;

    Article updateQteArtClient(Integer qte_client,Article article) ;
    List<Article> getArticleByCategory(Category category) ;
    List<Article> getArticleByCategoryId(Long id) ;

    //prendre un article et modifier la couleur voulu par l'utilisateur
    Article updateArticleColor(Article article,String color) ;

    //ceux qui sont pas encore sélectionner :
    List<Article> getAllBySell_false_ByCategoryId(Long id) ;
    List<Article> getAllBySell_true_ByCategoryId(Long id) ;
    List<Article> updateAllArticle(List<Article> articles) ;
    List<Article> findAllNotSell() ;
    List<Article> findAllSell() ; 

}
