package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.CRUDModel;
import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrepriseService implements CRUDService {
	
	@Override public String toString() {return "entreprise";}

    @Autowired private EntrepriseRepository<Entreprise> entrepriseRepository;
    @Autowired private EmployeService employeService;
    
    // ===== CRUDService implementation ===== //
    @Override public Long countAll() {return countAllEntreprise();}
	@Override public void delete(Long id) {deleteEntreprise(id);}
	@Override public void delete(CRUDModel m) {deleteEntreprise((Entreprise) m);}
	@Override public void update(CRUDModel m) {updateEntreprise((Entreprise) m);}
	@Override public Entreprise create(CRUDModel m) {return createEntreprise((Entreprise) m);}
    
    @Override
	public List<Entreprise> findAll() {
    	return (List<Entreprise>) entrepriseRepository.findAll();
    }

    @Override
	public Entreprise findById(Long id){
        return entrepriseRepository.findOne(id);
    }
    @Override
	public List<Entreprise> findByNom(String nom){
        return (List<Entreprise>) entrepriseRepository.findByNom(nom);
    }

    public Long countAllEntreprise() {
        return entrepriseRepository.count();
    }

    public void deleteEntreprise(Long id){
        entrepriseRepository.delete(id);
    }
    public void deleteEntreprise(Entreprise e){
        entrepriseRepository.delete(e);
    }

    public Entreprise createEntreprise(Entreprise e) {
        return entrepriseRepository.save(e);
    }
    
    public Entreprise updateEntreprise(Entreprise e) {
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