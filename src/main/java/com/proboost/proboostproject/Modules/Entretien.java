package com.proboost.proboostproject.Modules;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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

    @OneToOne
    private QCM qcm;

    @OneToOne
    private Interview interview;

}
