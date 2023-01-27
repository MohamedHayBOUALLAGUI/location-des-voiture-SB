package com.tekup.location.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Voiture {
	
	
	//Une voiture est caractérisée par son immatriculation, sa marque, sa date de mise en circulation
	//et son prix de location

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column(name = "immatriculation", unique = true)
	private String immatriculation;
	
	
    @Column(name="date_mise_en_circulation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateMiseEnCirculation;
    
    @Column(name="prix_jour")
    private Double prixJour;

    private String imagePath;
    
    @ManyToOne
    @JoinColumn(name="model_id")
    private Modele model;

    @OneToMany
    private List<Location> locations;

}
