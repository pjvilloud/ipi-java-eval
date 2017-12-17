package com.ipiecoles.java.javaeval.exceptions;

public class TechnicienException extends CustomException {

    public static final String GRADE = "Le grade doit être compris entre 1 et 5 : ";

    public TechnicienException(String message, Object valeurIncorrecte) {
		super(message, valeurIncorrecte);
	}
}