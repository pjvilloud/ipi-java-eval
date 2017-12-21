package com.ipiecoles.java.java230.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ipiecoles.java.java230.model.Entreprise;
import com.ipiecoles.java.java230.model.Employe;
import com.ipiecoles.java.java230.repository.EntrepriseRepository;

public class EntrepriseService {
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    
    private EmployeService employeService;
    

    public Entreprise creerEntreprise(Entreprise e) {
        return entrepriseRepository.save(e);
    }
    
    public Employe creerEmploye(Employe e) {
        return employeService.creerEmploye(e);
    }

    public void deleteEmploye(Long id){
    	employeService.deleteEmploye(id);
    }
    
    public Long countAllEmploye() {
        return employeService.countAllEmploye();
    }
}