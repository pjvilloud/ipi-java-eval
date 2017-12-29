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

    //Rechercher les cadres avec un coefficient précis
    public List<Cadre> findByCoefficient(Integer coeffiscient){
    	return cadreRepository.findByCoefficient(coeffiscient);
    }

    //Rechercher les cadres avec un coefficient compris entre deux valeurs
    public List<Cadre> findByCoefficientBetween(Integer X, Integer Y){
        return cadreRepository.findByCoefficientBetween(X, Y);
    }

}