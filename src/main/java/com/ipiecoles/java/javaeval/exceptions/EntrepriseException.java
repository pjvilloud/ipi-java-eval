package com.ipiecoles.java.javaeval.exceptions;

import com.ipiecoles.java.javaeval.model.Entreprise;

public class EntrepriseException extends CustomException {
	
	public static final String NOM =
	"Le nom doit être compris entre "+Entreprise.MIN_NOM+" et "+Entreprise.MAX_NOM;
			
    public EntrepriseException(String message, Object valeurIncorrecte) {
		super(message, valeurIncorrecte);
	}   
}