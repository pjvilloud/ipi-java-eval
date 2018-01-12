package com.ipiecoles.java.javaeval.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.JSpinner.ListEditor;

import org.joda.time.LocalDate;

//1.1
@Entity
@Table(name="Entreprise")
public final class Entreprise {
	public static final Double SALAIRE_BASE = 1480.27;
	public static final Integer NB_CONGES_BASE = 25;
	public static final Integer NB_RTT_BASE = 12;
	public static final Double INDICE_MANAGER = 1.3;
	public static final Double PRIME_MANAGER_PAR_TECHNICIEN = 250d;
	public static final Double PRIME_ANCIENNETE = 100d;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//Une relation one to many est caractérisée par un champ de type Collection (ici List <Employe> listEmploye dans la classe esclave (ici Entreprise).
	
	private String nom;
	
	@OneToMany(mappedBy="entreprise")
	private List <Employe> listEmploye;
	
	public static Double primeAnnuelleBase() {
		return LocalDate.now().getYear() * 0.5;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Employe> getListEmploye() {
		return listEmploye;
	}

	public void setListEmploye(List<Employe> listEmploye) {
		this.listEmploye = listEmploye;
	}
	
	

}
