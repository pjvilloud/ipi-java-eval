
package com.ipiecoles.java.javaeval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.service.EntrepriseService;

import java.util.List;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {
	
	@Autowired
	private EntrepriseService entrepriseService;
	
	private boolean running = true;

    @Override
    public void run(String... strings) throws Exception {
        Connection connexion = initConnection();
        if(connexion != null) {
        	
        	Scanner scan = new Scanner(System.in);
    		
    		outl("==================================", 0);
    		outl("====== Welcome to TextBased ======");
    		outl("==================================", 1);

    		outl("Enter '?' at any time to restate available actions.");
    		outl("Enter 'exit' or '' to quit.", 1);
    		
    		suggestion = new Suggestion(entrepriseService, Arrays.asList(State.LIST, State.CREATE, State.EDIT, State.DELETE));
    		
    		while(running) {
    			
    			outl("");
    			
    			out("> ");
    			String input = scan.nextLine().replaceAll("\\s", "");
    			
    			outl(new String(new char[90]).replaceAll("\0", "~"), 0);
    			outl("");

    			switch(input) {
    				case "exit":
    				case "":
    					exit();
    					break;
    				case "?":
    					suggestion.state();
    					break;

    				default:
    					parseInput(input);
    			}
    		}
    		
    		scan.close();
        	
        } else {
        	System.out.println("===== FAILED TO CONNECT TO DATABASE =====");
        }
        
        /*Technicien t = technicienRepository.findOne(4L);
        print(t);

        Manager m = managerRepository.findOneWithEquipeById(43L);
        print(m);
        m.getEquipe().stream().forEach(MyRunner::print);
        m.setPrenom(m.getPrenom().toUpperCase());
        managerRepository.save(m);

        print(employeRepository.count());

        List<Employe> list = employeRepository.findByNomAndPrenom("Adam", "Laura");
        list.stream().map(Employe::toString).forEach(MyRunner::print);

        list = employeRepository.findByDateEmbaucheBefore(new LocalDate(2012,07,28));
        list.stream().map(Employe::toString).forEach(MyRunner::print);

        list = employeRepository.findByDateEmbaucheAfter(new LocalDate(2012,07,28));
        list.stream().map(Employe::toString).forEach(MyRunner::print);

        list = employeRepository.findBySalaireGreaterThanOrderBySalaireDesc(2000.0);
        list.stream().map(Employe::toString).forEach(MyRunner::print);

        list = employeRepository.findByNomOrPrenomAllIgnoreCase("adam");
        list.stream().map(Employe::toString).forEach(MyRunner::print);

        PageRequest pageRequest = new PageRequest(0, 5, Sort.Direction.ASC, "matricule");

        Page<Technicien> techs = technicienRepository.findByNomIgnoreCase("adam", pageRequest);
        while(pageRequest.next()){

            techs.forEach(MyRunner::print);
        }
        print("coucou");
        print("coucou3");
        techs = technicienRepository.findByNomIgnoreCase("adam", pageRequest.next());
        techs.forEach(MyRunner::print);

        print(technicienRepository.findByNomOrPrenomAllIgnoreCase("adam").size());
        print(employeRepository.findByNomOrPrenomAllIgnoreCase("adam").size());*/

    }
    
    private enum State {
    	SELECT,
    	LIST("all"),
    	CREATE,
    	EDIT,
    	DELETE;
    	
    	private String suffix;
    	
    	State() 		{suffix = ""		;}
    	State(String s)	{suffix = " " + s	;}
    	
    	@Override
		public String toString() {
    		return super.toString().toLowerCase() + suffix;
    	}
    }
    
    private class Suggestion {
    	
    	private List<State> states;
    	private EntrepriseService entreprise;
    	private int max;
    	
		public Suggestion(EntrepriseService e, List<State> s) {
			states = s;
			this.entreprise = e;
			init();
		}
		
		public void state() {
			init();
		}
		private void init() {
			outl("What would you like to do?", 1);
			max = 0;
			for(State state : states) {
				outl(actionText(++max));
			}
		}
		private String actionText(int i) {
			return i + ") " + states.get(i-1).toString() + ": " + entreprise.toString();
		}
		
		public void input(int i) {
			outl("=== You have selected: (" + actionText(i) + " ===", 1);
			switch(states.get(i-1)) {
				case LIST:
					listEntities();
				case CREATE:
					break;
				case SELECT:
					break;
				case EDIT:
					break;
				case DELETE:
					break;
			}
		}
		
		private void listEntities() {
			Long num = entreprise.countAllEntreprise();
			
			outl("There are " + num + " " + entreprise.toString() + ".");
			if(num > 0) {
				outl("Here is the list:", 0);
				List<Entreprise> list = entreprise.findAll();
				for(int i=0, l=list.size(); i<l; i++) {
					outl("\t" + i + ": " + list.get(i).getNom());
				}
			}
		}

		public int getMax() {
			return max;
		}
    	
    }
    
    private State state;
    private Suggestion suggestion;
    
    private void parseInput(String input) {
    	try {
    		int num = Integer.parseInt(input);
    		if(1 <= num && num <= suggestion.getMax()) {
    			suggestion.input(num);
    			return;
    		}
    	} catch(NumberFormatException e) {}
    	outl("Please enter a number between 1 and " + suggestion.getMax() + ".");
	}

	public void out(Object s) {
		System.out.print("# " + s);
	}
	public void outl(Object s) {
		out(s + "\n");
	}
	public void outl(Object s, int i) {
		if(i == 0) out("\n");
		outl(s);
		if(i == 1) out("\n");
	}
    public void exit() {
    	outl("============================================================");
		outl("==================== Program terminated ====================");
		outl("============================================================", 1);
		running = false;
	}

    public java.sql.Connection initConnection(){
        String url = "jdbc:mysql://localhost:3306/entreprise?useSSL=false";
        String user = "root";
        String pwd = "password";
        
        java.sql.Connection connexion = null;

        try {
            connexion = java.sql.DriverManager.getConnection(url, user, pwd);
        } catch ( java.sql.SQLException e ) {
            //Problème de connexion à la base !
            print(e.getMessage());
        }
        return connexion;
    }

    public static void print(Object t) {
        System.out.println(t);
    }
}
