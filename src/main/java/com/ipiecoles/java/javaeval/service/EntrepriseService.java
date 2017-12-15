package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.EmployeRepository;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrepriseService {
    @Autowired
    private EmployeRepository employeRepository;
    public EntrepriseRepository entrepriseRepository;

    public Entreprise creerEntreprise(Entreprise e){
        return entrepriseRepository.save(e);
    };

    public Entreprise AjouterEmploye(Entreprise e){
        return entrepriseRepository.save(e);
    }

}
