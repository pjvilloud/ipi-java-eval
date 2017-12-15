package com.ipiecoles.java.javaeval.repository;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface EntrepriseRepository extends CrudRepository<Entreprise, Integer>, PagingAndSortingRepository<Entreprise,Integer> {
    public Entreprise creerEntreprise(Entreprise e);
    public  Entreprise AjouterEmploye(Employe emp);

    List<Entreprise> findByNom(String nom);
}
