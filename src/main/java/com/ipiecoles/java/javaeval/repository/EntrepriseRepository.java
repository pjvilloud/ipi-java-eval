package com.ipiecoles.java.javaeval.repository;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// Pour pouvoir profiter des implementations du crud 
public interface EntrepriseRepository extends CrudRepository<Entreprise, Long>{
	
	//Pouvoir retrouver entreprise par son nom 
	Entreprise findByNom(String nom);
	
	//Pouvoir retrouver toutes les entreprises 
	Iterable<Entreprise> findAll();
	
	//Pouvoir retrouver la liste des employes d'une entreprise: 
    @Query("select E from Employe E where entreprise.id = :id")
    List<Employe> findByEntreprise(@Param("id") Long id);
}




