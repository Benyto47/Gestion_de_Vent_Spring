package com.j2ee.gestionVente.service;

import com.j2ee.gestionVente.entities.Article;
import com.j2ee.gestionVente.entities.User;
import com.j2ee.gestionVente.repository.ArticleRepository;
import com.j2ee.gestionVente.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service /* on va implémenter tout les métodes de mise à jour et d'autre */
public class UserServiceImpl implements UserService{

    @Autowired
    private ArticleRepository articleRepository ;

    @Autowired
    private UserRepository userRepository ;
    @Override
    public User saveUser(User user) {

        //je marque en true , les personnes connectées
        user.setConnected(true);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public void deleteUser(User user) {

        userRepository.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }



    //lorsque l'utilisateur clique une article
    @Override
    public User updateUserArticles(User user) {

        User client = userRepository.findByEmail(user.getEmail()) ;

        List<Article> articles = user.getArticles();

      /*  articles.forEach(item -> item.setSell(true));
*/
        for (Article item : articles) {
            item.setSell(true);
            item.setQte_client(1);
        }

        //modifier les articles en true
        List<Article> articlesUpdates =  articleRepository.saveAll(articles)  ;

        //je modifie les articles en sell === true dans le client ,
        client.setArticles(articlesUpdates);

        return userRepository.save(client) ;
    }


    @Override
    public User deleteUserArticles(User user) {

        User client = userRepository.findByEmail(user.getEmail()) ;

        List<Article> articles = user.getArticles();

        /*  articles.forEach(item -> item.setSell(true));
         */
        for (Article item : articles) {
            if(item.getSell().equals(false))
                item.setSell(true);
                item.setQte_client(0);
        }

        //modifier les articles en true
        List<Article> articlesUpdates =  articleRepository.saveAll(articles)  ;

        //je modifie les articles en sell === true dans le client ,
        client.setArticles(articlesUpdates);

        return userRepository.save(client) ;
    }

    @Override
    public Float getTotalArticlePrice(Long id) {

        Float count = 0f ;
        User auth = userRepository.findById(id).get() ;

        List<Article> userArticle = auth.getArticles() ;

        if(userArticle != null)
        {
            for(Article article : userArticle){

                //jamais on aura null , car gteQte_client au moment que l'utilsateur ajoute l'article au panier il est mis a 1.
                count += article.getPrix() * article.getQte_client() ;
            }

        }

        return count;
    }

    @Override
    public User updateArtUserAuth(Long idUser, Long idArt) {

        //Chercher le User ainsi que les articles dans la base données
        //il faut aussi mettre à jour les OBJETS REFERENCÉS dans les deux cas, USER et ARTICLE , car on a pas fait des relation en CASCADE
        //ON DOIT GÉRER MANUELLEMENT

        User user = userRepository.findById(idUser).get() ;  ;
        Article article  = articleRepository.findById(idArt).get() ;

        if (article.getSell() == true) {
            throw new IllegalStateException("L'article est déjà pris");
        }
        //marquer comme article déja pris
        article.setSell(true);

      /*  Integer total = article.getQte_art()  ;
        Integer res = article.getQte_client() ;

        article.setQte_art(total- res);*/


        //Quant il sélectionne la Qte est automatiquement a 1. apres il peut modifier par la suite

        //sélectionner au moin quantité de client est à 1 : on évitera d'avoir des valeurs null quant on va calculer la somme total prix * qte_client
        article.setQte_client(1);


        //sélectionner la liste des articles de l'USER puis ajouté LE NOUVEAU ARTICLE
        List<Article> userArticles = user.getArticles();

        //au cas la liste est vide , d'abord l'initialisé
        if (userArticles == null) {
            userArticles = new ArrayList<>();
        }
        userArticles.add(article);
        /*
       important dans les deux parties de la Relations que ça soit @ManyToOne ,@OneToMany.....
       avec les SETTERS modifier les objets pour qu'ils soient mis à jour a la base de donnée avec les relations bidirictionnelles
         */
        //marquer les users qui ont selectionner les articles
        user.setSelectedArt(true);

        user.setArticles(userArticles);
        article.setUser(user);
        //Mettre à jour l'article a la base de donnée
         articleRepository.save(article) ;

        //Mettre à jour le USER
        return  userRepository.save(user) ;
    }

    @Override
    public User deleteArtUserAuth(Long idUser, Long idArt) {
        //Chercher le User ainsi que les articles dans la base données
        //si on Utilise pas les CASCADES : il faut aussi mettre à jour les OBJETS REFERENCÉS dans les deux cas, USER et ARTICLE

        User user = userRepository.findById(idUser).get() ;  ;
        Article article  = articleRepository.findById(idArt).get() ;

        if (article.getSell() == false) {
            throw new IllegalStateException("Erreur vous ne disposez pas de cet article");
        }
        //marquer comme article non pris
        article.setSell(false);

        // aucun client ne l'a sélectionner, on remet a zéro
        article.setQte_client(0);

        //sélectionner la liste des articles de l'USER puis soustraire LE NOUVEAU ARTICLE
        List<Article> userArticles = user.getArticles();

        //au cas la liste est vide , d'abord l'initialisé
        if (userArticles == null) {
            userArticles = new ArrayList<>();
        }

        //supprimé l'article de l'utilisateur
        userArticles.remove(article);
        /*
       important dans les deux parties de la Relations que ça soit @ManyToOne ,@OneToMany.....
       avec les SETTERS modifier les objets pour qu'ils soient mis à jour a la base de donnée avec les relations bidirictionnelles
         */
        user.setSelectedArt(false);

        user.setArticles(userArticles);
       /* article.setUser(user); je ne dois pas faire ça , car il modifiera cet article en ajoutant a chaque fois l'USER
       * supprimé la relation entre article et user : L'ARTICLE N'A PLUS D'UTILISATEUR*/
        article.setUser(null);
        //Mettre à jour l'article a la base de donnée
        articleRepository.save(article) ;

        //Mettre à jour le USER
        return  userRepository.save(user) ;
    }

    @Override
    public User deleteAllArticle(Long idUser) {

        User user = userRepository.findById(idUser).get() ;

        // supprimer les relations entre articles et user
        List<Article> userArticles = user.getArticles();
        if (userArticles != null) {
            for (Article article : userArticles) {
                article.setUser(null);
                article.setQte_client(0);
                article.setSell(false);
                articleRepository.save(article);
            }
        }

        //supprimes tout les articles déja sélectionner
        user.setArticles(null);
        user.setSelectedArt(false);
        userRepository.save(user);

        return user;
    }

    @Override
    public User loginUser(User user) {

        User costumer = userRepository.findByEmail(user.getEmail()) ;

        if(user.getPassword().equals(costumer.getPassword()) && user.getEmail().equals(costumer.getEmail())
        )
        {
            costumer.setConnected(true);
            userRepository.save(costumer) ;

            return costumer  ;
        }
        else{
            return null ;
        }
    }

    @Override
    public void logoutUser(User auth) {

        //si on ne trouve pas l'utilisateur on le déconnecte pas et on return false
        User user = userRepository.findById(auth.getId()).get();

            //je le déconnecte
            user.setConnected(false);
            //mise à jour
            userRepository.save(user) ;
    }

    @Override
    public List<User> authUsers() {

        return userRepository.findByConnected(true) ;
    }

    @Override
    public List<User> notAuthUsers() {
        return userRepository.findByConnected(false);
    }

    @Override
    public User forgotPassword(String email) {

        User user = userRepository.findByEmail(email) ;

        return user ;
    }


}
