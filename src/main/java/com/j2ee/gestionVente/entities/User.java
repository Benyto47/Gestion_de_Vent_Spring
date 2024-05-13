package com.j2ee.gestionVente.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;
    private String prenom ;

    @Column(unique = true)
    private String email ;

    private String password ;
    private Boolean passCmd ;
    private Boolean selectedArt;
    private Boolean connected ;
    private Boolean admin ;

    @OneToMany(mappedBy = "user") /* crée la clé étrangère dans l'entité articles */
    private List<Article> articles ; /* un utilisateur peut ajouter un ou plusieur article au Panier, juste ajouter sans faire de commande*/


    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE) /* une commande est attribué a une seule utilisateur*/
    private List<Commande> commande ;


}
