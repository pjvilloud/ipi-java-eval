package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.EmployeRepository;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class EntrepriseService {
	
    
    private EntrepriseRepository entrepriseRepository;
    private EmployeRepository employeRepository;
    
    public void setRepository(EntrepriseRepository er) {
    	this.entrepriseRepository = er;
    }
    public void setEmployeRepository(EmployeRepository er) {
    	this.employeRepository = er;
    }
    
    public Entreprise creerEntreprise(Entreprise ets) {
    	return entrepriseRepository.save(ets);
    }
    
    //Rechercher une entreprise par son id
    public Entreprise findById(Long id) {
    	return entrepriseRepository.findOne(id);
    }
    
    //Rechercher une entreprise par son nom
    public Entreprise findByNom(String nom) {
    	return entrepriseRepository.findByNom(nom);
    }
    
    //Ajouter un employé à une entreprise
    public void addEmployeToEntreprise(Employe emp, Entreprise ets) {
    	ets.addEmploye(emp);
    	entrepriseRepository.save(ets);
    	emp.setEntreprise(ets);
    	employeRepository.save(emp);
    }
    
    //Supprimer un employé d'une entreprise
    public void deleteEmployeFromEntreprise(Employe emp, Entreprise ets) {
    	ets.deleteEmploye(emp);
    	entrepriseRepository.save(ets);
    }
    
    //Lister les employés d'une entreprise
    public List<Employe> getEmployes(Entreprise ets){
    	return entrepriseRepository.findByEntreprise(ets.getId());
    }
    
    //Compter les employés de l'entreprise
    public Integer countEmployes(Entreprise ets) {
    	return ets.countEmployes();
    }
    
    //Augmenter le salaire d'un employé d'une entreprise
    public void AugmenterEmploye(Entreprise ets, Employe emp, Double augmentation) {
    	emp.setSalaire(Entreprise.SALAIRE_BASE+augmentation);
    }
    
    //Récupérer la masse salariale de l'entreprise
    public Double calculerMasseSalariale(Entreprise ets) {
    	Set<Employe>emp = ets.getEmployes();
    	Double masseSalariale = 0.0;
    	Iterator<Employe> it = emp.iterator();
    	while(it.hasNext()) {
    		masseSalariale += ((Employe) it.next()).getSalaire();
    	}
    	return masseSalariale;
    }
    
}
