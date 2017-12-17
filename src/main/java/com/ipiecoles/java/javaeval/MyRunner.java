
package com.ipiecoles.java.javaeval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipiecoles.java.javaeval.service.EntrepriseService;

import java.util.Arrays;
import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner, Outputter {
	
	@Autowired
	private EntrepriseService entrepriseService;

    @Override
    public void run(String... strings) throws Exception {
    		
    	Scanner scan = new Scanner(System.in);
		
		outl("==================================", 0);
		outl("====== Welcome to Hibernate ======");
		outl("==================================", 1);

		outImportant("Enter '?' at any time to restate available actions.");
		outImportant("Enter 'exit' or '' to quit.", 1);
		
		Suggestion suggestion = new Suggestion(entrepriseService, Arrays.asList(State.LIST, State.CREATE, State.EDIT, State.DELETE));
		
		boolean running;
		
		do {
			outl("");
			outInput("");
			running = suggestion.nextLine(scan);
			
		} while(running);
		
		scan.close();
		
		exit();

    }
	
    public void exit() {
    	outl("============================================================");
		outl("==================== Program terminated ====================");
		outl("============================================================", 1);
	}
}
