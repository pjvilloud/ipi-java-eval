package com.ipiecoles.java.javaeval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.joda.time.LocalDate;

import com.ipiecoles.java.javaeval.exceptions.CadreException;
import com.ipiecoles.java.javaeval.exceptions.TechnicienException;
import com.ipiecoles.java.javaeval.model.Cadre;
import com.ipiecoles.java.javaeval.model.Commercial;
import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.model.Manager;
import com.ipiecoles.java.javaeval.model.Technicien;
import com.ipiecoles.java.javaeval.repository.CadreRepository;
import com.ipiecoles.java.javaeval.repository.EmployeRepository;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;
import com.ipiecoles.java.javaeval.service.CadreService;
import com.ipiecoles.java.javaeval.service.EmployeService;
import com.ipiecoles.java.javaeval.service.EntrepriseService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
	private CadreService cadreService;
    @Autowired
	private EmployeService employeService;
    @Autowired
	private EntrepriseService entrepriseService;
    
    public static final Scanner scanner = new Scanner(System.in);

    // Prises de notes d'aide 
	/** Je nettoie le runner de tout son contenu pour ne garder que public void run throws exception
	 * et public static void print object t*/
	/** Bien regarde le TP du sudoku pour savoir comment faire, regarder comment etait fait le runner ou la classe main*/ 
	/** On injecte ici (avec les autowired???)*/
		/** public void run est le point d entree
	 * donc faire un mssage d accueil : hello
	 * ensuite proposition d un menu avec les possibilites
	 * systeme print ouln avec 0 creer entreprise, 1 recuperer blabla, 2 tititit et du coup quand on tape 0 et entree, ca appelle la fonction automatiquement  
	 * du coup apres on arrive par exemple sur une autre fonction ou il demande le nom, le prenom, ... et quand on fait entrer ca fait la fonction
	 * et eventuellement ca renvoie au debut*/
    @Override
    public void run(String... strings) throws Exception {
    	
    	//Tuto spring = ABANDON 
    	//http://www.objis.com/formation-java/tutoriel-spring-acces-donnees-jdbc-dao-jdbctemplate.html
    	//https://dzone.com/tutorials/java/spring/spring-hibernate-integration-1.html
    	//http://rpouiller.developpez.com/tutoriels/spring/application-web-spring-hibernate/
    	// ABANDON TROP COMPLIQUE POUR LE MOMENT
    	
    	// INUTILE 
    	// Initialisation de la connexion: 
    	//Connection connexion = initConnection();
        //Statement statement = connexion.createStatement();
       /** Toujours utiliser le maven boot spring run */
    	//System.out.println("test");
    	
    	/** les appels ici des injections au dessus */ 
    	
    	//INUTILE SPRING LE FAIT TOUT SEUL 
    	// Definitions des repository: 
        //EntrepriseService entrepriseService = new EntrepriseService();
        //entrepriseService.setEntrepriseRepository(this.entrepriseRepository);
        
        //CadreService cadreservice = new CadreService();
        //cadreservice.setCadreRepository(this.cadreRepository);
        
        //EmployeService empserv = new EmployeService();
        //empserv.setEmployeRepository(this.employeRepository);
             
        //Entreprise entreprise = new Entreprise();  
        
        // INUTILE 
        // Connexion à la base de données. Ma base de données s'appelle entrepriseeval et non entreprise
//        public java.sql.Connection initConnection(){
//            String url = "jdbc:mysql://localhost:3306/entrepriseeval";
//            String user = "root";
//            String pwd = "root";
    //
//            java.sql.Connection connexion = null;
    //
//            try {
//                connexion = java.sql.DriverManager.getConnection(url, user, pwd);
//            } catch ( java.sql.SQLException e ) {
//                //Problème de connexion à la base !
//            	System.out.println(e.getMessage());
//            }
//            return connexion;
//        }
    	
    	
    	// ----------------------------------------Debut de notre programme ---------------------------------
        //Affichage menu principal
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	System.out.println("--------------------------------------------------------------");
    	System.out.println("--------------------------------------------------------------");
    	System.out.println("Bienvenue Monsieur le Président, gérez ici vos entreprises");
    	System.out.println("--------------------------------------------------------------");
    	System.out.println("--------------------------------------------------------------");
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        menuArrivee();
    }
    
    public static void print(Object t) {
        System.out.println(t);
    }
    
    private void displayMenuPrincipal() {
    	System.out.println("--------------------------------------------------");
    	System.out.println("Que voulez-vous faire maintenant?");
    	System.out.println("--------------------------------------------------");
    	System.out.println("1. Lister les entreprises existantes");
    	System.out.println("2. Gérer une entreprise existante");
    	System.out.println("3. Créer une nouvelle entreprise");
    	System.out.println("4. Quitter l'application");
    }
    
    private void menuArrivee() throws NumberFormatException, TechnicienException, CadreException {
    	displayMenuPrincipal();
    	//Mise en place du scanner pour les inputs: 
        String choice = getIntfromInput();
        //Premier menu
        switch (choice) {
            case "1":
            	// lister les entreprises existantes
            	menuListerEntreprise();
                break;      	
            case "2":
                // Charger une entreprise existante en fonction de son nom
            	menuChargerEntreprise();
                break;
            case "3":                
            	// creer une entreprise 
            	menuCreerEntreprise();  
           	 	break;
            case "4":
            	// quitter l'application
            	System.out.println("Merci d'avoir utilisé cette application. Au revoir");
            	break;
            default:
                // si erreur de saisie 
            	defaultMenuArrivee();
        }
    }
    
    void defaultMenuArrivee() throws NumberFormatException, TechnicienException, CadreException {
       	System.out.println("Votre choix ne correspond pas aux menus disponibles. Que souhaitez-vous faire?"); 
    	System.out.println("1. Revenir au menu principal");
    	System.out.println("2. Quitter l'application");
    	String choice2 = getIntfromInput();
    	switch (choice2) {
             case "1":
            	 // retourner menu principal
             	menuArrivee();
                 break;
             case "2":
                 // quitter l application
            	 System.out.println("Merci d'avoir utilisé cette application. Au revoir");
                 break;
             default:
                 // par defaut on quitte l application
             	System.out.println("Votre choix ne correspond à aucune des possibilités, l'application se termine");                  	
    	}
    }
    
    public String getIntfromInput() {
    	return scanner.nextLine();
    }
    
    public void menuCreerEntreprise() throws NumberFormatException, TechnicienException, CadreException {
    	System.out.println("3. Créer une nouvelle entreprise");
    	System.out.println("Veuillez entrer le nom de votre nouvelle entreprise : ");
    	String choice = getIntfromInput();
    	Entreprise entreprise = new Entreprise(); 
    	entreprise.setNom(choice);
    	entrepriseService.creerEntreprise(entreprise);
    	System.out.println("L'entreprise '"+choice+"' a bien été créée");
    	menuArrivee();
    }
    
    public void menuListerEntreprise() throws NumberFormatException, TechnicienException, CadreException {
    	System.out.println("Voici la liste des entreprises disponibles : ");
        Iterable<Entreprise> entreprises = entrepriseService.listerEntreprises();
        for(Iterator<Entreprise> it=entreprises.iterator(); it.hasNext();) { 
        	System.out.println(it.next());   
        }
        menuArrivee();
    }
    
    public void menuListerEntreprise2() {
    	System.out.println("Voici la liste des entreprises disponibles : ");
        Iterable<Entreprise> entreprises = entrepriseService.listerEntreprises();
        for(Iterator<Entreprise> it=entreprises.iterator(); it.hasNext();) { 
        	System.out.println(it.next());   
        }
    }
    
    public void menuChargerEntreprise() throws NumberFormatException, TechnicienException, CadreException {
    	System.out.println("2. Gérer une entreprise existante");
    	menuListerEntreprise2();
    	Entreprise entreprise = new Entreprise();
    	System.out.println("Veuillez entrer le nom de votre entreprise : ");
    	String nom = getIntfromInput();
    	entreprise = entrepriseService.findByNom(nom);
    	//System.out.println(entreprise);
    	// si l entreprise existe
        if (entreprise != null) {
        // on recupere la liste des employes de cette entreprise
        List<Employe> employes = (entrepriseService.listerEmployes(entreprise));
            for(Iterator<Employe> it=employes.iterator(); it.hasNext();) {
            entreprise.ajouterEmploye(it.next()); }
            menuEntrepriseArrivee(nom);
        }
        else {
        	System.out.println("Cette entreprise n'existe pas");
        	menuChargerEntreprise();
        }
    }
    
    public void menuEntrepriseArrivee(String nom) throws NumberFormatException, TechnicienException, CadreException {
    	System.out.println("--------------------------------------------------");
    	System.out.println("--------------------------------------------------");
    	System.out.println("Bienvenue dans l'entreprise "+nom+" :");
    	System.out.println("--------------------------------------------------");
    	System.out.println("--------------------------------------------------");
    	choixMenuEntreprise();
    }
    
    public void choixMenuEntreprise() throws NumberFormatException, TechnicienException, CadreException {
    	System.out.println("Que voulez-vous faire maintenant?");
    	System.out.println("1. Ajouter un employé à l'entreprise");
    	System.out.println("2. Quitter l'application");
    	String choice = getIntfromInput();
    	switch (choice) {
	        case "1":
	        	// 1. Ajouter un employé à l'entreprise
	        	menuAddEmployeEnt();  
	        case "2":
	        	// 2. quitter l application
	        	System.out.println("Merci d'avoir utilisé cette application. Au revoir");
	            break;
	        default:
	            // si erreur de saisie 
	        	defaultMenuEntreprise();
	    }
    }
    
    void defaultMenuEntreprise() throws NumberFormatException, TechnicienException, CadreException {
       	System.out.println("Votre choix ne correspond pas aux menus disponibles. Que souhaitez-vous faire?"); 
    	System.out.println("1. Revenir au menu principal");
    	System.out.println("2. Quitter l'application");
    	String choice2 = getIntfromInput();
    	switch (choice2) {
             case "1":
            	 // retourner menu principal
            	 choixMenuEntreprise();
                 break;
             case "2":
                 // quitter l application
            	 System.out.println("Merci d'avoir utilisé cette application. Au revoir");
                 break;
             default:
                 // par defaut on quitte l application
             	System.out.println("Votre choix ne correspond à aucune des possibilités, l'application se termine");                  	
    	}
    }
    
    public void menuAddEmployeEnt() throws NumberFormatException, TechnicienException, CadreException {
    	System.out.print("Nom : ");
    	String nom = getIntfromInput();
    	System.out.print("Prénom : ");
    	String prenom = getIntfromInput();
    	System.out.print("Date d'embauche : (jj-mm-aa)");
    	String date = getIntfromInput();
    	LocalDate dateembauche = new LocalDate(date);
    	//System.out.print("Temps partiel : (1 : oui, 2 : non ): ");
    	//String tempspartiel = getIntfromInput();
    	System.out.print("Matricule : ");
   	 	String matricule = getIntfromInput();
   	 	System.out.print("Salaire : ");
   	 	String salaire = getIntfromInput();
   	 	System.out.print("Type d'employe ? (1 : Cadre, 2 : Manager, 3 : Commercial, 4 : Technicien) ");
   	    String choice = getIntfromInput();
   	    Entreprise entreprise = new Entreprise();
   	    switch (choice) {
   	    	case "1":
   	    		// 1 = Cadre
   	    		System.out.print("Coefficient : (1 - 2 - 3 - 4 - 5) :");
   	    	 	String coefficient = getIntfromInput();
   	    	 	System.out.print("Nombre de jours de RTT : ");
	    	 	String rtt = getIntfromInput();
   	    		Employe cadre = new Cadre(nom, prenom, matricule, dateembauche, Double.parseDouble(salaire), Integer.valueOf(coefficient), Integer.valueOf(rtt));
   	    		entrepriseService.ajouterEmployeAEntreprise(cadre, entreprise); 
   	    		choixMenuEntreprise() ;
   	    	case "2":
   	    		// 2 = manager
   	    		Employe manager = new Manager(nom, prenom, matricule, dateembauche, Double.parseDouble(salaire));
   	    		entrepriseService.ajouterEmployeAEntreprise(manager, entreprise); 
   	    		choixMenuEntreprise() ;
   	    	case "3":
   	    		// 3 = commercial
   	    		System.out.print("CA annuel : ");
   	    	 	String caAnnuel = getIntfromInput();
   	    	 	System.out.print("Performance : (0 - 50 - 100 - 150 - 200):");
	    	 	String performance = getIntfromInput();
   	    		Employe commercial = new Commercial(nom, prenom, matricule, dateembauche, Double.parseDouble(salaire), Double.parseDouble(caAnnuel), Integer.valueOf(performance));
   	    		entrepriseService.ajouterEmployeAEntreprise(commercial, entreprise); 
   	    		choixMenuEntreprise() ;
   	    	case "4": 
   	    		// 4 = technicien 
   	    		System.out.print("Grade : (1 - 2 - 3 - 4 - 4) :");
	    	 	String grade = getIntfromInput();
	    	 	Employe technicien = new Technicien(nom, prenom, matricule, dateembauche, Double.parseDouble(salaire), Integer.valueOf(grade));
   	    		entrepriseService.ajouterEmployeAEntreprise(technicien, entreprise); 
   	    		choixMenuEntreprise();
   	    }
	  
}
}
