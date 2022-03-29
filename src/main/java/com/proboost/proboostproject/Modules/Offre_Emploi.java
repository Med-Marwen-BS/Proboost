package com.proboost.proboostproject.Modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Offre_Emploi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private LocalDateTime postedDate;

    private LocalDateTime deadline ;

    private String jobeType ;

    private String location ;

    private int experience ;

    private int salary  ;

    private int vacancy;

    @ManyToOne
    private User recruteur;

    @OneToMany(mappedBy = "offre")
    private List<Candidature> candidatures=new ArrayList<Candidature>();

}
