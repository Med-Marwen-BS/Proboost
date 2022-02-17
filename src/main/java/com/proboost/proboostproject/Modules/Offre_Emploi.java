package com.proboost.proboostproject.Modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Offre_Emploi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;

    private String description;

    private LocalDate deadline;

    @ManyToOne
    private User recruteur;

    @OneToMany(mappedBy = "offre")
    private List<Candidature> candidatures=new ArrayList<Candidature>();

}
