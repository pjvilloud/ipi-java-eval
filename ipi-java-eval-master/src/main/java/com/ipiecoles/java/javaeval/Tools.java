package com.ipiecoles.java.javaeval;

public class Tools {
	
	public static void affichage1() {
		for(int i=0; i<30;i++) {print(" ");}
print("		.__                           __.");
print("		\\ `\\~~---..---~~~~~~--.---~~| /  "); 
print("		`~-.   `                   .~         _____ ");
print("		 ~.                .--~~    .---~~~    /");
print("		  / .-.      .-.      |  <~~        __/");
print("		 |  |_|      |_|       \\  \\     .--'");
print("		/-.      -       .-.    |  \\_   \\_");
print("		\\-'   -..-..-    `-'    |    \\__  \\_ ");
print("		 `.                     |     _/  _/");
print("		   ~-                .,-\\   _/  _/");
print("		  /                 -~~~~\\ /_  /_");
print("		 |               /   |    \\  \\_  \\_ ");
print("		 |   /          /   /      | _/  _/");
print("		 |  |          |   /    .,-|/  _/ ");
print("		 )__/           \\_/    -~~~| _/");
print("		   \\                      /  ");
print("		    |           |        /_---` ");
print("		    \\    .______|      ./");
print("		    (   /        \\    /    ");    
print("		    `--'          /__/");

        print("	**************************************************************");
        print("	*************BIENVENUE A ENTREPRISE DB SIMULATOR**************");
        print("	**************************************************************");
        print("                                                              ");
        print("	Que veux-tu faire ? ");
        print("	1. Créer une entreprise");
        print("	2. Gérer une entreprise existante ");
	}
	
	public static void affichage2() {
    	print("	**************************************************************");
    	print("	**************Vous avez choisi de créer une entreprise********");
    	print("	**************************************************************");
    	print("                                                              ");
    	print("	Veuillez choisir un nom pour votre entreprise : ");
	}
	
	public static void affichage3() {
		print(" ");print(" ");
    	print("	**************************************************************");
    	print("	************Vous avez choisi de gérer votre entreprise********");
    	print("	**************************************************************");
    	print("                                                              ");
    	print("	Veuillez entrer le nom de votre entreprise : ");
	}
	
	public static void affichage4() {
        print("	**************************************************************");
        print("	*******Vous entrez dans la gestion de votre entreprise********");
        print("	**************************************************************");
        print("                                                              ");
        print("	Quelle action voulez vous effectuer ?");
        print("	1. Ajouter un nouvel employé");
        print("	2. Lister les employés");
        print("	3. Compter les employés");
        print("	4. Augmenter un des employés");
        print("	5. Afficher la masse salariale");
        print("	6. Rechercher un employé");
	}
	
	public static void affichage5() {
   	 print(" **************************************************************");
     print(" **************Vous allez ajouter un employé*******************");
     print(" **************************************************************");
     print("                                                              ");
     print("  Veuillez renseigner les informations suivantes : ");
	}
	
	
	public static void affichage6() {
   	 print(" **************************************************************");
     print(" **************Vous allez augmenter un employé*****************");
     print(" **************************************************************");
     print("                                                              ");
     print("  Veuillez renseigner les informations suivantes : ");
	}
	
	public static void affichage7() {
   	 print(" **************************************************************");
     print(" **************Vous allez chercher un employé******************");
     print(" **************************************************************");
     print("                                                              ");
     print("  Veuillez renseigner les informations suivantes : ");
	}
	   public static void print(Object t) {
	        System.out.println(t);
	    }
}