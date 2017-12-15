package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.EmployeRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeService {

    
    private EmployeRepository employeRepository;
    
    public void setRepository(EmployeRepository er) {
    	this.employeRepository = er;
    }

    public Employe findById(Long i){
        return employeRepository.findOne(i);
    }

    public Long countAllEmploye() {
        return employeRepository.count();
    }

    public void deleteEmploye(Long id){
        employeRepository.delete(id);
    }

    public Employe creerEmploye(Employe e) {
        return employeRepository.save(e);
    }
    public Employe updateEmploye(Employe e) {
        return employeRepository.save(e);
    }
    public List<Employe> findByEntreprise(Entreprise ets){
    	return employeRepository.findByEntreprise_id(ets.getId());
    }
    public Employe findByNomAndPrenom(String nom, String prenom) {
    	return (Employe) employeRepository.findOneByNomAndPrenom(nom, prenom);
    }
}
