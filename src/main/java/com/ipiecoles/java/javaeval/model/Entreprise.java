package com.ipiecoles.java.javaeval.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.joda.time.LocalDate;

import com.ipiecoles.java.javaeval.exceptions.EntrepriseException;

@Entity
public final class Entreprise {
	
	public static final Integer MIN_NOM = 5;
	public static final Integer MAX_NOM = 20;
	
	public static final Double SALAIRE_BASE = 1480.27;
	public static final Integer NB_CONGES_BASE = 25;
	public static final Integer NB_RTT_BASE = 12;
	public static final Double INDICE_MANAGER = 1.3;
	public static final Double PRIME_MANAGER_PAR_TECHNICIEN = 250d;
	public static final Double PRIME_ANCIENNETE = 100d;
	
	public static Double primeAnnuelleBase() {
		return LocalDate.now().getYear() * 0.5;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nom;
	
	public Entreprise() {}
	
	public Entreprise(String nom) throws EntrepriseException {
		setNom(nom);
	}
	
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom - the nom to set
	 * @throws EntrepriseException 
	 */
	public void setNom(String nom) throws EntrepriseException {
		if(nom.length() < Entreprise.MIN_NOM || nom.length() > Entreprise.MAX_NOM) {
			throw new EntrepriseException(EntrepriseException.NOM, nom);
		}
		this.nom = nom;
	}	
	
	@OneToMany(mappedBy = "entreprise")
	private Set<Employe> employes = new HashSet<>();
	
	public void addEmploye(Employe emp) {
		employes.add(emp);
	}
	public void removeEmploye(Employe emp) {
		employes.remove(emp);
	}
	public Set<Employe> getEmployes() {
		return employes;
	}
	public Integer countEmployes() {
		return employes.size();
	}
	public boolean hasEmploye(Employe emp) {
		return employes.contains(emp);
	}
}
