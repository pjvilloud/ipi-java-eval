package com.ipiecoles.java.javaeval.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.OneToMany;
import org.joda.time.LocalDate;
import javax.persistence.*;

public class Cadre extends Employe {

	@OneToMany(mappedBy = "cadre")
	private Set<Commercial> equipeCo = new HashSet();
	
	private Integer NbCongesConsommes;
	
	public Integer getNbCongesConsommes() {
		return NbCongesConsommes;
	}

	public void setNbCongesConsommes(Integer nbCongesConsommes) {
		NbCongesConsommes = nbCongesConsommes;
	}

	public Cadre(){
		 
	}

	public Cadre(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade, HashSet<Commercial> equipeCo) {
		super(nom, prenom, matricule, dateEmbauche, salaire, grade);
		this.equipeCo = equipeCo;
	}
	
	public Set<Commercial> getEquipeCo() {
		return equipeCo;
	}

	public void setEquipeCo(Set<Commercial> equipeCo) {
		this.equipeCo = equipeCo;
	}
	
	@Override
	public Double getPrimeAnnuelle() {
		Double salaireBase = Entreprise.primeAnnuelleBase();
		return salaireBase + salaireBase * (1 + this.getGrade() * Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete());
	}
	
	@Override
	public Integer getNbConges() {
		Integer CongesRestants = Entreprise.NB_CONGES_BASE - this.getNbCongesConsommes();
		return CongesRestants;
	}
	
	@Override
	public Double augmenterSalaire(Double pourcentage) {
		return this.setSalaire(this.getSalaire() * (1 + pourcentage)*this.getGrade());
	}
	
	
}
