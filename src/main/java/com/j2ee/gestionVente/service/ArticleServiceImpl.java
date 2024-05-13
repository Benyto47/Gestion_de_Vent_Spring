package com.j2ee.gestionVente.service;

import com.j2ee.gestionVente.entities.Article;
import com.j2ee.gestionVente.entities.Category;
import com.j2ee.gestionVente.repository.ArticleRepository;
import com.j2ee.gestionVente.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service /*toujours pour marquer que c'est un bean */
public class ArticleServiceImpl implements ArticleService{

    /* injection des dépendance : L'injection de dépendance est un concept clé en programmation où une classe dépend d'une autre classe ou d'un objet, et cette dépendance est fournie par un tiers plutôt que d'être créée directement par la classe elle-même. Cela permet de rendre le code plus flexible,
     réutilisable et facilite la gestion des dépendances entre les différentes parties d'une application.*/

    @Autowired
    private ArticleRepository articleRepository ; /* si cette interface n'etait pas déclaré comme un bean, jamais on autait pu
    faire l'injection de dépendance */
    @Autowired
    private UserRepository userRepository;

    @Override
    public Article saveArticle(Article article) {

        Article art = article ;

        if (art.getImage() == null || art.getImage().isEmpty()) {
            String categoryNom = art.getCategory().getNom();
            if (categoryNom.equals("Téléphone"))
                art.setImage("/image/telephone/apple-14-pro-renders.jpg");
            else if (categoryNom.equals("Tablette"))
                art.setImage("/image/tablette/tablette noire.jpg");
            else if (categoryNom.equals("Ordinateur"))
                art.setImage("/image/pc/dell 13.jpg");
            else if (categoryNom.equals("Lunette"))
                art.setImage("/image/lunette/hugo-boss.jpg");
        }

        //Gérer automatiquement la date actuelle sous format String.
        Date date = new Date(); // Obtenir la date actuelle
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        String formattedDate = dateFormat.format(date);

        article.setDate(formattedDate);


        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Article article) {

        Article art = article ;

        if (art.getImage() == null || art.getImage().isEmpty()) {
            String categoryNom = art.getCategory().getNom();
            if (categoryNom.equals("Téléphone"))
                art.setImage("/image/telephone/apple-14-pro-renders.jpg");
            else if (categoryNom.equals("Tablette"))
                art.setImage("/image/tablette/tablette noire.jpg");
            else if (categoryNom.equals("Ordinateur"))
                art.setImage("/image/pc/dell 13.jpg");
            else if (categoryNom.equals("Lunette"))
                art.setImage("/image/lunette/hugo-boss.jpg");
        }

        //Gérer automatiquement la date actuelle sous format String.
        Date date = new Date(); // Obtenir la date actuelle
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        String formattedDate = dateFormat.format(date);

        article.setDate(formattedDate);

        return articleRepository.save(article);
    }

    @Override
    public void deleteArticleById(Long id) {

        articleRepository.deleteById(id);
    }

    @Override
    public void deleteArticle(Article article) {

        articleRepository.delete(article);
    }

    @Override
    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public Article updateQteArtClient(Integer qte_client, Article article) {

        //d'abord aller chercher l'article en question puis le modifier
        Article art = articleRepository.findById(article.getId()).get() ;

        //modifier l'article avec la quantité
        art.setQte_client(qte_client);

      return   articleRepository.save(art) ;
    }

    @Override
    public List<Article> getArticleByCategory(Category category) {
        return articleRepository.findByCategory(category);
    }

    @Override
    public List<Article> getArticleByCategoryId(Long id) {
        return articleRepository.findByCategoryId(id);
    }



    @Override
    public Article updateArticleColor(Article article, String color) {

        //d'abord aller chercher l'article en question puis le modifier

        Article art = articleRepository.findById(article.getId()).get() ;
        //juste modifier la couleur entrer par l'utilisateur
          art.setColor(color);
        return   articleRepository.save(art) ;
    }

    @Override
    public List<Article> getAllBySell_false_ByCategoryId(Long id) {

        //prendre toute les articles selon la category
        List<Article> articles = articleRepository.findByCategoryId(id) ;

        List<Article> article_false = new ArrayList<>() ;

        //trier par ceux ou l'attribut 'sell' est FALSE
        for(Article item: articles){

            if(item.getSell().equals(false))
                article_false.add(item) ;
        }

        return article_false ;
    }

    @Override
    public List<Article> getAllBySell_true_ByCategoryId(Long id) {

        //prendre toute les articles selon la category
        List<Article> articles = articleRepository.findByCategoryId(id) ;

        List<Article> article_true = new ArrayList<>() ;

        //trier par ceux ou l'attribut 'sell' est TRUE
        for(Article item: articles){

            if(item.getSell().equals(true))
                article_true.add(item) ;
        }

        return article_true ;
    }
    @Override
    public List<Article> updateAllArticle(List<Article> articles) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Article> updatedArticles = new ArrayList<>();

        for (Article art : articles) {
            if (art.getImage() == null || art.getImage().isEmpty()) {
                String categoryNom = art.getCategory().getNom();
                if (categoryNom.equals("Téléphone"))
                    art.setImage("/image/telephone/apple-14-pro-renders.jpg");
                else if (categoryNom.equals("Tablette"))
                    art.setImage("/image/tablette/tablette-noire.jpg");
                else if (categoryNom.equals("Ordinateur"))
                    art.setImage("/image/pc/dell-13.jpg");
                else if (categoryNom.equals("Lunette"))
                    art.setImage("/image/lunette/hugo-boss.jpg");
            }

            // Formater la date
            Date currentDate = new Date();
            String formattedDate = dateFormat.format(currentDate);
            art.setDate(formattedDate);

            updatedArticles.add(art);
        }

// Enregistrer la liste d'articles modifiés
        articleRepository.saveAll(updatedArticles);

        return articleRepository.saveAll(articles);
    }

    @Override
    public List<Article> findAllNotSell() {
        return articleRepository.findBySell(false);
    }

    @Override
    public List<Article> findAllSell() {
        return articleRepository.findBySell(true);
    }
}
