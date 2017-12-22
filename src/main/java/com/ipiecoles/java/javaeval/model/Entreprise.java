package com.ipiecoles.java.javaeval.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.joda.time.LocalDate;

//Je modifie ma classe entreprise pour la transformer en entité
@Entity
public class Entreprise {
	// --------------------------------------les constantes ----------------------------
	public static final Double SALAIRE_BASE = 1480.27;
	public static final Integer NB_CONGES_BASE = 25;
	public static final Integer NB_RTT_BASE = 12;
	public static final Double INDICE_MANAGER = 1.3;
	public static final Double PRIME_MANAGER_PAR_TECHNICIEN = 250d;
	public static final Double PRIME_ANCIENNETE = 100d;
	
	// --------------------------------------les attributs ----------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// Creation de l'attribut nom: 
	private String nom; 
	
	// Creation de la liste d'employes 
	// une entreprise pour plusieurs employes
	@OneToMany(mappedBy = "entreprise", fetch=FetchType.EAGER)
	private Set<Employe> listeEmployes = new HashSet<Employe>();
	
	// --------------------------------------les getters et setters ----------------------------
	// Getter de l'ID 
	public Long getId() {
		return id;
	}
	
	// Setter de l'ID 
	public void setId(Long id) {
		this.id = id;
	}

	//Getter de Liste d'Employes
	public Set<Employe> getListeEmployes() {
		return listeEmployes;
	}

	//Setter de Liste d'Employes
	public void setListeEmployes(Set<Employe> listeEmployes) {
		this.listeEmployes = listeEmployes;
	}

	// Getter du nom
	public String getNom() {
		return nom;
	}

	// Setter du nom 
	public void setNom(String nom) {
		this.nom = nom;
	}

	//--------------------------------------Autres fonctions ----------------------------
	
	public static Double primeAnnuelleBase() {
		return LocalDate.now().getYear() * 0.5;
	}

	// Fonction permettant de rajouter un employe dans la liste des Employes
	public boolean ajouterEmploye(Employe e) {
		return this.listeEmployes.add(e);
	}
	
	// Fonction permettant de supprimer un employe de la liste des Employes
	public boolean supprimerEmploye(Employe e) {
		return this.listeEmployes.remove(e);
	}
	
	//Fonction permettant de compter le nombre d'employes de la liste des Employes: 
	public Integer compterEmployes() {
		return this.listeEmployes.size();
	}
	
	@Override
	public String toString() {
		return this.getNom(); 
	}


}
