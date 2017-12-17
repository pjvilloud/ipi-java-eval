package com.ipiecoles.java.javaeval.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.persistence.*;
import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.repository.EmployeRepository;

import net.minidev.json.writer.CollectionMapper.ListType;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeService {

	//Injection d'employé repository pour récupérer les requetes
    @Autowired
    private EmployeRepository employeRepository;

    //Injection d'employés
    @Autowired
    public Employe employe;
    
    //Trouver un employé par son id
    public Employe findById(String id){
        return employeRepository.findOne(id);
    }

    //Compter le nombre d'employés
    public Long countAllEmploye() {
      Long nbEmploye = employeRepository.count();
      return nbEmploye;
    }

    //Supprimer un employé 
    public void deleteEmploye(String employe, Long id){
       	employeRepository.delete(employe);
    
    }

    //Créer un employé
    public Employe creerEmploye(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
    	return employe = employeRepository.save(nom, prenom,  matricule, dateEmbauche, salaire);
    }
    
    //Lister l'ensemble des employés
    public HashSet<Employe> findAllEmployeSortingAsc(String nom) {
    	HashSet<Employe> Liste = employeRepository.findAll(new Sort(Sort.Direction.ASC, nom));
    	return Liste;
    }
    
    //Augmenter le salaire d'un employé
    public Double augmentation(Double pourcentage) {
    	Double salaireAug =  employe.augmenterSalaire(pourcentage);	
    	return salaireAug;
    }

   //Récupérer le salaire annuel d'un employé
   public Double Salaireannuel(Double salaire) {
	 Double SalaireAnnuel = employe.setSalaire(salaire*12);
	 return SalaireAnnuel;
   }
		   
   }
