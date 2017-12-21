package com.ipiecoles.java.java230.model;

import org.joda.time.LocalDate;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Entreprise {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	private String nom;
	
	@OneToMany(mappedBy = "id")
	private ArrayList<Employe> ListeEmploye;
	public static final Double SALAIRE_BASE = 1480.27;
	public static final Integer NB_CONGES_BASE = 25;
	public static final Integer NB_RTT_BASE = 12;
	public static final Double INDICE_MANAGER = 1.3;
	public static final Double PRIME_MANAGER_PAR_TECHNICIEN = 250d;
	public static final Double PRIME_ANCIENNETE = 100d;
	
	public static Double primeAnnuelleBase() {
		return LocalDate.now().getYear() * 0.5;
	}


	/**
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<Employe> getListeEmploye() {
		return ListeEmploye;
	}

	public void setListeEmploye(ArrayList<Employe> listeEmploye) {
		this.ListeEmploye = listeEmploye;
	}
}
