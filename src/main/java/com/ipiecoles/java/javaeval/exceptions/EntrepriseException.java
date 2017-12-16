package com.ipiecoles.java.javaeval.exceptions;

import com.ipiecoles.java.javaeval.model.Entreprise;

public class EntrepriseException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -46465298479125228L;

    public static final String NOM = "Le nom doit être compris entre "+Entreprise.MIN_NOM+" et "+Entreprise.MAX_NOM+" : ";

    public EntrepriseException(String message, Object valeurIncorrecte) {
        super(message + valeurIncorrecte + ".");
        System.out.println("0?"+this.getMessage());
    }
}