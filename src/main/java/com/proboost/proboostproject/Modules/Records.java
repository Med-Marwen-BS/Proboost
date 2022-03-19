package com.proboost.proboostproject.Modules;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String user;

    private String date;

    private String score;

    @ManyToOne
    private QCM qcm;
}
