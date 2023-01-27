package com.tekup.location.entities;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Location {
	
	//Une opération de location de voiture possède une date de début, une date de fin, type de 
	//garantie, frais de location, (espèce ou chèque), montant de garantie, etc
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		@Column(name="date_debut")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date dateDebut;
	    
	    @Column(name="date_retour")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date dateRetour;

	    /*@Column(name="prixJour")
		private double prixJour;*/
	    @Column(name="prixTotal")
		@Transient
		private double prix;
	    
	    @ManyToOne
	    @JoinColumn(name="user_id")
	    private User user;
	    
	    @ManyToOne
	    @JoinColumn(name="voiture_id")
	    private Voiture voiture;

	    @PostLoad
		public void calculatePrix() {
			long nbJour = dateRetour.getTime() - dateDebut.getTime();
			float res = (nbJour / (1000 * 60 * 60 * 24));
			this.prix = voiture.getPrixJour() * res;
		}



}
