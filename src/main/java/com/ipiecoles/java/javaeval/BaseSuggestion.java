package com.ipiecoles.java.javaeval;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.List;

import com.ipiecoles.java.javaeval.State;
import com.ipiecoles.java.javaeval.exceptions.CustomException;
import com.ipiecoles.java.javaeval.model.CRUDModel;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.service.CRUDService;

public class BaseSuggestion implements Outputter {
	
	private int maxChoices;
	private List<State> choices;
	
	private CRUDService crudService;
	private CRUDModel entity;
	
	private String serviceName;
	
	public BaseSuggestion(CRUDService s, List<State> c) {
		choices = c;
		crudService = s;
		init();
	}
	private void init() {
		serviceName = crudService.toString();
		restate();
	}
	
	public void restate() {
		outQuestion("What would you like to do?");
		for(maxChoices = 1; maxChoices < choices.size() + 1; maxChoices++) {
			outList(numActionText(maxChoices));
		}
	}
	private String actionText(int i) {
		// ex: entreprise: create
		return serviceName + " - " + choices.get(i-1).toString();
	}
	private String numActionText(int i) {
		// ex: 1: entreprise - create
		return i + ": " + actionText(i);
	}
	
	public boolean nextLine() {
		
		String input = cleanInput();
		
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
    		if(1 <= num && num <= maxChoices) {
    			performAction(num);
    			return;
    		}
    	} catch(NumberFormatException e) {}
    	
    	outl("Please enter a number between 1 and " + maxChoices + ".");
	}
	
	public void performAction(int i) {
		outl("===== " + actionText(i) + " =====", 1);
		switch(choices.get(i-1)) {
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
		Long num = crudService.countAll();
		
		outl("There are " + num + " " + serviceName + ".");
		if(num > 0) {
			outl("Here is the list:", 2);
			
			List<? extends CRUDModel> list = crudService.findAll();
			for(int i=0, l=list.size(); i<l; i++) {
				outList("\t" + i + ": " + list.get(i).toString());
			}
		}
	}
	private void createEntity() {
		Constructor<?> constr = Entreprise.class.getConstructors()[0]; 
		int num = constr.getParameterCount();
		
		outl("There are " + num + " values to set to create a " + serviceName + ".");
		outl("Here is the list:", 2);
		
		Parameter[] params = constr.getParameters();
		
		if(!params[0].isNamePresent()) {
			outImportant("Warning: to properly display field names, please compile using '-parameters'.");
			outImportant("pom.xml should have provided this. Perhaps try a Maven run 'clean verify'?", 1);
		}

		for(int i=0; i<num; i++) {
			outList("\t" + params[i].getName() + " (" + params[i].getType().getSimpleName() + ")");
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
				crudService.create(hydrate);
				outl(serviceName + " was successfully created.", 0);
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
		return MyRunner.SCAN.nextLine().replaceAll("\\s", "");
	}
	
}