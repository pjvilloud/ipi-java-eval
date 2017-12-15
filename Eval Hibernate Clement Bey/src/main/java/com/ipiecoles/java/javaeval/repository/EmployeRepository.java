package com.ipiecoles.java.javaeval.repository;

import com.ipiecoles.java.javaeval.model.Employe;

public interface EmployeRepository extends BaseEmployeRepository<Employe> {
	
	Employe findOneByNomAndPrenom(String nom, String prenom);
}
