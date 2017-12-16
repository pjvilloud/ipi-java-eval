package com.ipiecoles.java.javaeval.model;

import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public final class Entreprise {
	public static final Double SALAIRE_BASE = 1480.27;
	public static final Integer NB_CONGES_BASE = 25;
	public static final Integer NB_RTT_BASE = 12;
	public static final Double INDICE_MANAGER = 1.3;
	public static final Double PRIME_MANAGER_PAR_TECHNICIEN = 250d;
	public static final Double PRIME_ANCIENNETE = 100d;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	private String nom;
	
	@OneToMany(mappedBy="entreprise")
	private List<Employe> listeEmployes = new ArrayList<>();
	
	public static Double primeAnnuelleBase() {
		return LocalDate.now().getYear() * 0.5;
	}
	
	public void ajouterEmploye(Employe e) {
		this.listeEmployes.add(e);
	}

	/*@formatter:off*/
	public Long getId() {return id;}	
	public void setId(Long id) {this.id = id;}
	
	public String getNom() {return nom;}	
	public void setNom(String nom) {this.nom = nom;}
	
	public List<Employe> getListeEmployes() {return listeEmployes;}	
	public void setListeEmployes(List<Employe> listeEmployes) {this.listeEmployes = listeEmployes;}
	
	

}
