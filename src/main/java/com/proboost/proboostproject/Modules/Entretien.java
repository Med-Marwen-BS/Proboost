package com.proboost.proboostproject.Modules;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
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

    @ManyToOne
    private User recruteur;

    @OneToMany(mappedBy = "entretien")
    private List<QCM> qcm=new ArrayList<>();

    @OneToOne
    private Interview interview;



}
