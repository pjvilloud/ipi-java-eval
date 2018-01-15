package com.ipiecoles.java.javaeval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ipiecoles.java.javaeval.model.Employe;
import com.ipiecoles.java.javaeval.model.Entreprise;
import com.ipiecoles.java.javaeval.model.Technicien;
import com.ipiecoles.java.javaeval.service.EntrepriseService;
import com.ipiecoles.java.javaeval.service.EmployeService;

import antlr.collections.List;

import com.ipiecoles.java.javaeval.repository.EmployeRepository;
import com.ipiecoles.java.javaeval.repository.EntrepriseRepository;
import com.ipiecoles.java.javaeval.repository.TechnicienRepository;

import ch.qos.logback.core.encoder.EchoEncoder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Component
public class MyRunner implements CommandLineRunner {
	
    @Override
    public void run(String... strings) throws Exception {
    	
    	System.out.println("Bonjour!!!!!!!!!!!!!!!!!!!!!!!!!");
        Connection connexion = initConnection();
        Statement statement = connexion.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Employe LIMIT 10");
        print(resultSet.getStatement());//cf fonction définie à la fin de cette classe
        print(resultSet.getFetchSize());
        while ( resultSet.next() ) {
        	print(resultSet.getString("nom"));
        	System.out.println(resultSet.getDate("dateEmbauche"));
        }
        
        //Nouvelle entreprise
        
        
       
        EntrepriseService entrepriseService=new EntrepriseService("entServ");
        
        //Suppression de getPrimeAnnuel pour ne pas avoir à l'implémenter
        Employe employe=new Employe() {
		};
        
        employe.setNom("Berthet");//Le même employe sera utilisé pour toute les opérations
        
        
        
        Entreprise entreprise=new Entreprise();
        entreprise.setNom("etpse_1");
        System.out.println(entreprise.getNom());
        entrepriseService.CreerEntreprise(entreprise);
   
     
      //Ajouter un nouvel employé        
        
        entrepriseService.AjoutEmploye(entreprise,employe);
        
        //Supprimer un employe
        
        entrepriseService.SupprimeEmploye(employe, entreprise);
        
        // On recréer l'employe pour pouvoir l'utiliser par la suite (on a préciser au début qu'on utilisait le même employés pour toute les opérations)
        entrepriseService.AjoutEmploye(entreprise, employe);
        
        //Lister les employés
        
        java.util.List employes =new ArrayList();
        employes=entrepriseService.findAllEmploye(entreprise);
        
        //compter le nombre d'employe
        
        Integer nombreEmployes=entrepriseService.CountAllEmploye(entreprise);
        
        //Augmenter un employe de 5%
        
        entrepriseService.AugmenterSalaireEmploye(employe, 5.0);
        
        //Récupérer la masse salariale de l'entreprise
        
        Double masseSal=entrepriseService.MasseSalariale(entreprise);
        
        //REchercher un employe par matricule 
        
        EmployeRepository employeRepository;
        
       Employe employe2= employeRepository.findByMatricule("M11109");
       
       //afficher le matricule trouvé
        
        print(employe2.getMatricule());
         

        /*Technicien t = technicienRepository.findOne(4L);
        print(t);

        /*Manager m = managerRepository.findOneWithEquipeById(43L);
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
        
        System.out.println("Good Morning!!!!!!!!!!!!!!!!!!!!!!!!!");

    }
    
    
    public java.sql.Connection initConnection(){
        String url = "jdbc:mysql://localhost:3306/entreprise2";
        String user = "root";
        String pwd = "";

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
