package com.ipiecoles.java.javaeval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;

public class EntrepriseService {
	
	@Autowired
	private EmployeService employeService;
	private EntrepriseRepository entrepriseRepository;
	
	public Entreprise CreerEntreprise(Entreprise entreprise) {
		
		return entrepriseRepository.save(entreprise);
		
	}
    
	public List<Entreprise> findByNom(String nom) {
		
		return entrepriseRepository.findByNom(nom);
		
	
	}
	//à modifier en utilisant employeService !
	public void AjoutEmploye (Entreprise entreprise,Employe employe) {
		
		//Création de l'employé (dans l'entité Employe)  à partir de la classe EmployeService
		Employe emp=employeService.creerEmploye(employe);
		//Ajout de l'employé crée dans l'entité Entreprise
		entreprise.getListEmploye().add(emp);
		

	}
	
	public void SupprimeEmploye (Employe employe,Entreprise entreprise) {
		
		//Récupération de l'id de l'employé
		Long id=employe.getId();
		//Suppression de l'employé ayant tel id  (dans l'entité Employe)  à partir de la classe EmployeService.
		employeService.deleteEmploye(id);
		
	}
	
	public List <Employe> findAllEmploye( Entreprise entreprise) {
		
		List <Employe> listeEmploye = entreprise.getListEmploye();
		
		return listeEmploye;
	}
	
	
	public Integer CountAllEmploye(Entreprise entreprise) {
		
		//renvoie un Integer au lieu d'un long car la méthode size() renvoie un type Long : compromis pour utiliser cette méthode
	    //il serait peut être préférable d'utiliser une requête native SQL
		return entreprise.getListEmploye().size();
	}
	
	
	//Augmenter un employé d'une entreprise 
	//Au préalable: établir une relation entre les entités Entreprise et Employe.
	
	public void AugmenterSalaireEmploye(Employe employe,Double pourcentage) {
		//On augmente le salaire d'un certain pourcentage: salaire => salaire(1+pourcentage*0.01);
		//La méthdoe getSalaire() de l'entité Employe renvoie un type Double
		Double salaire=employe.getSalaire();
		salaire=salaire*(1+0.01+pourcentage);
		employe.setSalaire(salaire);
		
	}
	//Récupérer la masse salariale d'une entreprise
	
	public Double MasseSalariale(Entreprise entreprise) {
		// Il y a peut-être une méthode plus directe avec une requête SQL ou en utilisant le repository mais je n'ai pas creusé de ce côté
		Double masseSal=0.0;
		for (int i=0;i<entreprise.getListEmploye().size();i++) {
			
			Employe employeCourant=entreprise.getListEmploye().get(i);
			Double salaireEmployeCourant=employeCourant.getSalaire();
			masseSal+=salaireEmployeCourant;
		}
	
		return masseSal;
		
	}
	
}
