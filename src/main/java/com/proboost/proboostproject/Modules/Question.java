package com.proboost.proboostproject.Modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;

    private String reponseA;

    private String reponseB;

    private String reponseC;

    private String answer;

    @ManyToOne
    @JsonIgnore
    private QCM qcm;

}
