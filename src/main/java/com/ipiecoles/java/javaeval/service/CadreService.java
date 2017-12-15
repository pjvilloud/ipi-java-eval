package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Cadre;
import com.ipiecoles.java.javaeval.repository.CadreRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CadreService extends EmployeService {

    
    private CadreRepository cadreRepository;
    
    public void setRepository(CadreRepository er) {
    	this.cadreRepository = er;
    }

    public List<Cadre> findByCoefficientBetween(Integer coefLower, Integer coefUpper){
        return cadreRepository.findByCoefficientBetween(coefLower, coefUpper);
    }
    public List<Cadre> findByCoefficient(Integer coef){
    	return cadreRepository.findByCoefficient(coef);
    }
}
