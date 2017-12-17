package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.repository.BaseEmployeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseEmployeService<T extends Employe> implements CRUDService {

	@Autowired
    BaseEmployeRepository<T> baseEmployeRepository;
	
	// ===== CRUDService implementation ===== //
	@Override public Long countAll() {return countAllEmploye();}
	@Override public void delete(Long id) {deleteEmploye(id);}
	@Override public void delete(Object o) {deleteEmploye((T) o);}
	@Override public void update(Object o) {updateEmploye((T) o);}
	@Override public Object create(Object o) {return createEmploye((T) o);}

    @Override
	public Employe findById(Long id){
        return baseEmployeRepository.findOne(id);
    }
    @Override public Object findByNom(String nom) {
    	return baseEmployeRepository.findByNomIgnoreCase(nom);
    }

    public Long countAllEmploye() {
        return baseEmployeRepository.count();
    }

    public void deleteEmploye(Long id){
        baseEmployeRepository.delete(id);
    }

    public Employe createEmploye(T emp) {
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
