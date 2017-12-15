package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.repository.BaseEmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseEmployeService<T extends Employe> {

    @Autowired
    BaseEmployeRepository<T> baseRepository;

    public Employe findById(Long id){
        return baseRepository.findOne(id);
    }

    public Long countAllEmploye() {
        return baseRepository.count();
    }

    public void deleteEmploye(Long id){
        baseRepository.delete(id);
    }

    public Employe creerEmploye(T e) {
        return baseRepository.save(e);
    }

	public void updateEmploye(T emp) {
		baseRepository.save(emp);
	}
	public void deleteEmploye(T emp) {
		baseRepository.delete(emp);
	}

	public void augmenterEmploye(T emp, Double pourcentage) {
		emp.augmenterSalaire(pourcentage);
	}
}
