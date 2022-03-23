package com.proboost.proboostproject.Modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private String email ;

    private String username;

    private String nom;

    private String prenom;

    private String password ;

    private LocalDate naissance;
    private Boolean locked = false;
    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = true)
    @OneToMany(mappedBy = "candidat", cascade = CascadeType.REMOVE)
    private List<Candidature> candidatures=new ArrayList<Candidature>();

    @Column(nullable = true)
    @OneToMany(mappedBy = "recruteur", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Offre_Emploi> offres=new ArrayList<Offre_Emploi>();

    @OneToMany(mappedBy = "recruteur")
    @JsonIgnore
    private List<Entretien> entretiens=new ArrayList();


    public User(String email,
                String username, String nom,
                String prenom, String password,
                LocalDate naissance, Boolean locked,
                Boolean enabled, Role role, List<Candidature> candidatures,
                List<Offre_Emploi> offres, List<Entretien> entretiens) {
        this.email = email;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.naissance = naissance;
        this.locked = locked;
        this.enabled = enabled;
        this.role = role;
        this.candidatures = candidatures;
        this.offres = offres;
        this.entretiens = entretiens;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
