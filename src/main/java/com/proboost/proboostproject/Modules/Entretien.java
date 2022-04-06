package com.proboost.proboostproject.Modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Entretien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;

    private String titre;

    private String type;

    private Integer hour;

    private Integer minute;

    @ManyToOne
    private User recruteur;

    @ManyToOne
    private User candidat;

    @ManyToMany(mappedBy = "entretiens")
    @JsonIgnore
    private List<QCM> qcm=new ArrayList<>();

    @OneToOne
    private Interview interview;



}
