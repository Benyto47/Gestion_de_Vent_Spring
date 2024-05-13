package com.j2ee.gestionVente.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data /* Lombok, cette annotation va créer les getters et setters*/
@NoArgsConstructor /* constructor par défaut sans paramètre*/
@AllArgsConstructor /* toute les autres constructor , pour eviter a chque fois qu'on ajoute un attribut de faire un constructor*/
@Entity /* pour indidquer a Hibernate que cette classe represente une entité , enfin une table de la base de donnée*/
public class Article {

    @Id /*specifier la clé primaire*/
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* activer l'auto incrémentation */
    private Long id ;

    private String nom ;
    private String image ;
    private String descp ;
    private String color ;
    private Integer stockage ;
    private String type ; 
    private Float prix ;
    private String date ;
    private Integer qte_art ;
    private Boolean sell ;
    private Integer qte_client ;
    private Boolean commander ;

    @ToString.Exclude//éviter la boucle infinie lors de la méthode toString()
    @JsonIgnore
    @ManyToOne /* établir la rélation avec la table User*/
    private User user ;

    @ManyToOne
    private Category category ;


}
