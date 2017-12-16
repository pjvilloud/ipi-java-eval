package com.ipiecoles.java.javaeval.model;

import javax.persistence.Entity;
import org.joda.time.LocalDate;

@Entity
public class Cadre extends Employe {

	private Double coefficient;
	
	@Override
	public Double getPrimeAnnuelle() {

		// TODO A redéfinir
		return null;
	}
	
	public Integer getNbConges() {
		
		// TODO A redéfinir
		return Entreprise.NB_CONGES_BASE;
	}
	
	public void augmenterSalaire(Double pourcentage) {
		
		// TODO A redéfinir
		super.setSalaire(this.getSalaire() * (1 + pourcentage));
	}	
	
	public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Double coefficient) {
		super(nom, prenom, matricule, dateEmbauche, salaire);
		
		//si le coefficient saisi n'est pas entre 1 et 5, on assigne
		//la valeur par défaut 0
		if(coefficient < 1 || coefficient > 5) {
			this.coefficient = 0D;
		} else {
			
			//on ne garde que deux chiffres après la virgule
			Double nombre = new Double(coefficient*100D);		
			this.coefficient = nombre.intValue() / 100.0;
		}
	}
		
	

}
