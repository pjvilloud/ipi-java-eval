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

	public Entreprise findByNom(String nom) {
		
		return entrepriseRepository.findByNom(nom);
		
	
	}
	
	public void AjoutEmploye (Employe employe,Entreprise entreprise) {
		
		entreprise.getListEmploye().add(employe);
		

	}
	
	public void SupprimeEmploye (Employe employe,Entreprise entreprise) {
		
		entreprise.getListEmploye().remove(employe);
		
	}
	
	public List <Employe> findAllEmploye( Entreprise entreprise) {
		
		List <Employe> listeEmploye = entreprise.getListEmploye();
		
		return listeEmploye;
	}
	
	
	public Integer CountAllEmploye(Entreprise entreprise) {
		//compromis:renvoie un type Integer au lieu de Long pour s'ajuster à la méthode size() qui renvoie la taille d'une liste.
		
		return entreprise.getListEmploye().size();
	}
	
	
	//Augmenter un employé d'une entreprise 
	//Au préalable: établir une relation entre les entités Entreprise et Employe.
	
	
	//Récupérer la masse salariale d'une entreprise
	
	
	
}
