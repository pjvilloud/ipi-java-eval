package com.ipiecoles.java.javaeval.exceptions;

import com.ipiecoles.java.javaeval.model.Cadre;

public class CadreException extends Exception {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String GRADE = "Le coefficient doit être compris entre 1 et 5 : ";

    public CadreException(String message, Cadre cadre, Object valeurIncorrecte) {
        super(message + valeurIncorrecte + ", technicien : " + cadre.toString());
        System.out.println(this.getMessage());
    }
}

