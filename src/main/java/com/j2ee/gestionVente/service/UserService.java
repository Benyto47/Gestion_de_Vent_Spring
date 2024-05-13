package com.j2ee.gestionVente.service;

import com.j2ee.gestionVente.entities.User;

import java.util.List;


public interface UserService {

   User saveUser(User user) ;
   User updateUser(User user) ;
   void deleteUserById(Long id) ;
   void deleteUser(User user) ;
   User getUserById(Long id) ;
   List<User> getAllUsers() ;

   User updateUserArticles(User user) ;

   User deleteUserArticles(User user) ;

   Float getTotalArticlePrice(Long id) ;

   //Mise à jour de l'article par l'utilisateur
   User updateArtUserAuth(Long idUser, Long idArt) ;
   User deleteArtUserAuth(Long idUser,Long idArt)  ;
   User deleteAllArticle(Long idUser) ;

   User loginUser(User user) ;

   void logoutUser(User auth) ;


   //savoir les personnes connectés
   List<User> authUsers() ;

   //les deconnectes
   List<User> notAuthUsers() ;

   User forgotPassword(String email) ;

}
