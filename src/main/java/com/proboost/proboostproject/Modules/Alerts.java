package com.proboost.proboostproject.Modules;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Alerts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    private Boolean opened;

    @ManyToOne
    private User user;
}
