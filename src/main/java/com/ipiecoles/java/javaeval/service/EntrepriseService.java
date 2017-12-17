package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrepriseService {
	
	@Override
	public String toString() {
		return "entreprise";
	}

    @Autowired
    private EntrepriseRepository entrepriseRepository;
    
    @Autowired
    private EmployeService employeService;
    
    public List<Entreprise> findAll() {
    	return (List<Entreprise>) entrepriseRepository.findAll();
    }

    public Entreprise findById(Long id){
        return entrepriseRepository.findOne(id);
    }
    public Entreprise findByNom(String nom){
        return entrepriseRepository.findByNom(nom);
    }

    public Long countAllEntreprise() {
        return entrepriseRepository.count();
    }

    public void deleteEntreprise(Long id){
        entrepriseRepository.delete(id);
    }

    public Entreprise createEntreprise(Entreprise e) {
        return entrepriseRepository.save(e);
    }
    
    public void addEmploye(Entreprise e, Employe emp) {
    	 emp.setEntreprise(e);
    	 e.addEmploye(emp);
    	 entrepriseRepository.save(e);
    	 employeService.updateEmploye(emp);
    }
    public void deleteEmploye(Entreprise e, Employe emp) {
    	 e.removeEmploye(emp);
    	 entrepriseRepository.save(e);
    	 employeService.deleteEmploye(emp);
    }
    public Set<Employe> listEmployes(Entreprise e) {
    	return e.getEmployes();
    }
    public Integer countEmployes(Entreprise e) {
    	return e.countEmployes();
    }
    public void augmenterEmploye(Entreprise e, Employe emp, Double pourcentage) {
    	if(e.hasEmploye(emp)) {
    		employeService.augmenterEmploye(emp, pourcentage);
    	} else {
    		System.err.println("Cet employé n'appartient pas à cette entreprise.");
    	}
    }
    
    private Double massSalary;
    public Double getMassSalaries(Entreprise e) {
    	massSalary = 0d;
    	Set<Employe> emps = e.getEmployes();
    	emps.forEach((ee) -> massSalary += ee.getSalaire());
    	return massSalary;
    }
}