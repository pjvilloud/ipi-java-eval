package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.CRUDModel;
import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.repository.BaseEmployeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseEmployeService<T extends Employe> implements CRUDService {

	@Autowired
    BaseEmployeRepository<T> baseEmployeRepository;
	
	// ===== CRUDService implementation ===== //
	@Override public Long countAll() {return countAllEmploye();}
	@Override public void delete(Long id) {deleteEmploye(id);}
	@Override public void delete(CRUDModel m) {deleteEmploye((T) m);}
	@Override public void update(CRUDModel m) {updateEmploye((T) m);}
	@Override public T create(CRUDModel m) {return createEmploye((T) m);}

    @Override
	public T findById(Long id){
        return baseEmployeRepository.findOne(id);
    }
    @Override public List<? extends T> findByNom(String nom) {
    	return (List<? extends T>) baseEmployeRepository.findByNomIgnoreCase(nom);
    }

    @Override
	public List<T> findAll() {
		return (List<T>) baseEmployeRepository.findAll();
	}
    
    public Long countAllEmploye() {
        return baseEmployeRepository.count();
    }

    public void deleteEmploye(Long id){
        baseEmployeRepository.delete(id);
    }

    public T createEmploye(T emp) {
        return baseEmployeRepository.save(emp);
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
