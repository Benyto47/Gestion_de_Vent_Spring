package com.j2ee.gestionVente.restcontroller;

import com.j2ee.gestionVente.entities.Article;
import com.j2ee.gestionVente.repository.ArticleRepository;
import com.j2ee.gestionVente.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController/* pour déclarer que c'est une Rest , API*/
@RequestMapping("/articles") /* le chemin pour acceder a ces methodes */
@CrossOrigin /* j'autorise a toute les origines a acçeder a ses divers méthodes , ce API*/
public class ArticleController {
    @Autowired /* injeciton de dépendance , pour la flexibilité de notre application . spring boot est basé sur l'injection des dépendances*/
    private ArticleService articleService ;

    @Autowired
    private ArticleRepository articleRepository ;

    @PostMapping /* @RequestBody pour dire qu'on va réçevoir notre JSON et le convertir en classe Article*/
    public ResponseEntity<Article> saveArticle(@RequestBody Article article) {
        Article savedArticle = articleService.saveArticle(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);  /* ResponseEntity , juste pour gérer les status*/
    }

    @PutMapping
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        Article savedArticle = articleService.updateArticle(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED); /* on retourne du JSON , même si on vois que c'est une entité Article
        il sera convertis en JSON . c'est notre @RestController qui a permit ça */
    }

    @PostMapping("/modify/{color}") /* @PathVariable  , on va prendre cette valeur {color} et on le converti au bon format*/
    public void modifyColorArticle(@RequestBody Article article,@PathVariable("color") String color)
    {
          articleService.updateArticleColor(article,color) ;
    }

    //pour ajouter plusieur articles plusieurs
    @PutMapping("/allArticle")
    public List<Article> updateArticles(@RequestBody List<Article> articles) {
        // Code pour modifier la liste d'articles

        return articleService.updateAllArticle(articles) ;
    }


    @GetMapping(value="/{id}")
    public Article findArticle(@PathVariable Long id){

        return articleService.getArticleById(id);
    }


    @GetMapping("/all") /* obtenir toute les articles */
    public List<Article> findAllArticle(){

        return articleService.getAllArticle() ;
    }

    //tout les articles non selectionner , qui n'ont pas été ajouter au Panier
    @GetMapping("/AllNotSell")  /*ici c est une logique qu'on a ajouter. pour le frontend*/
    public List<Article> findAllNotSell(){

        return articleService.findAllNotSell() ;
    }

    //tout les articles selectionner
    @GetMapping("/AllSell") /* l'inverser de ce qui est en haut */
    public List<Article> findAllSell(){

        return articleService.findAllSell() ;
    }

    //tout les articles non selectioner par category
    @GetMapping(value="/AllNotSellByCateogry/{id}")
    public List<Article> findAllNotSellByCat(@PathVariable("id") Long id){

        return articleService.getAllBySell_false_ByCategoryId(id) ;
    }

    //tout les articles non selectioner par category
    @GetMapping(value="/AllSellByCateogry/{id}")
    public List<Article> findAllSellByCat(@PathVariable("id") Long id){

        return articleService.getAllBySell_true_ByCategoryId(id) ;
    }

    @GetMapping(value="/category/{id}")
    public List<Article> findArticleByCatego(@PathVariable Long id){

        return articleService.getArticleByCategoryId(id) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/panier/qte/client/{qte}")
    public Article updateQteArtClient(@PathVariable("qte") Integer qte_client,@RequestBody Article article)
    {

       return   articleService.updateQteArtClient(qte_client,article);

    }

    //ADMIN afficher le nombre d'articles par pages
    @GetMapping("/admin")
    public Page<Article> getArticles(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleRepository.findAll(pageable);
    }

}
