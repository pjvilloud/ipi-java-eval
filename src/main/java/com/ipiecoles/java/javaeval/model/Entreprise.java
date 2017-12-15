package com.ipiecoles.java.javaeval.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.joda.time.LocalDate;

@Entity
public final class Entreprise {
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
	
	@OneToMany(mappedBy = "entreprise")
	private Set<Employe> employes = new HashSet<>();
	
	public void addEmploye(Employe emp) {
		employes.add(emp);
	}
	public void removeEmploye(Employe emp) {
		employes.remove(emp);
	}
	public Set<Employe> listEmployes() {
		return employes;
	}
	public Integer countEmployes() {
		return employes.size();
	}
	public boolean hasEmploye(Employe emp) {
		return employes.contains(emp);
	}
	
	private Double massSalary;
	public Double getMassSalaries() {
		massSalary = 0d;
		employes.forEach((e) -> massSalary += e.getSalaire());
		return massSalary;
	}
}
