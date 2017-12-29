package com.ipiecoles.java.javaeval.model;

import org.joda.time.LocalDate;

import com.ipiecoles.java.javaeval.exceptions.CadreException;

import javax.persistence.Entity;

@Entity
public class Cadre extends Employe {

	//Déclarer l'attribut Coeffiscient
	private Integer coefficient;

	public Cadre(){
	}
	
	public Cadre(Employe emp, Integer coefficient ) throws CadreException {
		super(emp.getNom(), emp.getPrenom(),emp.getMatricule(),emp.getDateEmbauche(),emp.getSalaire());
		this.setCoefficient(coefficient);

	}
	public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer coefficient) throws CadreException {
		super(nom, prenom, matricule, dateEmbauche, salaire);
		this.setCoefficient(coefficient);
	}
	
	public Integer getCoefficient() {
		return this.coefficient;
	}
	
	/**
	 * @param define the coefficient to set
	 * @throws CadreException 
	 */
	
	//Définir un coef entre 1 et 5
	public void setCoefficient(Integer coefficient) throws CadreException{
		if(coefficient<1 || coefficient > 5) {
			throw new CadreException(CadreException.GRADE, this, coefficient);
		}else{
			this.coefficient = coefficient ;
		}
	}
	

	public void augmenterSalaire(Double pourcentage) {
		super.augmenterSalaire(pourcentage);
	}

	
	@Override
	public String toString() {
		return "Cadre{" + super.toString() + "coefficient : "+ coefficient+ "}";
	}

	//Une autre valeur de la prime annuelle
	@Override
	public Double getPrimeAnnuelle() {
		return Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete();
	}
	
	//On est sympa, 10 jours de congés pour tt le monde :-)
	@Override 
	public Integer getNbConges() {
		return Entreprise.NB_CONGES_BASE + 10;
	
	}
}
