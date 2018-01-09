package com.ipiecoles.java.javaeval.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.service.EmployeService;

public interface EntrepriseRepository extends CrudRepository<Entreprise,Long> {
	
	Entreprise findByNom(String nom);
}
