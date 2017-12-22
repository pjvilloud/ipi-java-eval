package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Cadre;
import com.ipiecoles.java.javaeval.repository.CadreRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CadreService extends EmployeService {

    private CadreRepository cadreRepository;
    
    //getter
    public CadreRepository getCadreRepository() {
		return cadreRepository;
	}

    //setter
	public void setCadreRepository(CadreRepository cadreRepository) {
		this.cadreRepository = cadreRepository;
	}

	//recherche par coefficient entre limites
	public List<Cadre> findByCoefficientBetween(Integer coeffbas, Integer coeffhaut){
        return cadreRepository.findByCoefficientBetween(coeffbas, coeffhaut);
    }
    
	//recherche simple par coefficient
    public List<Cadre> findByCoefficient(Integer coeff){
    	return cadreRepository.findByCoefficient(coeff);
    }
}
