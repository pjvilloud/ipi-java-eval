package com.ipiecoles.java.javaeval.service;

import static org.hamcrest.CoreMatchers.nullValue;
import java.util.List;
import java.util.ListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;
import com.ipiecoles.java.javaeval.service.EmployeService;

@Service
public class EntrepriseService {
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	private EmployeService employeService;
	
	public Entreprise creerEntreprise(Entreprise e) {
		
		return entrepriseRepository.save(e);
	}
	
	public Entreprise findByNom(String pNom) {
		return entrepriseRepository.findByNom(pNom);
	}
	
	public Entreprise effacerEmploye(Long id_employe, Entreprise ent) {
		employeService.deleteEmploye(id_employe);
		return entrepriseRepository.save(ent);
	}
	
	public Entreprise ajouterEmploye(Employe e, Entreprise ent) {
		ent.ajouterEmploye(e);
		return entrepriseRepository.save(ent);
	}
	
	public List<Employe> listerEmployes(Entreprise e) {
		return e.getListeEmployes();
	}
	
	public int nombreEmployes(Entreprise e) {
		return e.getListeEmployes().size();
	}
	
	public Employe augmenterEmploye(Long id_employe, Entreprise ent, Double pourcentage) {
		employeService.findById(id_employe).augmenterSalaire(pourcentage);
		return employeService.findById(id_employe);
	}	
	
	public int masseSalariale(Entreprise e) {
		int somme = 0;
		ListIterator li = e.getListeEmployes().listIterator();
		while (li.hasNext()) {
			somme += ((Employe)li.next()).getSalaire();
		}
		return somme;
		
		
	}
}


