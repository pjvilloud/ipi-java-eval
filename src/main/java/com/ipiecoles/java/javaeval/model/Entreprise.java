package com.ipiecoles.java.javaeval.model;

import ch.qos.logback.core.util.COWArrayList;
import org.joda.time.LocalDate;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "Entreprise")
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
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="nom")
	private String nom;

	public ArrayList<String> getListeUtilisateurs() {
		return listeUtilisateurs;
	}

	public void setListeUtilisateurs(ArrayList<String> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}

	private ArrayList<String> listeUtilisateurs = new ArrayList();


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public Entreprise(String nom) {
		this.nom=nom;
	}

}
