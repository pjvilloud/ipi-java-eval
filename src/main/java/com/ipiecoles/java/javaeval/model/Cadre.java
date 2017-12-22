package com.ipiecoles.java.javaeval.model;

import javax.persistence.Entity;

import org.joda.time.LocalDate;

import com.ipiecoles.java.javaeval.exceptions.CadreException;

@Entity
public class Cadre extends Employe {
	
	// rajout  du coefficient comme demande dans l enonce 
	private Integer coefficient; 
	
	//le cadre a des rtt car il est soumis a un plus grand stress
	private Integer rtt;
	

	
	//----------------------------------------------------constructeurs
	public Cadre() {}
	
	public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer coefficient, Integer rtt) throws CadreException  {
		super(nom, prenom, matricule, dateEmbauche, salaire);
		this.coefficient = coefficient;
		this.rtt = rtt;
	}
	
	//----------------------------------------------------getters et setters
	public Integer getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Integer coefficient) throws CadreException{
		if(coefficient<1 || coefficient > 5) {
			throw new CadreException(CadreException.GRADE, this, coefficient);
		}
		this.coefficient = coefficient ;
	}
	
	public Integer getRtt() {
		return rtt;
	}

	public void setRtt(Integer rtt) {
		this.rtt = rtt;
	}
	

	// ----------------------------------------------------Redefinition des fonctions de la mere : 
	@Override
	public String toString() {
		return "Cadre{" +
				"coefficient=" + coefficient +
				"} " + super.toString();
	}

	// Pour la prime annuelle : il touche la prime annuelle de base a laquelle on rajoute une multiplication du coefficient par 100 euro (donc il est coefficient 1 il ne touchera que 100 euro de plus mais si il est coefficient 5 il touchera 500 euro de plus)
	@Override
	public Double getPrimeAnnuelle() {
		Double primecadre = Entreprise.primeAnnuelleBase();
		return primecadre = primecadre + 100.0*this.coefficient;
	}
	
	// une semaine de conges en plus pour les cadres car ils sont soumis a plus de stress et doivent plus se reposer
	@Override 
	public Integer getNbConges() {
		return Entreprise.NB_CONGES_BASE+7;
	}
	
	//on change tout simplement le coefficient qui etait originalement de 1 pour 1,1
	@Override
	public void augmenterSalaire(Double pourcentage) {
		super.setSalaire(super.getSalaire() * (1.1 + pourcentage));
	}

}
