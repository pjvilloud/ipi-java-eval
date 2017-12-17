package com.ipiecoles.java.javaeval.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name="Employe")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nom;
	
	private String prenom;

	private String matricule;
	
	private Integer grade;
	
		
	@ManyToOne
	@JoinColumn(name ="Entreprise_id")
	private Long entreprise_id;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateEmbauche;
	
	private Double salaire = Entreprise.SALAIRE_BASE;
	

	public Employe() {
		
	}
	
	public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) {
		this.nom = nom;
		this.prenom = prenom;
		this.matricule = matricule;
		this.dateEmbauche = dateEmbauche;
		this.setSalaire(salaire);
		this.setGrade(grade);
	}

	public final Integer getNombreAnneeAnciennete() {
		return LocalDate.now().getYear() - dateEmbauche.getYear();
	}
	
	public Integer getNbConges() {
		return Entreprise.NB_CONGES_BASE;
	}
	
	public Long getEntreprise_id() {
		return entreprise_id;
	}

	public void setEntreprise_id(Long entreprise_id) {
		this.entreprise_id = entreprise_id;
	}

	public abstract Double getPrimeAnnuelle();

	public Double augmenterSalaire(Double pourcentage) {
		return this.setSalaire(this.getSalaire() * (1 + pourcentage));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the dateEmbauche
	 */
	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	/**
	 * @param dateEmbauche the dateEmbauche to set
	 * @throws Exception 
	 */
	public void setDateEmbauche(LocalDate dateEmbauche) throws Exception {
		/*if(dateEmbauche != null && dateEmbauche.isAfter(LocalDate.now())) {
			throw new Exception("La date d'embauche ne peut être postérieure à la date courante");
		}*/
		this.dateEmbauche = dateEmbauche;
	}

	/**
	 * @return 
	 * @return the salaire
	 */
	public Double getSalaire() {
		return salaire;
	}
	
	/**
	 * @param salaire the salaire to set
	 * @return 
	 */
	public Double setSalaire(Double salaire) {
		return this.salaire = salaire;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Employe{");
		sb.append("nom='").append(nom).append('\'');
		sb.append(", prenom='").append(prenom).append('\'');
		sb.append(", matricule='").append(matricule).append('\'');
		sb.append(", dateEmbauche=").append(dateEmbauche);
		sb.append(", salaire=").append(getSalaire());
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Employe)) return false;

		Employe employe = (Employe) o;

		if (Double.compare(employe.getSalaire(), getSalaire()) != 0) return false;
		if (nom != null ? !nom.equals(employe.nom) : employe.nom != null) return false;
		if (prenom != null ? !prenom.equals(employe.prenom) : employe.prenom != null) return false;
		if (matricule != null ? !matricule.equals(employe.matricule) : employe.matricule != null) return false;
		return dateEmbauche != null ? dateEmbauche.equals(employe.dateEmbauche) : employe.dateEmbauche == null;

	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, prenom, matricule, dateEmbauche, getSalaire());
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
}
