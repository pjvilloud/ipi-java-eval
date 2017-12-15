package com.ipiecoles.java.javaeval.repository;

import java.util.List;

import com.ipiecoles.java.javaeval.model.Cadre;

public interface CadreRepository extends BaseEmployeRepository<Cadre> {

    List<Cadre> findByCoefficientBetween(Integer coefficientlower, Integer coefficientupper);

    List<Cadre> findByCoefficient(Integer coefficient);

}
