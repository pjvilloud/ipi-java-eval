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
import java.util.Scanner;
import java.util.Set;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
	private CadreRepository cadreRepository;
    @Autowired
	private EmployeRepository employeRepository;
    @Autowired
	private EntrepriseRepository entrepriseRepository;

    @Override
    public void run(String... strings) throws Exception {
        Connection connexion = initConnection();
        Statement statement = connexion.createStatement();

        // INITIALISATION DES SERVICES //
        EntrepriseService etsserv = new EntrepriseService();
        etsserv.setRepository(this.entrepriseRepository);
        etsserv.setEmployeRepository(this.employeRepository);
        
        CadreService cadreserv = new CadreService();
        cadreserv.setRepository(this.cadreRepository);
        
        EmployeService empserv = new EmployeService();
        empserv.setRepository(this.employeRepository);
        //*******************************//
        
        //Déclaration variables utiles //
        Scanner sc = new Scanner(System.in);
        Entreprise ets = new Entreprise();
        boolean exit = false;
        
        while(!exit) {
        	
        	Tools.affichage1();//Affichage de la page de démarrage de l'application
	        String choice = sc.nextLine();
	        print(choice.getClass().toString());
	        
	        if(choice.equals("1")) {
	        	
	        	Tools.affichage2();//Affichage choix créer entreprise ou utiliser existante
	        	String ets_name = sc.nextLine();
	            ets.setNom(ets_name);
	            etsserv.creerEntreprise(ets);
	
	        }
	        else {
	        	boolean etsvalide = false;
	        	while(!etsvalide) {
		        	Tools.affichage3(); // Page de recherche du nom d'une entreprise existante
		            String ets_name = sc.nextLine();
		            ets = etsserv.findByNom(ets_name);
		            	
		            if (ets != null) {
		            //On récupère la liste des employés
			            List<Employe>emp = (etsserv.getEmployes(ets));
			            for(Iterator<Employe> it=emp.iterator(); it.hasNext();) 
			            	ets.addEmploye(it.next()); 
			            etsvalide = true;
		            }
		            else {
		            	print(" ");print(" ");
		            	print("Cette entreprise n'existe pas entrez un nom correct !");
		            }
	        	}
	        }
	        boolean continueEts = true;
	        while(continueEts){
		        Tools.affichage4(); // Page principale de gestion de l'entreprise
		        choice = sc.nextLine();
		        
		        //Ajouter un employé à l'entreprise
		        if(choice.equals("1")) {
		        	 Tools.affichage5();//Page d'ajout d'un employé
		
		             System.out.print("Nom : ");
		         	 String nom = sc.nextLine();
		
		             System.out.print("Prénom : ");
		         	 String prenom = sc.nextLine();
		
		             System.out.print("Date d'embauche : (jj-mm-aa)");
		         	 String date = sc.nextLine();
		         	 LocalDate datemb = new LocalDate(date);
		         	 
		
		             System.out.print("Matricule : ");
		         	 String mat = sc.nextLine();
		         	 
		         	 System.out.print("Salaire : ");
		         	 String salaire = sc.nextLine();
		         	         	
		             print("Type d'employe ? (1 : Cadre, 2 : Manager, 3 : Commercial, 4 : Technicien) ");
		             choice = sc.nextLine();
		             if(choice .equals("1")) {
		            	 Cadre emp = null;
		            	 while(emp == null) {
			            	 print("Indiquez son coefficient : ");
			            	 try {
			            		 	emp = new Cadre(nom,prenom,mat,datemb,Double.valueOf(salaire),Integer.valueOf(sc.nextLine()));
			            	 }catch(CadreException e) {}
		            	
			             }	 
			            	 etsserv.addEmployeToEntreprise(emp, ets);
		             }
		             else if (choice.equals("2")) {
		            	 Manager emp = new Manager(nom,prenom,mat,datemb,Double.valueOf(salaire));
		            	 etsserv.addEmployeToEntreprise(emp, ets);
		             }
		             else if(choice.equals("3")) {
		            	 Commercial emp = new Commercial(nom,prenom,mat,datemb,Double.valueOf(salaire),0.0);
		            	 etsserv.addEmployeToEntreprise(emp, ets);
		             }
		             else if(choice.equals("4")) {
			         	 Technicien emp = null;
			         	 while(emp==null) {
			            	 print("Indiquez son grade : ");
			            	 Integer grade = Integer.valueOf(sc.nextLine());
			            	 try {
			            		 	emp = new Technicien(nom,prenom,mat,datemb,Double.valueOf(salaire),grade);
			            	 }catch(TechnicienException e) {}
			         	 }
		                 etsserv.addEmployeToEntreprise(emp, ets);
		             }
		        }
		        
		        //Lister les employés de l'entreprise
		        else if (choice.equals("2")) {
		        	List<Employe>emp = (etsserv.getEmployes(ets));
		        	for(int i=0; i<3;i++)print(" ");//De l'espace
		        	
		        	print("Les employés de votre entreprise sont : ");
		        	print(" ");
		        	for(Iterator<Employe> it=emp.iterator(); it.hasNext();) 
		                print(it.next().toString()); 
		        		print(" ");
		            }
		        
		        // Compter le nombre d'employés de l'entreprise
		        else if (choice.equals("3")) {        	
		        	print("Le nombre d'employés dans votre entreprise est : "+ etsserv.countEmployes(ets));
		        }
		        
		        //Augmenter un employé
		        else if (choice.equals("4")) {
		        	 Tools.affichage6();//Page augmenter un employé
		
		             System.out.print("Nom : ");
		         	 String nom = sc.nextLine();
		
		             System.out.print("Prénom : ");
		         	 String prenom = sc.nextLine();
		         	 
		         	 System.out.print("Pourcentage d'augmentation : ");
		         	 String pourcentage = sc.nextLine();
		         	 
		         	 Employe emp = empserv.findByNomAndPrenom(nom, prenom);
		         	 Double anciensal = emp.getSalaire();
		         	 emp.augmenterSalaire(Double.valueOf(pourcentage));
		         	 empserv.updateEmploye(emp);
		         	 print(" **********************************");
		         	 print(" Ancien salaire : "+anciensal);
		         	 print(" Nouveau salaire : "+emp.getSalaire());
		        }
		        
		        //Calculer la masse salariale
		        else if (choice.equals("5")) {
		        	print(" La masse salariale de votre entreprise est : "+ etsserv.calculerMasseSalariale(ets));
		        }
		        
		        //Chercher un employé
		        else if (choice.equals("6")) {
		        	 boolean employeexist = false;
		        	 while(!employeexist) {
			        	 Tools.affichage7();//Page chercher un employé
			
			             System.out.print(" Nom : ");
			         	 String nom = sc.nextLine();
			
			             System.out.print(" Prénom : ");
			         	 String prenom = sc.nextLine();
			         	 
			         	 Employe emp = empserv.findByNomAndPrenom(nom, prenom);
			         	 if(emp != null) {
				         	 print(" **********************************");
				         	 print(emp.toString());
				         	 employeexist = true;
			         	 }
			         	 else {
			         		 print(" ");print(" ");
			         		 print(" Cet employé n'existe pas, tentez un autre ! ");
			         		 print(" ");print(" ");
			         	 }
		        	 }
		        }
		        else
		        {
		        	print(" Mauvais choix ! ");
		        }
		        
		        print(" *******************************************");
		        print(" Voulez-vous continuer à gérer votre entreprise, revenir au menu ou quitter l'application?");
		        print(" 1. Gérer mon entreprise");
		        print(" 2. Revenir au menu principal");
		        print(" 3. Quitter l'application");
		        choice = sc.nextLine();
		        if(choice.equals("3")) {exit=true;}
		        if(choice.equals("2")) {continueEts = false;}
		        
	        }
        }
    }

    public java.sql.Connection initConnection(){
        String url = "jdbc:mysql://localhost:3306/entreprise";
        String user = "root";
        String pwd = "root";

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

