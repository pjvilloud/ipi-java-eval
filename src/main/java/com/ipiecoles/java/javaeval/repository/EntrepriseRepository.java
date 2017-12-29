package com.ipiecoles.java.javaeval.repository;

import com.ipiecoles.java.javaeval.model.Entreprise;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface EntrepriseRepository<T extends Entreprise> extends PagingAndSortingRepository<T, Long> {
	
    T findById(Integer id);

    List<T> findByNom(String nom);

    T findByNomIgnoreCase(String nom);
    
}