package com.ipiecoles.java.javaeval.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.joda.time.LocalDate;

@Entity
public class Entreprise {
	public static final Double SALAIRE_BASE = 1480.27;
	public static final Integer NB_CONGES_BASE = 25;
	public static final Integer NB_RTT_BASE = 12;
	public static final Double INDICE_MANAGER = 1.3;
	public static final Double PRIME_MANAGER_PAR_TECHNICIEN = 250d;
	public static final Double PRIME_ANCIENNETE = 100d;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nom;
	@OneToMany(mappedBy = "entreprise", fetch=FetchType.EAGER)
	private Set<Employe> employes = new HashSet<Employe>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(Set<Employe> employes) {
		this.employes = employes;
	}
	
	public Integer countEmployes() {
		return this.employes.size();
	}
	
	public boolean addEmploye(Employe employe) {
		return this.employes.add(employe);
	}
	public boolean deleteEmploye(Employe employe) {
		return this.employes.remove(employe);
	}

	public static Double primeAnnuelleBase() {
		return LocalDate.now().getYear() * 0.5;
	}

}
