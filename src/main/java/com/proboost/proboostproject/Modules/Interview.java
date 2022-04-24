package com.proboost.proboostproject.Modules;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;

    private LocalDate date;

    private Integer hour;

    private Integer minute;

    private String link;

    private String code;

    @ManyToOne
    private User recruter;

    @ManyToOne
    private User candidat;



}
