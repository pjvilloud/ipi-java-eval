package com.ipiecoles.java.javaeval.service;

public interface CRUDService {
	public Long countAll();
	public Object findById(Long id);
	public Object findByNom(String nom);
	public void delete(Long id);
	public void delete(Object o);
	public void update(Object o);
	public Object create(Object o);
}
