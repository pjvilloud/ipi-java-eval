package com.ipiecoles.java.javaeval.repository;

import java.util.List;

import com.ipiecoles.java.javaeval.model.Cadre;

public interface CadreRepository extends BaseEmployeRepository<Cadre> {

    List<Cadre> findByCoefficient(Integer coeffiscient);
	
    List<Cadre> findByCoefficientBetween(Integer X, Integer Y);

}
