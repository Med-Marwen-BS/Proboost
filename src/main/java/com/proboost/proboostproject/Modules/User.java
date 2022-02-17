package com.proboost.proboostproject.Modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String nom;

    private String prenom;

    private LocalDate naissance;

    private Boolean enabled;

    private String role;

    @Column(nullable = true)
    @OneToMany(mappedBy = "candidat", cascade = CascadeType.REMOVE)
    private List<Candidature> candidatures=new ArrayList<Candidature>();

    @Column(nullable = true)
    @OneToMany(mappedBy = "recruteur", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Offre_Emploi> offres=new ArrayList<Offre_Emploi>();

}
