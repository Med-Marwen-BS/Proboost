package com.proboost.proboostproject.Modules;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "interview")
    private Entretien entretien;

}
