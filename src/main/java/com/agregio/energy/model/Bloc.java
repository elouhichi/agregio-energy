package com.agregio.energy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int heureDebut;
    private int heureFin;
    private double puissanceMW;
    private double prixPlancher;

    @ManyToMany
    @JoinTable(
            name = "bloc_parc",
            joinColumns = @JoinColumn(name = "bloc_id"),
            inverseJoinColumns = @JoinColumn(name = "parc_id")
    )
    private List<Parc> parcs;


    @ManyToOne
    @JoinColumn(name = "offre_id")
    private Offre offre;
}
