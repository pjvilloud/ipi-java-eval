package com.ipiecoles.java.javaeval.exceptions;

public class CustomException extends Exception {
	
	public CustomException(String message, Object valeurIncorrecte) {
        super(message);
    }

}