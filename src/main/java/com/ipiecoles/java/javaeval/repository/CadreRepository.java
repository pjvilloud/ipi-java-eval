package com.ipiecoles.java.javaeval.repository;

import com.ipiecoles.java.javaeval.model.Cadre;

import java.util.List;

public interface CadreRepository extends BaseEmployeRepository<Cadre> {

    public List<Cadre> findByCoefficient(Integer coefficient);
    public List<Cadre> findByCoefficientBetween(Integer gradeLower, Integer gradeUpper);

}