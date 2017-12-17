package com.ipiecoles.java.javaeval;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Scanner;

import com.ipiecoles.java.javaeval.State;
import com.ipiecoles.java.javaeval.exceptions.CustomException;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.service.EntrepriseService;

public class Suggestion implements Outputter {
	
	private Scanner scan;
	private int max;
	private List<State> options;
	private EntrepriseService entrepriseService;
	
	private String entityName;
	
	public Suggestion(EntrepriseService e, List<State> s) {
		options = s;
		entrepriseService = e;
		init();
	}
	private void init() {
		entityName = entrepriseService.toString();
		restate();
	}
	
	public void restate() {
		outQuestion("What would you like to do?");
		for(max = 1; max < options.size() + 1; max++) {
			outList(numActionText(max));
		}
	}
	private String actionText(int i) {
		// ex: entreprise: create
		return entityName + ": " + options.get(i-1).toString();
	}
	private String numActionText(int i) {
		// ex: 1) entreprise: create
		return "(" + i + ") " + actionText(i);
	}
	
	public boolean nextLine(Scanner scan) {
		this.scan = scan;
		
		String input = scan.nextLine();
		
		switch(input) {
			case "exit":
			case "":
				outImportant("exiting...", 2);
				return false;
					
			default:
				outl(spamChar("~", 90), 2);// print separator
				return takeInput(input);
		}
	}
	private boolean takeInput(String input) {
		
		switch(input) {				
			case "?":
				restate();
				break;

			default:
				parseInput(input);
		}
		
		return true;
	}
	private void parseInput(String input) {
    	try {
    		int num = Integer.parseInt(input);
    		if(1 <= num && num <= max) {
    			performAction(num);
    			return;
    		}
    	} catch(NumberFormatException e) {}
    	
    	outl("Please enter a number between 1 and " + max + ".");
	}
	
	public void performAction(int i) {
		outl("===== " + actionText(i) + " =====", 1);
		switch(options.get(i-1)) {
			case LIST:
				listEntities();
				break;
			case CREATE:
				createEntity();
				break;
			case SELECT:
				break;
			case EDIT:
				break;
			case DELETE:
				break;
			case DEFAULT:
				outl("01don't do this.");
				break;
		}
	}
	
	private void listEntities() {
		Long num = entrepriseService.countAllEntreprise();
		
		outl("There are " + num + " " + entityName + ".");
		if(num > 0) {
			outl("Here is the list:", 2);
			
			List<Entreprise> list = entrepriseService.findAll();
			for(int i=0, l=list.size(); i<l; i++) {
				outList("\t" + i + ": " + list.get(i).getNom());
			}
		}
	}
	private void createEntity() {
		Constructor<?> constr = Entreprise.class.getConstructors()[0]; 
		int num = constr.getParameterCount();
		
		outl("There are " + num + " values to set to create a " + entityName + ".");
		outl("Here is the list:", 2);
		
		Parameter[] params = constr.getParameters();
		for(int i=0; i<num; i++) {
			outList("\t" + params[i].getName() + " : " + params[i].getType().getSimpleName());
			
			if(params[i].getName().equals("arg0")) {
				outImportant("Warning: to properly display field names, please compile using '-parameters'.");
				outImportant("pom.xml should have provided this. Perhaps try a Maven run 'clean verify'?");
			}
		}
		
		outl("Enter '' to cancel.", 0);
		
		boolean success = false;
		Entreprise hydrate = new Entreprise();
		
		for(int i=0; i<num; i++) {
			
			String paramName = params[i].getName();
			
			Class<?> type = params[i].getType();
			
			String input;
			success = false;
			
			do {
				outInput("Please enter the " + type.getSimpleName() + " " + params[i].getName() + ": ", 0);
				
				input = cleanInput();
				
				if(input.length() == 0) break;
				
				try {
					success = invokeSetter(hydrate, params[i], input);
				} catch (CustomException e) {
					outImportant("Invalid input: " + e.getMessage());
				}
				
			} while(!success);
			
			if(!success) {
				outImportant("Creation was cancelled while setting " + paramName + ".", 0);
				break;
			}
		}
		if(success) {
			try {
				entrepriseService.createEntreprise(hydrate);
				outl(entityName + " was successfully created.", 0);
			} catch(Exception e) {
				outImportant("Something went wrong: " + e.getClass().getSimpleName(), 0);
			}
		} else {
			outImportant("Aborting.");
		}
	}
	
	private boolean invokeSetter(Entreprise hydrate, Parameter param, String input) throws CustomException {
		String paramName = param.getName();
		String setterName = "set" + upperFirst(paramName);
		Class<?> type = param.getType();
		
		try {
			// invoke setter for hydration
			hydrate.getClass().getMethod(setterName, type).invoke(hydrate, type.cast(input));
			return true;
			
		} catch (InvocationTargetException e) {
			throw (CustomException) e.getTargetException();
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
			outImportant("02Something went wrong: " + e.getClass().getSimpleName());
		}
		return false;
	}
	private String upperFirst(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	private String cleanInput() {
		return scan.nextLine().replaceAll("\\s", "");
	}
	
}