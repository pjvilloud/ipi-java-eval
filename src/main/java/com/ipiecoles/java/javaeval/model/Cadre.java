package com.ipiecoles.java.javaeval.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cadre")
public class Cadre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="coefficient")
	private Integer coefficient;
	
	//reste à faire:
	//Redefinir nombre de congés
	//Reféfinir augmenter salaire
	//Redéfinir prime annuel
	
	public Integer getCoefficient() {
		//Si la valeur du coefficient n'est pas renseignée ou si le coefficient n'est pas compris entre 1 et 5
		//on décide dans un premier temps de fixer le coefficient à 1 (il aurait été préférable de renvoyer un message d'erreur)
		Integer i=1; 
		if (coefficient==1) {
			
			i=1;
		}
		
		if (coefficient==2) {
			
			i=2;
		}if (coefficient==3) {
			
			i=3;
		}
		
		if (coefficient==4) {
			
			i=4;
		}
		
		if (coefficient==5) {
			
			i=5;
		}
		
		return i;
		
		
		
		
	}

}