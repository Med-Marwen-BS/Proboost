package com.proboost.proboostproject.Modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@Column(unique = true,nullable = false)
    private String email ;



    private String nom;

    private String prenom;

    private String password ;

    private LocalDate naissance;
    private Boolean locked = false;
    private Boolean enabled= false;

    @Enumerated(EnumType.STRING)
    private Role role=Role.USER;

    @Column(nullable = true)
    @OneToMany(mappedBy = "candidat", cascade = CascadeType.REMOVE)
    private List<Candidature> candidatures=new ArrayList<Candidature>();

    @Column(nullable = true)
    @OneToMany(mappedBy = "recruteur", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Offre_Emploi> offres=new ArrayList<Offre_Emploi>();

    @OneToMany(mappedBy = "recruteur")
    @JsonIgnore
    private List<Entretien> entretiensRecruter=new ArrayList();

    @OneToMany(mappedBy = "candidat")
    @JsonIgnore
    private List<Entretien> entretiensCandidat=new ArrayList<>();

    @OneToMany(mappedBy = "createur")
    @JsonIgnore
    private List<QCM> qcmList=new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cv_id", referencedColumnName = "id")
    private cvFile cvfile;

    @OneToMany(mappedBy = "recruter")
    private List<Interview> interviewsrecruter=new ArrayList<>();

    @OneToMany(mappedBy = "candidat")
    private List<Interview> interviewscandidat=new ArrayList<>();



    public User(String prenom, String nom,
                String email, String password, Role role) {
        this.email = email;

        this.nom = nom;
        this.prenom = prenom;
        this.password = password;


        this.role = role;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public String getUsername() {
        return email;
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