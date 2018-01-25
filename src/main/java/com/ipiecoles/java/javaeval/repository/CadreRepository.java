package com.ipiecoles.java.javaeval.repository;

import java.util.List;

import com.ipiecoles.java.javaeval.model.Cadre;

public interface CadreRepository {
	 
	List<Cadre> findByCoefficient (Integer coefficient);
	List<Cadre> findByCoefficientGreaterThanCoeffMinAndLowerThanCoeffMax (Integer coeffMin, Integer coeffMax);
    

}
