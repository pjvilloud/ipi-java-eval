package com.ipiecoles.java.javaeval.exceptions;

public class CadreException extends CustomException {
	
	public static final String COEFF = "Le grade doit être compris entre 1 et 5 : ";

    public CadreException(String message, Object valeurIncorrecte) {
		super(message, valeurIncorrecte);
	}

}