package com.ipiecoles.java.javaeval.service;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static javafx.scene.input.KeyCode.T;

@Service
public class EntrepriseService implements EntrepriseRepository {

    private EmployeService employeService;

    public EntrepriseService(EmployeService employeService) {

        this.employeService = employeService

    }

    @Autowired
    private EntrepriseRepository entrepriseRepository; //création d'une instance qui contient les méthodes abstraites EntrepriseRepository


    @Override
    public Employe findByNom(String nom) {
        return null;
    }

    @Override
    public <S extends T> S save(S s) {
        return null;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Entreprise findOne(Integer integer) {
        return null;
    }

    @Override
    public boolean exists(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Entreprise> findAll() {
        return null;
    }

    @Override
    public Iterable<Entreprise> findAll(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void delete(Entreprise entreprise) {

    }

    @Override
    public void delete(Iterable<? extends Entreprise> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}



}
