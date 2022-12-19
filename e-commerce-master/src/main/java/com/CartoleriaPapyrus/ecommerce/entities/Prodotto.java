package com.CartoleriaPapyrus.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "prodotti")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private double prezzo;
    private String immagineUrl;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private Disponibilita disponibilita;

    @OneToMany(mappedBy = "prodotto" , cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private List<Carrello> ordine;

    @OneToMany(mappedBy = "prodotto" , cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private List<Preferito> preferito;

}
