package com.ipiecoles.java.javaeval.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import com.ipiecoles.java.javaeval.exceptions.TechnicienException;
import com.ipiecoles.java.javaeval.service.EmployeService;

@Entity
@Table (name = "entreprise2")
public class Entreprise {
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
			
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@Column
	public static String nomEntreprise;
	
	public static String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		Entreprise.nomEntreprise = nomEntreprise;
	}

	//Injecter la classe employés pour pouvoir l'utiliser
	@Autowired
	public Employe employe;
	
	//Injecter la classe employeService pour pouvoir l'utiliser
	@Autowired
	public EmployeService employeService;

	//Créer une liste d'employés par entreprise
	@OneToMany(mappedBy ="Entreprise")
	private HashSet<Employe> ListeEmp = new HashSet();
	
	public void setListeEmp(HashSet<Employe> listeEmp) {
		ListeEmp = listeEmp;
	}
	

	public HashSet<Employe> getListeEmp() {
		return ListeEmp;
	}


	//Compter le nombre d'employes dans l'entreprise
	@Column(name ="nbEmployes")
	public Integer nbEmployes() {
		return ListeEmp.size();
	
	}
	
	//Ajouter un employé à la liste
	public  void ajouterEmployeListe(Employe employe) {
		ListeEmp.add(employe);

	    }
		
	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public EmployeService getEmployeService() {
		return employeService;
	}

	public void setEmployeService(EmployeService employeService) {
		this.employeService = employeService;
	}

	

	//Masse salariale de l'entreprise, j'ai essayé de faire une liste de l'ensemble des salaires
	//Puis je parcours ce résultat et fait la somme de tous les salaires présents dans la liste
	public Double masseSalariale(Double salaire){
		List<Double> masseSalariale = ListeEmp.stream().map(c -> c.getSalaire()).collect(Collectors.toList());
		for(salaire= 0.0d; salaire <masseSalariale.size(); salaire++);
		return salaire++;
		
	}

	public static final Double SALAIRE_BASE = 1480.27;
	public static final Integer NB_CONGES_BASE = 25;
	public static final Integer NB_RTT_BASE = 12;
	public static final Double INDICE_MANAGER = 1.3;
	public static final Double PRIME_MANAGER_PAR_TECHNICIEN = 250d;
	public static final Double PRIME_ANCIENNETE = 100d;
		
	public static Double primeAnnuelleBase() {
		return LocalDate.now().getYear() * 0.5;
	}

	
	
}
