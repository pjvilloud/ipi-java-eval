package com.ipiecoles.java.javaeval.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.persistence.*;

import com.ipiecoles.java.javaeval.model.Cadre;
import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.repository.EmployeRepository;

import net.minidev.json.writer.CollectionMapper.ListType;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CadreService {

	//Injection d'employé repository pour récupérer les requetes
    @Autowired
    private EmployeRepository employeRepository;

    //Injection de Cadre
    @Autowired
    public Cadre cadre;
    
    //Trouver des cadres avec un grade précis
    public Employe findByGrade(Integer grade){
        return employeRepository.findAll(grade);
    }

   //Trouver un cadre avec un grade compris entre X et Y
    

		   
   }
