package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.repository.BaseEmployeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseEmployeService<T extends Employe> {

	@Autowired
    BaseEmployeRepository<T> baseEmployeRepository;

    public Employe findById(Long id){
        return baseEmployeRepository.findOne(id);
    }

    public Long countAllEmploye() {
        return baseEmployeRepository.count();
    }

    public void deleteEmploye(Long id){
        baseEmployeRepository.delete(id);
    }

    public Employe creerEmploye(T e) {
        return baseEmployeRepository.save(e);
    }

	public void updateEmploye(T emp) {
		baseEmployeRepository.save(emp);
	}
	public void deleteEmploye(T emp) {
		baseEmployeRepository.delete(emp);
	}

	public void augmenterEmploye(T emp, Double pourcentage) {
		emp.augmenterSalaire(pourcentage);
	}
}
