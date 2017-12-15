package com.ipiecoles.java.javaeval;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.repository.EmployeRepository;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;
import com.ipiecoles.java.javaeval.service.EmployeService;
import com.ipiecoles.java.javaeval.service.EntrepriseService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
	private EmployeRepository employeRepository;
    @Autowired
	private EntrepriseRepository entrepriseRepository;

    @Override
    public void run(String... strings) throws Exception {
        // ICI DU CODE QUI ETAIT DANS LE PROJET AU DEPART, je l'ai laissé dans le doute...
        // mais je pense qu'il ne nous sert pas 
        Connection connexion = initConnection();
        Statement statement = connexion.createStatement();

        // initialisation des services + on leur attribue les repository
        EntrepriseService etsserv = new EntrepriseService();
        etsserv.setRepository(this.entrepriseRepository);
        etsserv.setEmployeRepository(this.employeRepository);

        EmployeService empserv = new EmployeService();
        empserv.setRepository(this.employeRepository);

        // Stuff declaration
        Scanner sc = new Scanner(System.in);
        Entreprise ets = new Entreprise();
        String choix="0";
        
        // Affichage liste des commandes 
        System.out.println("Quelle est votre demande ? \n 1- Créer une entreprise \n 2- Gérer une entreprise \n 3- Quitter \n");

        // tant que choix != de 3
        while (!choix.equals("3")) {
            choix = sc.nextLine();
            
            // Choix = creer entreprise
            if (choix.equals("1")) {
            	System.out.println("Entrez un nom d'entreprise");
            	String ets_name = sc.nextLine();
            	ets.setNom(ets_name); // set nom de l'entreprise
	            etsserv.creerEntreprise(ets); // liaison avec la BDD
            }
            
            // Choix = gerer une entreprise
            else if (choix.equals("2")) {
            	
            	boolean etsValide = false;

            	// demande de nom tant que nom entreprise est invalide
            	while (!etsValide) {
            		System.out.println("Quelle est le nom de l'entreprise ?");
            		String ets_name = sc.nextLine();
		            ets = etsserv.findByNom(ets_name); // fonction du service

                    // si entreprise existe
		            if (ets != null) {
		            	
		            	// interrogation du service pour recuper liste employe 
			            List<Employe>emp = (etsserv.getEmployes(ets));

			            for(Iterator<Employe> it=emp.iterator(); it.hasNext();) 
			            	ets.addEmploye(it.next());

			            etsValide = true;
		            } else {
		            	System.out.println("Entreprise non trouvée. Rentrez un nom d'entreprise valide.");
		            }
		            
            	}//end while donc estValide = true, on continue par gestion d'entreprise

            	boolean continuerGererEntreprise = true;

            	// tant que gestion n'est pas false (pas terminée)
            	while (continuerGererEntreprise ) {
            		System.out.println("Gérer l'entreprise : \n 1-Ajouter un employé \n 2- Liste des employés \n 3- Compter les employés \n 4- Augmenter un employé \n 5- Afficher la masse salariale \n 6- Rechercher un employé \n 7- Retour" );
            		String choixGestion = sc.nextLine();
            		
            		// choixGestion = ajouter un employé
            		if (choixGestion.equals("1")) {
            			
            			// récupération des données de l'employé
	   		             System.out.println("Nom : ");
	   		         	 String nom = sc.nextLine();
	   		
	   		             System.out.println("Prénom : ");
	   		         	 String prenom = sc.nextLine();
	   		
	   		             System.out.println("Date d'embauche : (jj-mm-aa)");
	   		         	 String date = sc.nextLine();
	   		         	 LocalDate datemb = new LocalDate(date);
	   		         	 
			             System.out.println("Matricule : ");
			         	 String mat = sc.nextLine();
			         	 
			         	 System.out.println("Salaire : ");
			         	 String salaire = sc.nextLine();
			         	 
			         	 // création  de l'employé
			         	Employe emp = new Employe(nom,prenom,mat,datemb,Double.valueOf(salaire));
			         	
			         	// ajout de l'employé à la BDD avec le service de l'entreprise
		            	 etsserv.addEmployeToEntreprise(emp, ets);
            		}
            		
            		// choixGestion = liste des employés
            		else if (choixGestion.equals("2")) {
            			
            			// liste qui contiendra résultat de la requête
    		        	List<Employe>emp = (etsserv.getEmployes(ets));
    		        	
    		        	for(int i= 0; i < 3; i++)print(" ");
    		        	
    		        	System.out.println("Liste des employés : ");
    		        	System.out.println(" ");

    		        	for(Iterator<Employe> it=emp.iterator(); it.hasNext();) 
    		        		System.out.println(it.next().toString()); 
    		        		System.out.println(" ");
            		}
            		
            		// choixGestion = compter employés
            		else if (choixGestion.equals("3")) {
                        // appel du service
            			System.out.println("Nombre d'employés : "+ etsserv.countEmployes(ets));
            		}
            		
            		// choixGestion = augmenter un employé
						else if (choixGestion.equals("4")) {

            		    // Récupération des infos de l'employé
                        System.out.println("Nom : ");
                        String nom = sc.nextLine();
						
                        System.out.println("Prénom : ");
                        String prenom = sc.nextLine();
						 
                        System.out.println("Pourcentage d'augmentation : ");
                        String pourcentage = sc.nextLine();
						 
                        // Récupération de l'employé en appelant le service
                        Employe emp = empserv.findByNomAndPrenom(nom, prenom);
						 
                        // calcul du nouveau salaire
                        Double anciensal = emp.getSalaire();
                        emp.augmenterSalaire(Double.valueOf(pourcentage));
						 
                        // Modification du salaire dans la BDD
                        empserv.updateEmploye(emp);
                        System.out.println(" ");
                        System.out.println(" Ancien salaire : "+anciensal);
                        System.out.println(" Nouveau salaire : "+emp.getSalaire());
						 
            		}
            		
            		// choixGestion = calculer la masse salariale
            		else if (choixGestion.equals("5")) {
            		    // appel du service
                        System.out.println(" La masse salariale de votre entreprise est : "+ etsserv.calculerMasseSalariale(ets));
            		}
            		
            		// choixGestion = rechercher un employé
            		else if (choixGestion.equals("6")) {
            		    // Récupération des infos de l'employé
            			System.out.println(" Nom : ");
                        String nom = sc.nextLine();
			
			         	System.out.println(" Prénom : ");
                        String prenom = sc.nextLine();

                        // appel du service après stockage des infos dans un objet
                        Employe emp = empserv.findByNomAndPrenom(nom, prenom);

                        // si employé existe ou pas
                        if(emp != null) {
                            System.out.println(emp.toString());
                        } else {
                            System.out.println(" Cet employé n'existe pas. Réessayez ! ");
                        }
            		}
            		
            		// choixGestion = quitter
            		else if (choixGestion.equals("7")) {
            			continuerGererEntreprise = false;
            		}
            	} 
            }
        }
        
        // choix = quitter
        sc.close();
        System.out.println("Fin du programme. Appuyer sur (X) pour quitterla console...");
        
    }

    // ICI DU CODE QUI ETAIT DANS LE PROJET AU DEPART, je l'ai laissé dans le doute...
    // mais je pense qu'il ne nous sert pas 
    //Connexion à la BDD
    public java.sql.Connection initConnection(){
        String url = "jdbc:mysql://localhost:3306/entreprise";
        String user = "root";
        String pwd = "root";

        java.sql.Connection connexion = null;

        try {
            connexion = java.sql.DriverManager.getConnection(url, user, pwd);
        } catch ( java.sql.SQLException e ) {
            // Problème de connexion à la base !
            print(e.getMessage());
        }
        return connexion;
    }

    public static void print(Object t) {
        System.out.println(t);
    }
}
