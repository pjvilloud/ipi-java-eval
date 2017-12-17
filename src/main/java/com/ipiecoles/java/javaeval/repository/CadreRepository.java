package com.ipiecoles.java.javaeval.repository;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.*;
import org.springframework.data.repository.CrudRepository;
import com.ipiecoles.java.javaeval.model.Employe;
import net.minidev.json.writer.CollectionMapper.ListType;


public interface CadreRepository extends CrudRepository<Employe,String>  {

	String save(String employe, Long id);

	HashSet<Employe> findAll(Integer grade);
	
	HashSet<Employe> findAll(Sort sort);
	
	ListType<Employe> findAll(Double salaire);

	Employe save(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire);
	
}
