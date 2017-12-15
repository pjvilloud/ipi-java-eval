package com.ipiecoles.java.javaeval.exceptions;

import com.ipiecoles.java.javaeval.model.Cadre;

public class CadreException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -46465298479125228L;

    public static final String COEFF = "Le grade doit être compris entre 1 et 5 : ";

    public CadreException(String message, Cadre cadre, Object valeurIncorrecte) {
        super(message + valeurIncorrecte + ", cadre : " + cadre.toString());
        System.out.println(this.getMessage());
    }
}