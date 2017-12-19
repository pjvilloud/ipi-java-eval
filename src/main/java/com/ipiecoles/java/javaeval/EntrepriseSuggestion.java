package com.ipiecoles.java.javaeval;

import java.util.List;

import com.ipiecoles.java.javaeval.State;
import com.ipiecoles.java.javaeval.service.EntrepriseService;

public class EntrepriseSuggestion extends BaseSuggestion {

	public EntrepriseSuggestion(EntrepriseService service, List<State> choices) {
		super(service, choices);
	}
	
}