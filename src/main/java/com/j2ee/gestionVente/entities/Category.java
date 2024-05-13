package com.j2ee.gestionVente.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nom  ;

    @ToString.Exclude //article et category on une realation bidirectionnelle avec User , donc eviter la bouche infinie
    @JsonIgnore
    @OneToMany(mappedBy = "category") /* créer la clé étrangère sur l'entité Article*/
    private List<Article> article ;
}
