package com.ipiecoles.java.javaeval.service;

import java.util.List;

import com.ipiecoles.java.javaeval.model.CRUDModel;

public interface CRUDService {
	public Long countAll();
	public List<? extends CRUDModel> findAll();
	public CRUDModel findById(Long id);
	public List<? extends CRUDModel> findByNom(String nom);
	public void delete(Long id);
	public void delete(CRUDModel m);
	public void update(CRUDModel m);
	public CRUDModel create(CRUDModel m);
}