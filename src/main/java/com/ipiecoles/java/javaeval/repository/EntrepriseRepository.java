package com.ipiecoles.java.javaeval.repository;

import java.util.List;

import javax.swing.JSpinner.ListEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.service.EmployeService;

public interface EntrepriseRepository extends CrudRepository<Entreprise,Long> {
	
	List<Entreprise> findByNom(String nom);
}
