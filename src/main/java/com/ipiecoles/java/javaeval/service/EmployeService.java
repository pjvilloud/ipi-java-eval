package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
//import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.EmployeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import java.util.List;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;
    
    // Getter de Employe Repository
    public EmployeRepository getEmployeRepository() {
		return employeRepository;
	}

    // Setter de Employe Repository
	public void setEmployeRepository(EmployeRepository employeRepository) {
		this.employeRepository = employeRepository;
	}

	public Employe findById(Long id){
        return employeRepository.findOne(id);
    }

    public Long compterEmployes() {
        return employeRepository.count();
    }

    //supprimer employe par son id
    public void supprimerEmploye(Long id){
        employeRepository.delete(id);
    }

    // Creer un employe: 
    public Employe creerEmploye(Employe e) {
        return employeRepository.save(e);
    }
    
    // mettre a jour employe
    public Employe majEmploye(Employe e) {
        return employeRepository.save(e);
    }
    
    // trouver employes par entreprise
    public List<Employe> findByEntreprise(Entreprise etps){
    	return employeRepository.findByEntreprise_id(etps.getId());
    }
    
    // Trouver employe par nom et prenom: 
    public Employe findByNomAndPrenom(String nom, String prenom) {
    	return (Employe) employeRepository.findOneByNomAndPrenom(nom, prenom);
    }
    
    // Trouver employe par nom: 
    public Employe findByNom(String nom) {
    	return (Employe) employeRepository.findOneByNom(nom);
    }
}
