package com.ipiecoles.java.javaeval.service;

import java.util.List;

import javax.swing.JSpinner.ListEditor;

import org.springframework.beans.factory.annotation.Autowired;

import com.ipiecoles.java.javaeval.model.Cadre;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.CadreRepository;

public class CadreService {
	
	@Autowired
	private CadreRepository cadreRepository;
	
	public List<Cadre> findByCoeff (Integer coefficient) {
		
		return cadreRepository.findByCoefficient(coefficient);
		
		
	}
	
	
public List<Cadre> findByCoeffInterval (Integer coeffMin,Integer coeffMax) {
		
		return cadreRepository.findByCoefficientGreaterThanCoeffMinAndLowerThanCoeffMax (coeffMin, coeffMax);
		
		
	}

}
