package com.j2ee.gestionVente.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String dateCmd ;
    private Long qteCmd ; /* quantité des produit commandés*/
    private Long total  ; /* prix total de commande par l'utilisateur*/

    /*Dans commande je ne verrai pas l'article*/
    @ManyToOne
    private User user ; /* un utlisateur peut avoir plusieur commande */



}
