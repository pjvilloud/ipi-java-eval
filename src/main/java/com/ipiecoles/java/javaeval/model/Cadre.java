package com.ipiecoles.java.javaeval.model;

import org.joda.time.LocalDate;

import com.ipiecoles.java.javaeval.exceptions.CadreException;

public class Cadre extends Employe {

	public Cadre() {}

	public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
		super(nom, prenom, matricule, dateEmbauche, salaire);
	}
	
	private Integer coefficient;
	/**
	 * @return the coefficient
	 */
	public Integer getCoefficient() {
		return coefficient;
	}
	/**
	 * @param coefficient the coefficient to set
	 * @throws CadreException 
	 */
	public void setCoefficient(Integer coeff) throws CadreException {
		if(coeff < 1 || coeff > 5) {
			throw new CadreException(CadreException.COEFF, this, coeff);
		}
		coefficient = coeff;
	}

	@Override
	public Double getPrimeAnnuelle() {
		return null;
	}
}
