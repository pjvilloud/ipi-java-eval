package com.ipiecoles.java.javaeval.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Cadre")
public class Cadre {
	
	//Sûrement à revoir !!!
	private enum coeff {
		un,
		deux,
		trois,
		quatre,
		cinq
	}
	
	@Column(name="coefficient")
	private String coefficient;
	
	public Integer getCoefficient() {
		Integer i=0;
		if (coefficient.equals(coeff.un)) {
			
			i=1;
		}
		
		if (coefficient.equals(coeff.deux)) {
			
			i=2;
		}if (coefficient.equals(coeff.trois)) {
			
			i=3;
		}
		
		if (coefficient.equals(coeff.quatre)) {
			
			i=4;
		}
		
		if (coefficient.equals(coeff.cinq)) {
			
			i=5;
		}
		
		return i;
		
		
		
		
	}

}