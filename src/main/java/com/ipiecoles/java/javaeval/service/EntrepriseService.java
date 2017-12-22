package com.ipiecoles.java.javaeval.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.EmployeRepository;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;

@Service 
public class EntrepriseService {
	
	@Autowired
    private EntrepriseRepository entrepriseRepository;
	@Autowired
	private EmployeRepository employeRepository;
	
	// getter de EntrepriseRepository: 
    public EntrepriseRepository getEntrepriseRepository() {
		return entrepriseRepository;
	}

    // setter de EntrepriseRepository: 
	public void setEntrepriseRepository(EntrepriseRepository entrepriseRepository) {
		this.entrepriseRepository = entrepriseRepository;
	}
	
	// getter de EmployeRepository: 
    public EmployeRepository getEmployeRepository() {
		return employeRepository;
	}

    //setter de EmployeRepository
	public void setEmployeRepository(EmployeRepository employeRepository) {
		this.employeRepository = employeRepository;
	}

	// Creer une entreprise:  
    public Entreprise creerEntreprise(Entreprise etp) {
        return entrepriseRepository.save(etp);
    }
    
    // Trouver entreprise par nom: 
    public Entreprise findByNom(String nom) {
    	return (Entreprise) entrepriseRepository.findByNom(nom);
    }
    
    // Trouver entreprise par son ID: 
    public Entreprise findById(Long id) {
    	return entrepriseRepository.findOne(id);
    }
    
    // Ajouter un employe a une entreprise: 
    public void ajouterEmployeAEntreprise(Employe emp, Entreprise etp) {
    	// On rajoute un Employe a la liste des employes de l entreprise et on sauvegarde
    	etp.ajouterEmploye(emp);
    	entrepriseRepository.save(etp); 
    	// on attribue une entreprise a un employe et on sauvegarde
    	emp.setEntreprise(etp);
    	employeRepository.save(emp);
    }
    
    //Supprimer un employe de entreprise: 
    public void supprimerEmployeDeEntreprise(Employe emp, Entreprise etp) {
    	// On supprime l employe de la liste des employes
    	etp.supprimerEmploye(emp);
    	entrepriseRepository.save(etp);
    	// On dit a l employe que son entreprise est null
    	emp.unsetEntreprise(); 
    	employeRepository.save(emp);
    }
    
    //lister les employes d une entreprise: 
    public List<Employe> listerEmployes(Entreprise etp){
    	return entrepriseRepository.findByEntreprise(etp.getId());
    }
    
    //compter les employes d'une entreprise: 
    public Integer compterEmployes(Entreprise etp) {
    	return etp.compterEmployes();
    }
    
	//Augmenter un employe d'une entreprise: 
	public void AugmenterEmploye(Employe e, Double augmentation) {
		e.setSalaire(e.getSalaire()+augmentation);
	}
	
    public Double calculerMasseSalariale(Entreprise etp) {
    	Set<Employe> listeEmployes = etp.getListeEmployes();
    	Double masseSalariale = 0.0;
    	Iterator<Employe> i = listeEmployes.iterator();
    	while(i.hasNext()) {
    		masseSalariale = masseSalariale + ( i.next()).getSalaire();
    	}
    	return masseSalariale;
    }
    
    //lister toutes les entreprises 
    public Iterable<Entreprise> listerEntreprises(){
    	return entrepriseRepository.findAll();
    }
}
    
    
