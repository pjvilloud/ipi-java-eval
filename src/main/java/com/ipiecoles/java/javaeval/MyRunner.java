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
import java.sql.Connection;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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

        EntrepriseService etsserv = new EntrepriseService();
        etsserv.setRepository(this.entrepriseRepository);
        etsserv.setEmployeRepository(this.employeRepository);
        
        CadreService cadreserv = new CadreService();
        cadreserv.setRepository(this.cadreRepository);
        
        EmployeService empserv = new EmployeService();
        empserv.setRepository(this.employeRepository);
        
        //Déclaration variables utiles //
        Scanner scan = new Scanner(System.in);
        Entreprise ets = new Entreprise();
        boolean exit = false;
        
        while(!exit) {
        	
        	//Affichage de la page de creation ou
            print("Que voulez-vous faire? ? ");
            print("1. Créer votre entreprise");
            print("2. Edité une entreprise ");
	      
            String console = scan.nextLine();
	        
	        if(console.equals("1")) {
	        	print("Veuillez choisir le nom de votre entreprise : ");
	        	String ets_name = scan.nextLine();
	            ets.setNom(ets_name);
	            etsserv.creerEntreprise(ets);
	
	        }
	        else {
	        	boolean etsvalide = false;
	        	while(!etsvalide) {
		        	// Page de recherche du nom d'une entreprise existante
	            	print("Veuillez entrer le nom de votre entreprise que vous souhaitez modifier");
	        		
		            String ets_name = scan.nextLine();
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
		            	print("Cette entreprise n'existe pas!");
		            }
	        	}
	        }
	        boolean continueEts = true;
	        while(continueEts){
		        // Page principale de gestion de l'entreprise
	            print("Quelle action voulez vous effectuer ?");
	            print("1. Ajouter un nouvel employé");
	            print("2. Afficher la liste des employés");
	            print("3. Compter les employés");
	            print("4. Augmenter le salaire d'un employé");
	            print("5. Afficher la masse salariale");
	            print("6. Rechercher un employé");
	        	
		        console = scan.nextLine();
		        
		        //Ajouter un employé à l'entreprise
		        if(console.equals("1")) {
		        	 //Page d'ajout d'un employé
		        	 print(" Veuillez renseigner les informations suivantes : ");
		
		             System.out.print("Nom : ");
		         	 String nom = scan.nextLine();
		
		             System.out.print("Prénom : ");
		         	 String prenom = scan.nextLine();
		
		             System.out.print("Date d'embauche : (jj-mm-aa)");
		         	 String date = scan.nextLine();
		         	 LocalDate datemb = new LocalDate(date);
		         	 
		
		             System.out.print("Matricule : ");
		         	 String mat = scan.nextLine();
		         	 
		         	 System.out.print("Salaire : ");
		         	 String salaire = scan.nextLine();
		         	         	
		             print("Type d'employe ? (1 : Cadre, 2 : Manager, 3 : Commercial, 4 : Technicien) ");
		             console = scan.nextLine();
		             if(console .equals("1")) {
		            	 Cadre emp = null;
		            	 while(emp == null) {
			            	 print("Indiquez son coefficient : ");
			            	 try {
			            		 	emp = new Cadre(nom,prenom,mat,datemb,Double.valueOf(salaire),Integer.valueOf(scan.nextLine()));
			            	 }catch(CadreException e) {}
		            	
			             }	 
			            	 etsserv.addEmployeToEntreprise(emp, ets);
		             }
		             else if (console.equals("2")) {
		            	 Manager emp = new Manager(nom,prenom,mat,datemb,Double.valueOf(salaire));
		            	 etsserv.addEmployeToEntreprise(emp, ets);
		             }
		             else if(console.equals("3")) {
		            	 Commercial emp = new Commercial(nom,prenom,mat,datemb,Double.valueOf(salaire),0.0);
		            	 etsserv.addEmployeToEntreprise(emp, ets);
		             }
		             else if(console.equals("4")) {
			         	 Technicien emp = null;
			         	 while(emp==null) {
			            	 print("Indiquez son grade : ");
			            	 Integer grade = Integer.valueOf(scan.nextLine());
			            	 try {
			            		 	emp = new Technicien(nom,prenom,mat,datemb,Double.valueOf(salaire),grade);
			            	 }catch(TechnicienException e) {}
			         	 }
		                 etsserv.addEmployeToEntreprise(emp, ets);
		             }
		        }
		        
		        //Lister les employés de l'entreprise
		        else if (console.equals("2")) {
		        	List<Employe>emp = (etsserv.getEmployes(ets));
		        	for(int i=0; i<3;i++)print(" ");//De l'espace
		        	
		        	print("Les employés de votre entreprise sont : ");
		        	print(" ");
		        	for(Iterator<Employe> it=emp.iterator(); it.hasNext();) 
		                print(it.next().toString()); 
		        		print(" ");
		            }
		        
		        // Compter le nombre d'employés de l'entreprise
		        else if (console.equals("3")) {        	
		        	print("Le nombre d'employés dans votre entreprise est : "+ etsserv.countEmployes(ets));
		        }
		        
		        //Augmenter un employé
		        else if (console.equals("4")) {
		        	 
		        	//Page augmenter un employé
		
		             System.out.print("Nom : ");
		         	 String nom = scan.nextLine();
		
		             System.out.print("Prénom : ");
		         	 String prenom = scan.nextLine();
		         	 
		         	 System.out.print("Pourcentage d'augmentation : ");
		         	 String pourcentage = scan.nextLine();
		         	 
		         	 Employe emp = empserv.findByNomAndPrenom(nom, prenom);
		         	 Double anciensal = emp.getSalaire();
		         	 emp.augmenterSalaire(Double.valueOf(pourcentage));
		         	 empserv.updateEmploye(emp);
		         	 print("Ancien salaire : "+ anciensal);
		         	 print("Nouveau salaire : "+ emp.getSalaire());
		        }
		        
		        //Calculer la masse salariale
		        else if (console.equals("5")) {
		        	print("La masse salariale de votre entreprise est : "+ etsserv.calculerMasseSalariale(ets));
		        }
		        
		        //Chercher un employé
		        else if (console.equals("6")) {
		        	 boolean employeexist = false;
		        	 while(!employeexist) {
			        	 //Page chercher un employé
		        		 
			
			             System.out.print("Nom : ");
			         	 String nom = scan.nextLine();
			
			             System.out.print("Prénom : ");
			         	 String prenom = scan.nextLine();
			         	 
			         	 Employe emp = empserv.findByNomAndPrenom(nom, prenom);
			         	 if(emp != null) {
				         	 print(emp.toString());
				         	 employeexist = true;
			         	 }
			         	 else {
			         		 print("Cet employé n'existe pas, tentez un autre ! ");
			         	 }
		        	 }
		        }
		        else
		        {
		        	print("Erreur ! ");
		        }

		        print("Voulez-vous continuer à gérer votre entreprise, revenir au menu ou quitter l'application?");
		        print("1. Gérer mon entreprise");
		        print("2. Revenir au menu principal");
		        print("3. Quitter l'application");
		        console = scan.nextLine();
		        if(console.equals("3")) {exit=true;}
		        if(console.equals("2")) {continueEts = false;}
		        
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

