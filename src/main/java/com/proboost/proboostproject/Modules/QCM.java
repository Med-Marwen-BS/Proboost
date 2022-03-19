package com.proboost.proboostproject.Modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class QCM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;

    private int temp;

    private int score;

    @OneToMany(mappedBy = "qcm",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Question> questions=new ArrayList();

    @ManyToOne
    @JsonIgnore
    private Entretien entretien;

    @OneToMany(mappedBy = "qcm")
    @JsonIgnore
    private List<Records> records=new ArrayList<>();


    @OneToMany(mappedBy = "qcm")
    @JsonIgnore
    private List<Cheaters> cheaters=new ArrayList<>();

}
