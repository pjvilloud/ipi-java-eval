package com.ipiecoles.java.javaeval.model;

import org.joda.time.LocalDate;
import javax.persistence.*;
import java.util.HashSet;

@Entity
@Table(name="entreprise")
public final class Entreprise {
	public static final Double SALAIRE_BASE = 1480.27;
	public static final Integer NB_CONGES_BASE = 25;
	public static final Integer NB_RTT_BASE = 12;
	public static final Double INDICE_MANAGER = 1.3;
	public static final Double PRIME_MANAGER_PAR_TECHNICIEN = 250d;
	public static final Double PRIME_ANCIENNETE = 100d;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;

	@Column(name = "nom")
	public String nom;

	@Column(name = "listeEmployes")
	public HashSet<Employe> listeEmployes;
	
	public static Double primeAnnuelleBase() {
		return LocalDate.now().getYear() * 0.5;
	}

	public void AjouterEmploye(Employe emp){
		this.listeEmployes.add(Employe emp) = ;
	}
}
