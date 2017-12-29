package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Cadre;
import com.ipiecoles.java.javaeval.repository.CadreRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadreService extends BaseEmployeService<Cadre> {

	@Autowired
	CadreRepository cadreRepository;
	
	public List<Cadre> findByCoefficient(Integer coefficient) {
		return cadreRepository.findByCoefficient(coefficient);
	}
    public List<Cadre> findByCoefficientBetween(Integer gradeLower, Integer gradeUpper) {
    	return cadreRepository.findByCoefficientBetween(gradeLower, gradeUpper);
    }
	
}