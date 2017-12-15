package com.ipiecoles.java.javaeval.model;

import org.joda.time.LocalDate;

import com.ipiecoles.java.javaeval.exceptions.CadreException;

import javax.persistence.Entity;

@Entity
public class Cadre extends Employe {

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
	
	public int getCoefficient() {
		return this.coefficient;
	}
	
	/**
	 * @param define the coefficient to set
	 * @throws CadreException 
	 */
	
	public void setCoefficient(Integer coefficient) throws CadreException{
		if(coefficient<=0 || coefficient > 5) {
			throw new CadreException(CadreException.GRADE, this, coefficient);
		}
		this.coefficient = coefficient ;
	}
	

	public void augmenterSalaire(Double pourcentage) {
		super.augmenterSalaire(pourcentage);
	}

	@Override
	public String toString() {
		return "Cadre{" + super.toString() + "coefficient : "+ coefficient+ "}";
	}

	@Override
	public Double getPrimeAnnuelle() {
		Double salaireBase = Entreprise.primeAnnuelleBase();
		return salaireBase + salaireBase * 1.1 + Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete();
	}
	@Override 
	public Integer getNbConges() {
		return Entreprise.NB_CONGES_BASE+5;
	
	}
}
