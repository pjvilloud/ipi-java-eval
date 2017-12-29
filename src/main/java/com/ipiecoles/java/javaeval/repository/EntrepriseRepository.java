package com.ipiecoles.java.javaeval.repository;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EntrepriseRepository extends CrudRepository<Entreprise, Long>{
	
	//Rechercher une entreprise par nom
	Entreprise findByNom(String nom);
	
	
    @Query("select E from Employe E where entreprise_id = :id")
    List<Employe> findByEntreprise(@Param("id") Long id);
	
}