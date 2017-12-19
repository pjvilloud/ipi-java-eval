
package com.ipiecoles.java.javaeval;

import java.util.Arrays;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipiecoles.java.javaeval.service.EntrepriseService;

@Component
public class MyRunner implements CommandLineRunner, Outputter {
	
	@Autowired
	private EntrepriseService entrepriseService;
	
	public static final Scanner SCAN = new Scanner(System.in);

    @Override
    public void run(String... strings) throws Exception {
		
		outl("==================================", 0);
		outl("====== Welcome to Hibernate ======");
		outl("==================================", 1);

		outImportant("Enter '?' at any time to restate available actions.");
		outImportant("Enter 'exit' or '' to quit.", 1);
		
		EntrepriseSuggestion suggestion = new EntrepriseSuggestion(entrepriseService, Arrays.asList(State.LIST, State.CREATE, State.SELECT));
		
		boolean running;
		
		do {
			outl("");
			outInput("");
			running = suggestion.nextLine();
			
		} while(running);
		
		SCAN.close();
		
		exit();

    }
	
    public void exit() {
    	outl("============================================================");
		outl("==================== Program terminated ====================");
		outl("============================================================", 1);
	}
}
