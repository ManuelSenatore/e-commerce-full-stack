package com.CartoleriaPapyrus.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nomeCompleto;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany // PIU UTENTI POSSONO AVERE PIU RUOLI E VICEVERSA
    @JoinTable(name = "user_roles", //Nome della tabella che verrà creata
            joinColumns = @JoinColumn(name = "user_id"),// Crea colonna
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<Role>();

    private Boolean active = true;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private List<Indirizzo> indirizzo;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private List<Carrello> ordine;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private List<Preferito> preferito;

    public void addRole(Role r) {

        this.roles.add(r);

    }
}
