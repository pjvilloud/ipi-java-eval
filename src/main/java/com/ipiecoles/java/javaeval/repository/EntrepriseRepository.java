package com.ipiecoles.java.javaeval.repository;

import com.ipiecoles.java.javaeval.model.Entreprise;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface EntrepriseRepository extends PagingAndSortingRepository<Entreprise, Long> {
	
    Entreprise findById(Integer id);

    Entreprise findByNom(String nom);

    Entreprise findByNomIgnoreCase(String nom);
    
}