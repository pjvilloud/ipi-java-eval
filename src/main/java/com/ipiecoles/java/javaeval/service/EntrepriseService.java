package com.ipiecoles.java.javaeval.service;
import javax.persistence.*;
import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.service.EmployeService;

import net.minidev.json.writer.CollectionMapper.ListType;

import com.ipiecoles.java.javaeval.repository.EmployeRepository;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrepriseService  {

    @Autowired
    private EntrepriseRepository entrepriseRepository;
    
    @Autowired
    private Entreprise entreprise;
    
    @Autowired
    private EmployeService employeService;
    
    //Compter le nombre d'entreprises
    public Long countAllEntreprise() {
        return this.entrepriseRepository.count();
    }
    
    //Trouver une entreprise par nom
    public Entreprise nomEntreprise(String nomEntreprise) {
     return this.entrepriseRepository.findOne(nomEntreprise);
     
    }
            
    //creer une Entreprise
    public void createEntreprise() {
         entreprise = this.entrepriseRepository.save(new Entreprise ());
    }
    
    //Supprimer une Entreprise
    public void deleteEntreprise(Entreprise entreprise) {
      this.entrepriseRepository.delete(entreprise);
    } 
    
    //Ajouter un employe
    public void newEmploye(Employe employe) {
    	

    }
    
    //Supprimer un employe
    public void deleteEmploye(String employe, Long id) {
        this.employeService.deleteEmploye(employe, id);
      } 
    
    //Lister les employés d'une entreprise 
    public HashSet<Employe> listEmploye() {
    	HashSet<Employe> ListeEmp = entreprise.getListeEmp();
		return ListeEmp;
    }
    
   // Augmenter le salaire d'un employe
    public Double augmentationEmploye(Double pourcentage) {
    	return employeService.augmentation(pourcentage);	
    }
    
    
}